package android.example.waterapp;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;

import static androidx.core.content.ContextCompat.getSystemService;

public class UploadWorker<context> extends Worker {


    private Object context2;

    private NotificationManagerCompat notificationManager;

    public static final int TEXT_REQUEST = 1;
    private static final String LOG_TAG = "message";
    public final static String EXTRA_PATIENT = "com.example.myexampleapp.PATIENT";
    public String jpp ="";
    public  String mResponse;
    public Patient[] patients = null;
    public ArrayList<Patient> pat ;
    public String notif_text;

    public UploadWorker(
            @NonNull Context context,
            @NonNull WorkerParameters params) {
        super(context, params);
        context2 = context;

    }



    public  Result doWork() {

        notif_text = "";
        request();



        SharedPreferences m = PreferenceManager.getDefaultSharedPreferences((Context) context2);
        mResponse = m.getString("Response", "");
        Log.i(LOG_TAG, "AUTO");


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

            NotificationCompat.Builder builder2 = new NotificationCompat.Builder(((Context) context2).getApplicationContext(), "2")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle("Dehydrated patient(s)!")
                    .setColor(ContextCompat.getColor((Context) context2, R.color.colorPrimary))
                    .setContentText(notif_text)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(notif_text))
                    .setPriority(NotificationCompat.PRIORITY_HIGH);


            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(((Context) context2).getApplicationContext());



            notificationManager.notify(id , builder2.build());



            Log.i(LOG_TAG, "JE NOTIFIE2" + " " + mResponse);
        }

        Log.i(LOG_TAG, "JE NOTIFIE" + " " + notif_text);

        return Result.success();

    }

    public void request(){
        try {
            final String URL = "http://10.0.2.2:8080/demo/all"; // il faut mettre 10.0.2.2 pour avoir localhost dans l'émulateur andoid : http://10.0.2.2:8080/demo/all



            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {


                    //Toast.makeText(getApplicationContext(), "I am OK !" + response.toString(), Toast.LENGTH_LONG).show();
                    sharedResponse(response.toString());

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
        editor.putString("Response", response);
        editor.commit();
    }





}