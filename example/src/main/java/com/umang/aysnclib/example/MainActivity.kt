package com.umang.aysnclib.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import dev.assemblage.asynclib.runAsync
import dev.assemblage.asynclib.runOnActivityThread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //example
        runAsync {
            //code to be run asynchronously
            Log.v("MainActivity", "worker")
            runOnActivityThread {
                //code to run on main thread
                Log.v("MainActivity", "UI")
            }
        }
    }
}
