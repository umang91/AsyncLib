package com.umang.aysnclib.example

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.umang.asynclib.onUIThread
import com.umang.asynclib.runAsync

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    //example
    runAsync {
      //code to be run asynchronously
      onUIThread {
        //code to run on main thread
      }
    }
  }
}
