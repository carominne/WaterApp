package android.example.waterapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.example.waterapp.R;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OverviewActivity extends AppCompatActivity  implements  View.OnClickListener {

    LinearLayout layout;
    public static final int TEXT_REQUEST = 1;
    private static final String LOG_TAG = "message";
    public final static String EXTRA_PATIENT = "com.example.mysampleapp.PATIENT";
    public String[] json = {"{'id':7,'name':'Hu','forename':'Louis','dehydrationState': 2,'gender':'M','age':21,'medication1':1,'medication2':1,'medication3':0,'disease1':0,'room':34}", "{'id':7,'name':'Minne','forename':'Caro','dehydrationState':1,'gender':'F','age':21,'medication1':0,'medication2':1,'medication3':1,'disease1':0,'room':12}", "{'id':7,'name':'Minne','forename':'Caro','dehydrationState':1,'gender':'F','age':21,'medication1':0,'medication2':1,'medication3':1,'disease1':0,'room':12}", "{'id':7,'name':'Minne','forename':'Caro','dehydrationState':0,'gender':'F','age':21,'medication1':0,'medication2':1,'medication3':1,'disease1':0,'room':12}","{'id':7,'name':'Minne','forename':'Caro','dehydrationState':0,'gender':'F','age':21,'medication1':0,'medication2':1,'medication3':1,'disease1':0,'room':12}", "{'id':7,'name':'Minne','forename':'Caro','dehydrationState':0,'gender':'F','age':21,'medication1':0,'medication2':1,'medication3':1,'disease1':0,'room':12}", "{'id':7,'name':'Minne','forename':'Caro','dehydrationState':0,'gender':'F','age':21,'medication1':0,'medication2':1,'medication3':1,'disease1':0,'room':12}", "{'id':7,'name':'Minne','forename':'Caro','dehydrationState':0,'gender':'F','age':21,'medication1':0,'medication2':1,'medication3':1,'disease1':0,'room':12}", "{'id':7,'name':'Minne','forename':'Caro','dehydrationState':0,'gender':'F','age':21,'medication1':0,'medication2':1,'medication3':1,'disease1':0,'room':12}", "{'id':7,'name':'Minne','forename':'Caro','dehydrationState':0,'gender':'F','age':21,'medication1':0,'medication2':1,'medication3':1,'disease1':0,'room':12}", "{'id':7,'name':'Minne','forename':'Caro','dehydrationState':0,'gender':'F','age':21,'medication1':0,'medication2':1,'medication3':1,'disease1':0,'room':12}", "{'id':7,'name':'Minne','forename':'Caro','dehydrationState':2,'gender':'F','age':21,'medication1':0,'medication2':1,'medication3':1,'disease1':0,'room':12}", "{'id':7,'name':'Minne','forename':'Caro','dehydrationState':0,'gender':'F','age':21,'medication1':0,'medication2':1,'medication3':1,'disease1':0,'room':12}", "{'id':7,'name':'Minne','forename':'Caro','dehydrationState':0,'gender':'F','age':21,'medication1':0,'medication2':1,'medication3':1,'disease1':0,'room':12}", "{'id':7,'name':'Minne','forename':'Caro','dehydrationState':0,'gender':'F','age':21,'medication1':0,'medication2':1,'medication3':1,'disease1':0,'room':12}", "{'id':7,'name':'Minne','forename':'Caro','dehydrationState':0,'gender':'F','age':21,'medication1':0,'medication2':1,'medication3':1,'disease1':0,'room':12}"};
    public Patient[] patients = new Patient[json.length];
    public ArrayList<Patient> pat ;
    public Integer nb_patient = json.length;


    @RequiresApi(api = Build.VERSION_CODES.N)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        layout = findViewById(R.id.layout_overview);


        for (int i = 0; i< json.length; i++) {

            Patient patient = new Gson().fromJson(json[i], new TypeToken<Patient>() {
            }.getType());
            patients[i] = patient;
        }

        pat = new ArrayList<>(Arrays.asList(patients));
        Log.i(LOG_TAG, "coucou: " + pat.get(0).getName());
        pat.sort(new SortedPatient());

        LinearLayout.LayoutParams params= new LinearLayout.LayoutParams(400, 200);
        params.setMargins(75, 48, 24, 24);


        for (int i = 0; i< json.length; i++) {

            final Button button = new Button(this);
            int remainder = i % 2;
            if (remainder == 0){
                params.setMargins(75, 48, 24, 24);
                button.setLayoutParams(new LinearLayout.LayoutParams(params));
            }
            if (remainder == 1){
                params.setMargins(554, -224, 75, 24);
                button.setLayoutParams(new LinearLayout.LayoutParams(params));
            }


            button.setId(i);
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

    public void launchPatientActivity(View view) {
        Intent intent = new Intent(this, PatientActivity.class);
//        if (view.getId() == R.id.buttonCollect){
        @SuppressLint("ResourceType") int ind = view.getId();
        intent.putExtra(EXTRA_PATIENT, pat.get(ind));
        Log.i(LOG_TAG, "coucou" + patients[ind].getGender());
//        }
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
                Toast.makeText(this, "Need activity for settings", Toast.LENGTH_LONG).show();
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