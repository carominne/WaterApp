package android.example.waterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.example.waterapp.R;

public class OverviewActivity extends AppCompatActivity {

    public static final int TEXT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
    }

    public void launchPatientActivity(View view) {
        Intent intent = new Intent(this, PatientActivity.class);
        startActivityForResult(intent, TEXT_REQUEST);
    }

    public void launchAddActivity(View view) {
        Intent intent = new Intent(this, AddActivity.class);
        startActivityForResult(intent, TEXT_REQUEST);
    }
}