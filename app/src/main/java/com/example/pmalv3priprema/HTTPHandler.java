package com.example.pmalv3priprema;

import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
public class HTTPHandler {
    private static final String classtag = HTTPHandler.class.getSimpleName();
    public String makeHTTPCall(String URLinput) {
        String response = null;
        try {
            URL url = new URL(URLinput);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = InputStreamToString(in);
        } catch (MalformedURLException e) {
            Log.e(classtag, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(classtag, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(classtag, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(classtag, "Exception: " + e.getMessage());
        }
        return response;
    }
    private String InputStreamToString(InputStream is) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is)); StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}