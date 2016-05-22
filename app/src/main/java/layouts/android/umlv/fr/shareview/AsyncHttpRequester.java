package layouts.android.umlv.fr.shareview;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Heretic on 22/05/2016.
 */
public class AsyncHttpRequester extends AsyncTask<HttpBundle, Void, String> {

    private final String DEBUG_TAG = "SpiderBoard";

    @Override
    protected String doInBackground(HttpBundle... params) {
        try {
            return downloadUrl(params[0]);
        } catch (IOException e) {
            return "Invalid URL or error occurred";
        }
    }

    public String downloadUrl(HttpBundle bundle) throws IOException {
        InputStream is = null;
        try {
            URL url = new URL(bundle.getFullRequest());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod(bundle.getMethod());
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d(DEBUG_TAG, "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is, 5_000);
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }
}
