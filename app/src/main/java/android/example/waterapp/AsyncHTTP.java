package android.example.waterapp;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by jf on 03/05/17.
 */
public class AsyncHTTP extends AsyncTask<String, Integer, String> {

    public String result;

    private static final int LOG_TAG = 1;
    private AppCompatActivity myActivity;

    public AsyncHTTP(AppCompatActivity mainActivity) {
        myActivity = mainActivity;
    }
    
    public String jpp(String url2){
        // publishProgress(1);
        Log.i(String.valueOf(LOG_TAG), "pitié58" + result);
        //try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }


        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(url2);

            urlConnection = (HttpURLConnection) url.openConnection(); // Open
            InputStream in = new BufferedInputStream(urlConnection.getInputStream()); // Stream
            //publishProgress(2);

            //result = readStream(in); // Read stream
        }
        catch (MalformedURLException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }
        //finally { if (urlConnection != null)
        //    urlConnection.disconnect();  }

        //(4);
        Log.i(String.valueOf(LOG_TAG), "pitié59" + result);
        return result; // returns the result
    }
    

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(String... strings) {
       /* publishProgress(1);
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }

        URL url = null;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(strings[0]);

            urlConnection = (HttpURLConnection) url.openConnection(); // Open
            InputStream in = new BufferedInputStream(urlConnection.getInputStream()); // Stream
            publishProgress(2);

            result = readStream(in); // Read stream
        }
        catch (MalformedURLException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }
        finally { if (urlConnection != null)
            urlConnection.disconnect();  }

        publishProgress(4);
        Log.i(String.valueOf(LOG_TAG), "pitié56" + result);*/
        return result; // returns the result
    }


    private String readStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is),1000);
        for (String line = r.readLine(); line != null; line =r.readLine()){
            sb.append(line);
            //Log.i(String.valueOf(LOG_TAG), "pitié77" + sb.toString());
        }
        is.close();
        return sb.toString();
    }


}
