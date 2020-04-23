package android.example.waterapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class OverviewActivity<jsonArray> extends AppCompatActivity  implements  View.OnClickListener {

    LinearLayout layout;
    public static final int TEXT_REQUEST = 1;
    private static final String LOG_TAG = "message";
    public final static String EXTRA_PATIENT = "com.example.myexampleapp.PATIENT";
    public String jpp ="";
    public  String mResponse;
    public Patient[] patients = null;
    public ArrayList<Patient> pat ;
    public Integer nb_patient = 0;

    private boolean mIsPeriodicWorkScheduled = false;

    private static Integer FIRST = 0;

    @RequiresApi(api = Build.VERSION_CODES.N)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        layout = findViewById(R.id.layout_overview);


//   %%%%%%%%%%%%%%% JSONARRAY GET REQUEST
        // Formulate the request and handle the response.


        request();

        SharedPreferences m = PreferenceManager.getDefaultSharedPreferences(this);
        mResponse = m.getString("Response", "");

        String[] words = {};
       if (mResponse.length() > 0) {
           mResponse = mResponse.substring(1, mResponse.length() - 1);

           String[] json = {};

           String line = mResponse;
           words = line.split("\\}\\,");


           patients = new Patient[words.length];

           for (int i = 0; i < words.length; i++) {

               Patient patient = null;
               if (i == words.length - 1) {
                   patient = new Gson().fromJson(words[i], new TypeToken<Patient>() {
                   }.getType());
               } else {
                   patient = new Gson().fromJson(words[i] + "}", new TypeToken<Patient>() {
                   }.getType());
               }

               patients[i] = patient;
           }


           pat = new ArrayList<>(Arrays.asList(patients));

           pat.sort(new SortedPatient());

           for (int i = 0; i < pat.size(); i++) {
               Patient patient = pat.get(i);
               patient.setButton(i);
               pat.set(i, patient);
           }

           LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(400, 200);
           params.setMargins(75, 48, 24, 24);


           for (int i = 0; i < words.length; i++) {


               final Button button = new Button(this);
               int remainder = i % 2;
               if (remainder == 0) {
                   params.setMargins(75, 48, 24, 24);
                   button.setLayoutParams(new LinearLayout.LayoutParams(params));
               }
               if (remainder == 1) {
                   params.setMargins(554, -224, 75, 24);
                   button.setLayoutParams(new LinearLayout.LayoutParams(params));
               }


               button.setId(pat.get(i).getButton());
               button.setText(pat.get(i).getName() + " " + pat.get(i).getForename() + " :" + pat.get(i).getRoom());


               if (pat.get(i).getDehydrationState().equals(0)) {
                   button.setBackgroundColor(Color.GREEN);
               }

               if (pat.get(i).getDehydrationState().equals(1)) {
                   button.setBackgroundColor(Color.YELLOW);
                   Log.i(LOG_TAG, "red: " + pat.get(i).getDehydrationState());
               }

               if (pat.get(i).getDehydrationState().equals(2)) {
                   button.setBackgroundColor(Color.RED);

               }

               button.setOnClickListener(this);


               layout.addView(button);
           }
       }


         Intent intent = getIntent();

        if (intent.getIntExtra("var", 1)==4) {
            Log.i(LOG_TAG, "JE REDEMARRE");
           Intent i = new Intent(OverviewActivity.this, OverviewActivity.class);
          //finish();
           overridePendingTransition(0, 0);
           startActivity(i);
           overridePendingTransition(0, 0);

       }

        if (FIRST == 0 ){
            FIRST = 1;
            WorkManager workManager = WorkManager.getInstance();
            PeriodicWorkRequest.Builder builder = new PeriodicWorkRequest.Builder(UploadWorker.class, 25, TimeUnit.MINUTES, 5, TimeUnit.MINUTES);
            PeriodicWorkRequest workRequest = builder.build();
            workManager.enqueue(workRequest);
            Log.i(LOG_TAG, "JE NOTIFIE que j'ajoute le work" );

        }

    }



    public int doWork() {

        String notif_text = "";
        Patient[] patients = null;


        SharedPreferences m = PreferenceManager.getDefaultSharedPreferences(this);
        String mResponse = m.getString("Response", "");
        Log.i(LOG_TAG, "AUTO");


        String[] words = {};
        Log.i(LOG_TAG, "JE NOTIFIE le get" + " " + mResponse);

        if (mResponse.length() > 0) {
            mResponse = mResponse.substring(1, mResponse.length() - 1);

            String[] json = {};

            String line = mResponse;
            words = line.split("\\}\\,");


            patients = new Patient[words.length];

            for (int i = 0; i < words.length; i++) {

                Patient patient = null;
                if (i == words.length - 1) {
                    patient = new Gson().fromJson(words[i], new TypeToken<Patient>() {
                    }.getType());
                } else {
                    patient = new Gson().fromJson(words[i] + "}", new TypeToken<Patient>() {
                    }.getType());
                }

                patients[i] = patient;
            }


            ArrayList<Patient> pat = new ArrayList<>(Arrays.asList(patients));

            for (int i = 0; i<words.length; i++){
                if (pat.get(i).getDehydrationState() == 2){

                    notif_text = notif_text +  pat.get(i).getName() +" " + pat.get(i).getForename() + " : " + pat.get(i).getRoom() + "\n";

                }
            }
        }

        if (notif_text.isEmpty()){
            notif_text = "rien";

        }else{
            Date now = new Date();
            String id_text = new SimpleDateFormat("ddHHmmss", Locale.US).format(now);
            int id = Integer.parseInt(new SimpleDateFormat("ddHHmmss", Locale.US).format(now));

            NotificationCompat.Builder builder2 = new NotificationCompat.Builder(this, "1")
                    .setLargeIcon(BitmapFactory. decodeResource (getResources() , R.drawable. ic_launcher_foreground ))
                    .setSmallIcon(R.drawable. ic_launcher_foreground )
                    .setContentTitle("Dehydrated patient(s)!")
                    .setColor(ContextCompat.getColor((Context) this, R.color.colorPrimary))
                    .setContentText(notif_text)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(notif_text))
                    .setPriority(NotificationCompat.PRIORITY_HIGH);


            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);



            notificationManager.notify(id , builder2.build());



            Log.i(LOG_TAG, "JE NOTIFIE2" + " " + mResponse);
        }

        Log.i(LOG_TAG, "JE NOTIFIE" + " " + notif_text);

        return 1;

    }



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



    public void launchPatientActivity(View view) {
        Intent intent = new Intent(this, PatientActivity.class);
        @SuppressLint("ResourceType") int ind = view.getId();
       // intent.putExtra(EXTRA_PATIENT, pat.get(ind));
        intent.putExtra("id", pat.get(ind).getId());
        intent.putExtra("button", ind);
    //    Log.i(LOG_TAG, "coucou0072: " + pat.get(ind));
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.getItem(0).setVisible(true);
        menu.getItem(1).setVisible(true);
        menu.getItem(2).setVisible(false);
        menu.getItem(3).setVisible(false);
        menu.getItem(4).setVisible(true);
        menu.getItem(5).setVisible(false);
        menu.getItem(6).setVisible(false);
        menu.getItem(7).setVisible(true);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch(id){
            case R.id.action_settings:
                Intent intent0 = new Intent(this, SettingsActivity.class);
                startActivityForResult(intent0, TEXT_REQUEST);
                break;
            case R.id.action_form:
                Intent intent = new Intent(this, FeedbackActivity.class);
                startActivityForResult(intent, TEXT_REQUEST);
                break;
            case R.id.action_deconnect:
                Intent intent2 = new Intent(this, MainActivity.class);
                startActivityForResult(intent2, TEXT_REQUEST);
                break;
            case R.id.action_add_patient:
                Intent intent3 = new Intent(this, AddActivity.class);
                startActivityForResult(intent3, TEXT_REQUEST);
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        launchPatientActivity(v);
    }
}