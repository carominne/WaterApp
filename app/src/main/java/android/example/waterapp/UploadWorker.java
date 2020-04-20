package android.example.waterapp;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

public class UploadWorker<context> extends Worker {


    private Object context2;

    public UploadWorker(
            @NonNull Context context,
            @NonNull WorkerParameters params) {
        super(context, params);
        context2 = context;

    }



    public Result doWork() {

        request();


        SharedPreferences m = PreferenceManager.getDefaultSharedPreferences((Context) context2);
        mResponse = m.getString("Response", "");

        return (Result) m;

    }

    public static final int TEXT_REQUEST = 1;
    private static final String LOG_TAG = "message";
    public final static String EXTRA_PATIENT = "com.example.myexampleapp.PATIENT";
    public String jpp ="";
    public  String mResponse;

    public void request (){
        try {
            final String URL = "http://10.0.2.2:8080/demo/all"; // il faut mettre 10.0.2.2 pour avoir localhost dans l'émulateur andoid : http://10.0.2.2:8080/demo/all



            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {


                    //Toast.makeText(getApplicationContext(), "I am OK !" + response.toString(), Toast.LENGTH_LONG).show();
                    sharedResponse(response.toString());
                    Log.i(LOG_TAG, "coucou wtf2: " + response);

                    jpp = response.toString();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                    error.printStackTrace();
                }
            });
            VolleyController.getInstance(getApplicationContext()).addToRequestQueue(jsonArrayRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sharedResponse(String response) {
        SharedPreferences m = PreferenceManager.getDefaultSharedPreferences((Context) context2);
        SharedPreferences.Editor editor = m.edit();
        Log.i(LOG_TAG, "coucou0072: " + response);
        editor.putString("Response", response);
        editor.commit();
    }


}