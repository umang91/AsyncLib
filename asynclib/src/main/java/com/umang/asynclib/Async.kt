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

/**
 * Default handler for exceptions. Prints the error stacktrace.
 */
private val errorTrace = { throwable : Throwable -> throwable.printStackTrace() }

class AsyncContext<T>(val weakRef: WeakReference<T>)

/**
 * Execute [function] on the UI/main thread
 * @param function code to be executed on main/UI thread
 */
fun <T> AsyncContext<T>.runOnUIThread(function: (T) -> Unit): Boolean {
  val ref = weakRef.get() ?: return false
  if (MainThreadHelper.mainThread == java.lang.Thread.currentThread()) {
    function(ref)
  } else {
    MainThreadHelper.handler.post { function(ref) }
  }
  return true
}

/**
 * Execute [function] on the application UI thread if the underlying [Activity] still exists and is not finished.
 *  If it is not exist anymore or if it was finished, [function] will not be called.
 *  @param function  code to be executed on the main/UI thread
 */
fun <T: Activity> AsyncContext<T>.runOnActivityUIThread(function: (T) -> Unit): Boolean {
  val activity = weakRef.get() ?: return false
  if (activity.isFinishing) return false
  activity.runOnUiThread { function(activity) }
  return true
}

/**
 * Execute [function] on the application UI thread if the underlying [Fragment] still exists and is not
 * detached.
 *  If it is not exist anymore or if it was finished, [function] will not be called.
 *  @param function code to be executed on the main/UI thread
 */
fun <T: Fragment> AsyncContext<T>.runOnFragmentUIThread(function: (T) -> Unit): Boolean {
  val fragment = weakRef.get() ?: return false
  if (fragment.isDetached) return false
  val activity = fragment.activity ?: return false
  activity.runOnUiThread { function(fragment) }
  return true
}

/**
 * Execute [task] asynchronously.
 *
 * @param exceptionHandler optional exception handler.
 *  If defined, any exceptions thrown inside [task] will be passed to it. If not, exceptions will be ignored.
 * @param task  code to execute asynchronously.
 */
fun <T> T.runAsync(
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
      Executors.newCachedThreadPool()

  fun <T> submit(task: () -> T): Future<T> {
    return executor.submit(task)
  }
}

private object MainThreadHelper {
  val handler = Handler(Looper.getMainLooper())
  val mainThread: Thread = Looper.getMainLooper().thread
}