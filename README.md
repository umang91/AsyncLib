# AsyncLibKotlin
Light weight Kotlin library for performing tasks on background threads a.k.a WorkerThreads.

![Download](https://api.bintray.com/packages/umangchamaria/umang/aysnclibrary/images/download.svg)

This library has an advantage over the [AsyncTask](https://developer.android.com/reference/android/os/AsyncTask.html) in android as one need not code extra for checking if 
the Activity is still in foreground or the Fragment is still attached when the async execution 
ends and the result needs to be updated on the UI.

Dis-advantage of using AsyncTask in your SDK/library is maximum number of concurrent tasks limit.
 The OS restricts the maximum number of concurrent tasks to 128, as an SDK you might be 
 contributing to this limit which could ultimately result in some important tasks of app being 
 dropped off.
 
 # Installation
 
 Add the below dependency in the app level build gradle.
 
 ```
 implementation "com.umang:asynclib:$sdkVersion"
 ```
 replace `$sdkVersion` with the latest SDK version.
 
# Usage

Running on background thread and updating on ui thread without checks on the activity/fragment 
state

```
    runAsync {
      //code to be run asynchronously
      onUIThread {
        //code to run on main thread
      }
    }
```

Running on background thread and updating on ui thread only if the activity has not finished

```
    runAsync {
      //code to be run asynchronously
      runOnActivityThread {
        //code to run on main thread
      }
    }
    
```
Running on background thread and updating on ui thread only if the fragment has not detached

```
    runAsync {
      //code to be run asynchronously
      runOnFragmentThread {
        //code to run on main thread
      }
    }
    
```


    
