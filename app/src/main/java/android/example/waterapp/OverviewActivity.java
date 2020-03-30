package android.example.waterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.example.waterapp.R;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OverviewActivity extends AppCompatActivity {

    public static final int TEXT_REQUEST = 1;
    private static final String LOG_TAG = "message";
    public String[] json = {"{'id':7,'name':'Hu','forename':'Louis','dehydrationState':0,'gender':'M','age':21,'medication1':false,'medication2':true,'medication3':false,'disease1':false,'room':34}", "{'id':7,'name':'Minne','forename':'Caro','dehydrationState':0,'gender':'M','age':21,'medication1':false,'medication2':true,'medication3':false,'disease1':false,'room':null}"};
    public Patient[] patients = new Patient[json.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        Log.i(LOG_TAG, "coucou: " + json.length);
        for (int i=0; i< json.length; i++){
            Patient patient = new Gson().fromJson(json[i], new TypeToken<Patient>() {
            }.getType());
            patients[i] = patient;
        }
        Log.i(LOG_TAG, "coucou: " + patients[0].getName());
        this.updateTextView(patients[0].getName()+" " + patients[0].getForename() +" : " + patients[0].getRoom());
    }

    public void updateTextView(String toThis) {
        Button textView = (Button) findViewById(R.id.buttonCollect);
        textView.setText(toThis);
    }


    public void launchPatientActivity(View view) {
        Intent intent = new Intent(this, PatientActivity.class);
        startActivityForResult(intent, TEXT_REQUEST);
    }

    public void launchAddActivity(View view) {
        Intent intent = new Intent(this, AddActivity.class);
        startActivityForResult(intent, TEXT_REQUEST);
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
        }
        return true;
    }
}