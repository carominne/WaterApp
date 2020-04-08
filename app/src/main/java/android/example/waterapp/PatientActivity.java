package android.example.waterapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.example.waterapp.R;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

public class PatientActivity extends AppCompatActivity {

    public Patient patient;
    public final static String EXTRA_PATIENT = "com.example.myexampleapp.PATIENT";
    public static final int TEXT_REQUEST = 1;
    private static final String LOG_TAG = "test2";
    private String medication = "";


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        final Intent intent = getIntent();
        Log.i(LOG_TAG, "coucou wtf: " + intent);
        // patient = intent.getParcelableExtra("com.example.myexampleapp.PATIENT");

        try {
            final String get_singleURL = "http://10.0.2.2:8080/demo/patient/" + intent.getIntExtra("id", 0); // il faut mettre 10.0.2.2 pour avoir localhost dans l'émulateur andoid : http://10.0.2.2:8080/demo/all
            Log.i(LOG_TAG, "coucou wtf: " + get_singleURL);

            JsonObjectRequest get_singleRequest = new JsonObjectRequest(Request.Method.GET, get_singleURL, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    Log.i(LOG_TAG, "coucou wtf: " + response.toString());

                    //Log.i(LOG_TAG, "coucou wtf: " + response.toString());
                    //Toast.makeText(getApplicationContext(), "I am OK !" + response.toString(), Toast.LENGTH_LONG).show();
                    // sharedResponse(response.toString());

                    patient = new Gson().fromJson(String.valueOf(response), new TypeToken<Patient>() {
                    }.getType());

                    patient.setButton(intent.getIntExtra("button", 0));

                    this.updateTextViewName(patient.getForename() + " " + patient.getName());
                    this.updateTextViewBirthday(String.valueOf(patient.getBirthday()));
                    this.updateTextViewAge(String.valueOf(patient.getAge()));
                    this.updateTextViewRoom(String.valueOf(patient.getRoom()));
                    this.updateTextViewGender(patient.getGender());
                    this.updateTextViewDehydration(String.valueOf(patient.getDehydrationState()));
                    this.updateTextViewDisease(String.valueOf(patient.getDisease1()));
                    this.updateTextViewMedication(String.valueOf(patient.getMedication1()), String.valueOf(patient.getMedication2()), String.valueOf(patient.getMedication3()));
                    this.updateTextViewSize(String.valueOf(patient.getHeight()));
                    this.updateTextViewWeight(String.valueOf(patient.getWeight()));
                    this.updateTextViewHeartbeat(String.valueOf(patient.getHeartbeat()));
                    this.updateTextViewSpo(String.valueOf(patient.getSpo2()));


                }

                private void updateTextViewSpo(String spo) {
                    TextView textView = (TextView) findViewById(R.id.spo22);
                    textView.setText(spo);
                }

                private void updateTextViewHeartbeat(String heart) {
                    TextView textView = (TextView) findViewById(R.id.hearbeat2);
                    textView.setText(heart);
                }


                private void updateTextViewBirthday(String birthday) {
                    TextView textView = (TextView) findViewById(R.id.patient_birthday);
                    textView.setText(birthday);
                }

                private void updateTextViewWeight(String w) {
                    TextView textView = (TextView) findViewById(R.id.patient_weight);
                    textView.setText(w);
                }

                private void updateTextViewSize(String size) {
                    TextView textView = (TextView) findViewById(R.id.patient_size);
                    textView.setText(size);
                }


                private void updateTextViewMedication(String med1, String med2, String med3) {
                    TextView textView = (TextView) findViewById(R.id.patient_medication);
                    Boolean x = med2.equals("1");
                    Log.i(LOG_TAG, "coucou" + x);
                    if (med1.equals("1")) {
                        medication += "Medication 1\n";
                    }
                    if (med2.equals("1")) {
                        Log.i(LOG_TAG, med2);
                        medication += "Medication 2\n";
                    }
                    if (med3.equals("1")) {
                        medication += "Medication 3\n";
                    }
                    if (med2.equals("0") && med1.equals("0") && med3.equals("0")) {
                        medication += "No medication taken\n";
                    }
                    textView.setText(medication);
                }

                private void updateTextViewDisease(String dis1) {
                    TextView textView = (TextView) findViewById(R.id.patient_background);
                    String disease = "";

                    if (dis1.equals("1")) {
                        disease += "Renal Failure\n";
                    }
                    if (dis1.equals("0")) {
                        disease += "No particular disease\n";
                    }
                    textView.setText(disease);
                }

                public void updateTextViewAge(String toThis) {
                    TextView textView = (TextView) findViewById(R.id.patient_age);
                    textView.setText(toThis);
                }

                public void updateTextViewName(String toThis) {
                    TextView textView = (TextView) findViewById(R.id.patientName);
                    textView.setText(toThis);
                }

                public void updateTextViewGender(String toThis) {
                    TextView textView = (TextView) findViewById(R.id.patient_gender);
                    textView.setText(toThis);
                }


                public void updateTextViewRoom(String toThis) {
                    TextView textView = (TextView) findViewById(R.id.patient_room_number);
                    textView.setText(toThis);
                }

                public void updateTextViewDehydration(String toThis) {
                    TextView textView = (TextView) findViewById(R.id.patient_dehydration_level);
                    if (toThis.equals("0")) {
                        textView.setText("Low");
                    }
                    if (toThis.equals("1")) {
                        textView.setText("Medium");
                    }
                    if (toThis.equals("2")) {
                        textView.setText("High");
                    }
                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                    error.printStackTrace();
                }
            });
            VolleyController.getInstance(getApplicationContext()).addToRequestQueue(get_singleRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.getItem(0).setVisible(false);
        menu.getItem(1).setVisible(false);
        menu.getItem(2).setVisible(true);
        menu.getItem(3).setVisible(true);
        menu.getItem(4).setVisible(false);
        menu.getItem(5).setVisible(true);
        menu.getItem(6).setVisible(true);
        menu.getItem(7).setVisible(false);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_modifiy:
                Intent intent = new Intent(this, ModifyActivity.class);
                intent.putExtra(EXTRA_PATIENT, patient);
                Log.i(LOG_TAG, "STP " + patient.getGender());
                startActivity(intent);
                break;
            case R.id.action_stat:
                Toast.makeText(this, "Need activity for statistics", Toast.LENGTH_LONG).show();

               /* JSONObject postObject = new JSONObject();
                try {
                    //input your API parameter
                    String b =  patient.getBirthday();
                    String b2 = b.replace("\\", "");
                    Log.i(LOG_TAG, "STP" + b2);


                  //  postObject.put("id", patient.getId()+53);
                    postObject.put("room", patient.getRoom());
                    postObject.put("name", patient.getName());
                    postObject.put("forename", patient.getForename());
                    postObject.put("dehydrationState", patient.getDehydrationState());
                    postObject.put("heartbeat", patient.getHeartbeat());
                    postObject.put("spo2", patient.getSpo2());
                    postObject.put("gender", patient.getGender());
                    postObject.put("birthday", b2);
                    postObject.put("age", patient.getAge());
                    postObject.put("weight", patient.getWeight());
                    postObject.put("height", patient.getHeight());
                    postObject.put("medication1", patient.getMedication1());
                    postObject.put("medication2", patient.getMedication2());
                    postObject.put("medication3", patient.getMedication3());
                    postObject.put("disease1", patient.getDisease1());
*/


                    /*postObject.put("id", patient.getId());
                    postObject.put("room", patient.getRoom());
                    postObject.put("name", patient.getName());
                    postObject.put("forename", patient.getForename());
                    postObject.put("dehydrationState", patient.getDehydrationState());
                    postObject.put("heartbeat", patient.getHeartbeat());
                    postObject.put("spo2", patient.getSpo2());
                    postObject.put("gender", patient.getGender());
                    postObject.put("birthday", patient.getBirthday());
                    postObject.put("age", 12);
                    postObject.put("weight", patient.getWeight());
                    postObject.put("height", patient.getHeight());
                    postObject.put("medication1", 1);
                    postObject.put("medication2", 1);
                    postObject.put("medication3", 1);
                    postObject.put("disease1", 1);*/
             /*   } catch (JSONException e) {
                    e.printStackTrace();
                }
                final String postURL = "http://10.0.2.2:8080/demo/add";
                JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, postURL, postObject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                Toast.makeText(getApplicationContext(), "Post request sent !", Toast.LENGTH_LONG).show();

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                VolleyController.getInstance(getApplicationContext()).addToRequestQueue(postRequest);*/



                break;
            case R.id.action_back:
                Intent intent2 = new Intent(this, OverviewActivity.class);
                startActivityForResult(intent2, TEXT_REQUEST);
                break;
            case R.id.action_delete:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(true);
                builder.setTitle("Confirmation");
                builder.setMessage("Do you really want to delete this patient?");
                builder.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //                ajouter la requete vers la vbb
                                //    %%%%%%%%%%%%%%% DELETE Request

                                final String deleteURL = "http://10.0.2.2:8080/demo/patient/" + patient.getId().toString();
                                Log.i(LOG_TAG, "STP " + deleteURL);
                                StringRequest deleteRequest = new StringRequest(Request.Method.DELETE, deleteURL, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        Toast.makeText(getApplicationContext(), "Delete successful", Toast.LENGTH_SHORT).show();
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(getApplicationContext(), "Error in DELETE", Toast.LENGTH_LONG).show();
                                        error.printStackTrace();
                                    }
                                });
                                VolleyController.getInstance(getApplicationContext()).addToRequestQueue(deleteRequest);


                                Intent mainIntent;
                                mainIntent = new Intent(PatientActivity.this, OverviewActivity.class);
                                mainIntent.putExtra("var", 4);
                                Log.i(LOG_TAG, "STP " + mainIntent);
                                PatientActivity.this.startActivity(mainIntent);
                                PatientActivity.this.finish();
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

        }
        return true;
    }
}