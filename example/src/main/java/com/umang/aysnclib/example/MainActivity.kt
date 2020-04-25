package com.umang.aysnclib.example

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.umang.asynclib.onUIThread
import com.umang.asynclib.runAsync

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    //example
    runAsync {
      //code to be run asynchronously
      Log.v("RamdonClass", "worker")
      onUIThread {
        //code to run on main thread
        Log.v("RamdonClass", "UI")
      }
    }
  }
}
