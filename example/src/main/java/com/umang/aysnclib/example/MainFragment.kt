package com.umang.aysnclib.example

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import com.umang.asynclib.onUIThread
import com.umang.asynclib.runAsync
import com.umang.asynclib.runOnFragmentThread

/**
 * @author Umang Chamaria
 * Date: 25/04/20
 */
class MainFragment: Fragment(){
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    runAsync {
      //code to be run asynchronously
      Log.v("MainActivity", "worker")
      runOnFragmentThread {
        //code to run on main thread
        Log.v("MainActivity", "UI")
      }
    }
  }
}