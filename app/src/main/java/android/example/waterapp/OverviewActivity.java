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
import android.widget.Toast;

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

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.getItem(0).setVisible(true);
        menu.getItem(1).setVisible(true);
        menu.getItem(2).setVisible(false);
        menu.getItem(3).setVisible(false);
        menu.getItem(4).setVisible(false);
        menu.getItem(5).setVisible(true);
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