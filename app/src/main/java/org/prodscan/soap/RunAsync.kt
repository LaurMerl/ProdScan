package org.prodscan.soap

import android.os.AsyncTask

public class RunAsync<T, U>  : AsyncTask<T, Int, U> {

    override fun onCancelled(result: U) {
        super.onCancelled(result)
    }

    override fun onCancelled() {
        super.onCancelled()
    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
    }

    override fun onPostExecute(result: U) {
        super.onPostExecute(result)
    }

    override fun onPreExecute() {
        super.onPreExecute()
    }

    val f:(T) -> U ;
      constructor(func:(T) -> U){
            f = func
      }

    override fun doInBackground(ts: Array<T>): U {
            return f(ts[0])
    }
}