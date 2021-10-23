package com.umang.aysnclib.example

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umang.asynclib.runAsync
import com.umang.asynclib.runOnFragmentThread

/**
 * @author Umang Chamaria
 * Date: 25/04/20
 */
class MainFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        runAsync {
            //code to be run asynchronously
            Log.v("MainFragment", "worker")
            runOnFragmentThread {
                //code to run on main thread
                Log.v("MainFragment", "UI")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }
}