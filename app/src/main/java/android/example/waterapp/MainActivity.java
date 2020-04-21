package android.example.waterapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
    //private RequestQueue requestQueue = VolleyController.getInstance(this.getApplicationContext()).getRequestQueue();
    private static final String LOG_TAG = "message";


    public static final String TAG_MY_WORK = "mywork";



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


        createNotificationChannel();



/*        if(!isWorkScheduled(TAG_MY_WORK)) { // check if your work is not already scheduled
            Log.i(LOG_TAG, "JE NOTIFIE que j'ajoute le work" );
            scheduleWork(TAG_MY_WORK); // schedule your work
        }*/
         


}


    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "channel";
            String description = "description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("1", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
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
    public void verifycredentials(View view) {
        // insérer ici le code qui sert à vérifier les données d'identification entrées par l'utilisateur
        boolean credentials;
        credentials = false;
        String username = mMainUsername.getText().toString();
        String password = mMainPassword.getText().toString();

        if (true) {
            credentials = true; // vérifications des données, si c'est juste, on met credentials à 1
        }
        if (credentials == true) {
            Intent intent = new Intent(this, OverviewActivity.class);
            intent.putExtra("var", 4);
            MainActivity.this.startActivity(intent);
        }
    }
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%

}