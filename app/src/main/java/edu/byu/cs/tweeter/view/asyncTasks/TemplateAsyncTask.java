package edu.byu.cs.tweeter.view.asyncTasks;

import android.os.AsyncTask;
import android.util.Log;

abstract public class TemplateAsyncTask<Req, Res> extends AsyncTask<Req, Void, Res> {
    abstract Res sendRequest(Req[] requests) throws Exception;
    public abstract void handleException(Exception ex);
    public abstract void handleResponse(Res response);
    private Exception exception;

    @Override
    protected Res doInBackground(Req... requests) {
        Res response = null;

        try {
            response = sendRequest(requests);
        } catch (Exception ex) {
            exception = ex;
        }

        return response;
    }

    @Override
    protected void onPostExecute(Res response) {
        if(exception != null) {
            handleException(exception);
        } else {
            handleResponse(response);
        }
    }
}
