package android.example.waterapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.transform.Result;

public class UploadWorker extends Worker {


    public UploadWorker(
            @NonNull Context context,
            @NonNull WorkerParameters params) {
        super(context, params);
    }


    public SharedPreferences doWork() {

        request();

        SharedPreferences m = PreferenceManager.getDefaultSharedPreferences(this);
        mResponse = m.getString("Response", "");

        return m;

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
        SharedPreferences m = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = m.edit();
        Log.i(LOG_TAG, "coucou0072: " + response);
        editor.putString("Response", response);
        editor.commit();
    }


}