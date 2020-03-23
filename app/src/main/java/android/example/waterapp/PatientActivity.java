package android.example.waterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.example.waterapp.R;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class PatientActivity extends AppCompatActivity {

    public static final int TEXT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        Intent intent = getIntent();
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.getItem(0).setVisible(false);
        menu.getItem(1).setVisible(false);
        menu.getItem(2).setVisible(true);
        menu.getItem(3).setVisible(true);
        menu.getItem(4).setVisible(true);
        menu.getItem(5).setVisible(false);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch(id){
            case R.id.action_modifiy:
                Intent intent = new Intent(this, ModifyActivity.class);
                startActivityForResult(intent, TEXT_REQUEST);
                break;
            case R.id.action_stat:
                Toast.makeText(this, "Need activity for statistics", Toast.LENGTH_LONG).show();
            case R.id.action_back:
                Intent intent2 = new Intent(this, OverviewActivity.class);
                startActivityForResult(intent2, TEXT_REQUEST);

        }
        return true;
    }
}