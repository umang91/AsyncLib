package com.umang.asynclib

import android.app.Activity
import android.app.Fragment
import android.os.Handler
import android.os.Looper
import java.lang.ref.WeakReference
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

/**
 * @author Umang Chamaria
 */
private val errorTrace = { throwable : Throwable -> throwable.printStackTrace() }

class AsyncContext<T>(val weakRef: WeakReference<T>)

fun <T> AsyncContext<T>.uiThread(f: (T) -> Unit): Boolean {
  val ref = weakRef.get() ?: return false
  if (MainThreadHelper.mainThread == java.lang.Thread.currentThread()) {
    f(ref)
  } else {
    MainThreadHelper.handler.post { f(ref) }
  }
  return true
}

fun <T: Activity> AsyncContext<T>.activityUiThread(f: (T) -> Unit): Boolean {
  val activity = weakRef.get() ?: return false
  if (activity.isFinishing) return false
  activity.runOnUiThread { f(activity) }
  return true
}

fun <T: Fragment> AsyncContext<T>.fragmentUiThread(f: (T) -> Unit): Boolean {
  val fragment = weakRef.get() ?: return false
  if (fragment.isDetached) return false
  val activity = fragment.activity ?: return false
  activity.runOnUiThread { f(fragment) }
  return true
}

/**
 * Execute [task] asynchronously.
 *
 * @param exceptionHandler optional exception handler.
 *  If defined, any exceptions thrown inside [task] will be passed to it. If not, exceptions will be ignored.
 * @param task the code to execute asynchronously.
 */
fun <T> T.doAsync(
    exceptionHandler: ((Throwable) -> Unit)? = errorTrace,
    task: AsyncContext<T>.() -> Unit
): Future<Unit> {
  val context = AsyncContext(WeakReference(this))
  return AsyncExecutor.submit {
    return@submit try {
      context.task()
    } catch (thr: Throwable) {
      val result = exceptionHandler?.invoke(thr)
      if (result != null) {
        result
      } else {
        Unit
      }
    }
  }
}


internal object AsyncExecutor {
  var executor: ExecutorService =
      Executors.newScheduledThreadPool(2 * Runtime.getRuntime().availableProcessors())

  fun <T> submit(task: () -> T): Future<T> {
    return executor.submit(task)
  }

}

private object MainThreadHelper {
  val handler = Handler(Looper.getMainLooper())
  val mainThread: Thread = Looper.getMainLooper().thread
}