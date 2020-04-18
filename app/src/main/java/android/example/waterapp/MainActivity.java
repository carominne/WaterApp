package android.example.waterapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        mMainUsername = findViewById(R.id.editText_username);
        mMainPassword = findViewById(R.id.editText_password);
       sharedResponse("");
        //%%%%%%%%%%%%%%%%%%%%%%%%%%%

      //  final TextView resultTextView = (TextView) findViewById(R.id.textview_main);


//   %%%%%%%%%%%%%%%  GET REQUEST for all patients (use of jsonArray)
        // Formulate the request and handle the response.
      /*  try {
            final String get_allURL = "http://10.0.2.2:8080/demo/all"; // il faut mettre 10.0.2.2 pour avoir localhost dans l'émulateur andoid : http://10.0.2.2:8080/demo/all

            JsonArrayRequest get_allRequest = new JsonArrayRequest(Request.Method.GET, get_allURL, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                 //   resultTextView.setText("Response : " + response.toString());
                   //Log.i(LOG_TAG, "coucou wtf: " + response.toString());
                    //Toast.makeText(getApplicationContext(), "I am OK !" + response.toString(), Toast.LENGTH_LONG).show();
                    sharedResponse(response.toString());

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Error in GET all", Toast.LENGTH_LONG).show();
                    error.printStackTrace();
                }
            });
            VolleyController.getInstance(getApplicationContext()).addToRequestQueue(get_allRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }*/


        //%%%%%%%%%%%%  GET REQUEST for a single patient (note: we have to use jsonObject request for a single patient)
        /*try {
            final String get_singleURL = "http://10.0.2.2:8080/demo/patient/12"; //12= id du patient dans la DB

            JsonObjectRequest get_singleRequest = new JsonObjectRequest(Request.Method.GET, get_singleURL, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    resultTextView.setText("Response : " + response.toString());
                    //Toast.makeText(getApplicationContext(), "I am OK !" + response.toString(), Toast.LENGTH_LONG).show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Error in GET single patient", Toast.LENGTH_LONG).show();
                    error.printStackTrace();
                }
            });
            VolleyController.getInstance(getApplicationContext()).addToRequestQueue(get_singleRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        //%%%%%%%%%%%%%%% DELETE Request

 /*       final String deleteURL = "http://10.0.2.2:8080/demo/patient/19";
        StringRequest deleteRequest = new StringRequest(Request.Method.DELETE, deleteURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //resultTextView.setText("Response : " + response.toString());
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
*/

        //%%%%%%%%%%%%%%%%%%% POST request

      /*  JSONObject postObject = new JSONObject();
        try {
            //input your API parameters
            postObject.put("id", "2");
            postObject.put("roomr", 12);
            postObject.put("name", "testPOST");
            postObject.put("forename", "post");
            postObject.put("dehydrationState", 1);
            postObject.put("heartbeat", 80);
            postObject.put("spo2", 100);
            postObject.put("gender", "M");
            postObject.put("birthday", "20/12/2012");
            postObject.put("weight", 70);
            postObject.put("height", 170);
            postObject.put("medication1", 1);
            postObject.put("medication2", 1);
            postObject.put("medication3", 1);
            postObject.put("disease1", 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String postURL = "http://10.0.2.2:8080/demo/add";
        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, postURL, postObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        resultTextView.setText("String Response : " + response.toString());
                        Toast.makeText(getApplicationContext(), "Post request sent !", Toast.LENGTH_LONG).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultTextView.setText("Error POSTing");
            }
        });
        VolleyController.getInstance(getApplicationContext()).addToRequestQueue(postRequest);
*/


        //%%%%%%%%%%%%% PUT request
     /*   JSONObject putObject = new JSONObject();
        try {
            //input your API parameters
            putObject.put("id", "2");
            putObject.put("roomNumber", 12);
            putObject.put("name", "testput");
            putObject.put("forename", "putput");
            putObject.put("dehydrationState", 1);
            putObject.put("heartbeat", 80);
            putObject.put("spo2", 100);
            putObject.put("gender", "M");
            putObject.put("birthday", "20/12/2012");
            putObject.put("weight", 70);
            putObject.put("height", 170);
            putObject.put("medication1", 1);
            putObject.put("medication2", 1);
            putObject.put("medication3", 1);
            putObject.put("disease1", 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String putURL = "http://10.0.2.2:8080/demo/patientAll/23";
        JsonObjectRequest putRequest = new JsonObjectRequest(Request.Method.PUT, putURL, putObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //resultTextView.setText("String Response : " + response.toString());
                        Toast.makeText(getApplicationContext(), "PUT request sent !", Toast.LENGTH_LONG).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultTextView.setText("Error PUTing");
            }
        });
        VolleyController.getInstance(getApplicationContext()).addToRequestQueue(putRequest);
    }*/


//%%%%%%%%%%%%%%% String GET REQUEST
/*              try {
            final String URL = "http://10.0.2.2:8080/demo/all"; //https://api.ipify.org/?format=json

            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    resultTextView.setText("Response : " + response);
                    Toast.makeText(getApplicationContext(), "I am OK !" + response.toString(), Toast.LENGTH_LONG).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                    error.printStackTrace();
                }
            });
            VolleyController.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

/* OPERATIONS AVEC LA REQUESTQUEUE
// Get a RequestQueue
    RequestQueue requestQueue = VolleyController.getInstance(this.getApplicationContext()).
            getRequestQueue();

// Add a request (in this example, called stringRequest) to your RequestQueue.
VolleyController.getInstance(this).addToRequestQueue(stringRequest);
*/

    public void sharedResponse(String response) {
        SharedPreferences m = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = m.edit();
        Log.i(LOG_TAG, "coucou00725: " + response);
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