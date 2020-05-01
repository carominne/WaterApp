package android.example.waterapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%
    public static final String USERNAME_KEY =
            "com.example.android.twoactivities.extra.USERNAME_KEY";
    public static final String PASSWORD_KEY =
            "com.example.android.twoactivities.extra.PASSWORD_KEY";
    private EditText mMainUsername;
    private EditText mMainPassword;
    public String file  ="";
    //private RequestQueue requestQueue = VolleyController.getInstance(this.getApplicationContext()).getRequestQueue();
    private static final String LOG_TAG = "message";

    public static final String TAG_MY_WORK = "mywork";
    public User[] users = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        mMainUsername = findViewById(R.id.editText_username);
        mMainPassword = findViewById(R.id.editText_password);
        sharedResponse("");

        Log.i(LOG_TAG, "JE NOTIFIE3" );




}

    private void request() {

        try {
            final String get_singleURL = "http://10.0.2.2:8080/demo/allUser"; // il faut mettre 10.0.2.2 pour avoir localhost dans l'émulateur andoid : http://10.0.2.2:8080/demo/all
            Log.i(LOG_TAG, "coucou wtf: " + get_singleURL);

            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, get_singleURL, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    Log.i(LOG_TAG, "coucou wtf: " + response.toString());
                    boolean credentials;
                    credentials = false;
                    boolean enter = false;
                    String username = mMainUsername.getText().toString();
                    String password = mMainPassword.getText().toString();
                    String[] words = {};
                    Log.i(LOG_TAG, "coucou toi" + mMainPassword + mMainUsername);
                    Log.i(LOG_TAG, "coucou toi2" + response.length());
                    if (response.length() > 0) {
                        Log.i(LOG_TAG, "coucou toi2" + response.toString().length());
                        String res = response.toString().substring(1, response.toString().length() - 1);

                        Log.i(LOG_TAG, "coucou toi2" + res);

                        String[] json = {};

                        String line = res.toString();
                        words = line.split("\\}\\,");
                        Log.i(LOG_TAG, "coucou toi4" + words.length);

                        users = new User[words.length];

                        for (int i = 0; i < words.length; i++) {

                            User patient = null;
                            if (i == words.length - 1) {
                                patient = new Gson().fromJson(words[i], new TypeToken<User>() {
                                }.getType());

                            } else {
                                patient = new Gson().fromJson(words[i] + "}", new TypeToken<User>() {
                                }.getType());
                            }

                            users[i] = patient;
                        }

                        credentials = false;
                        for (int i = 0; i < words.length; i++){
                            Log.i(LOG_TAG, "coucou toi4" + username);
                            Log.i(LOG_TAG, "coucou toi4" + users[i].getIdentifier());



                            if ((username.equals(users[i].getIdentifier()))){
                                if (password.equals(users[i].getPassword())){
                                   credentials = true;
                                }
                            }



                            if (credentials){

                                Log.i(LOG_TAG, "coucou toi5" + password);

                                Intent intent = new Intent(getApplicationContext(), OverviewActivity.class);
                                intent.putExtra("var", 4);
                                intent.putExtra("enter", credentials);
                                MainActivity.this.startActivity(intent);

                                // vérifications des données, si c'est juste, on met credentials à 1

                            }

                        }
                        if (!credentials){
                            Toast.makeText(getApplicationContext(), "This password / username is not in the database. Please try again.", Toast.LENGTH_LONG).show();
                        }
                    }

                    //Log.i(LOG_TAG, "coucou wtf: " + response.toString());
                    // sharedResponse(response.toString());

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
        editor.putString("Response", response);
        editor.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //%%%%%%%%%%%%%%%%%%%%%%%%%%%
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%
    public void verifycredentials(View view){
        request();

    }


}