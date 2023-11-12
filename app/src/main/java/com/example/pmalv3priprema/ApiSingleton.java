package com.example.pmalv3priprema;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ApiSingleton {


    static final String JSON_FILE = "projekti.json";
    public static final String JSON_PATH = "http://www.vsmti.hr/test/";
    private static ApiSingleton oInstance = null;
    public ArrayList<String> oList = null;
    public ArrayList<String> oListByParent = null;
    public static Context oGlobalContext = null;

    protected ApiSingleton() {

    }

    public static ApiSingleton getInstance() {
        if (oInstance == null) {
            oInstance = new ApiSingleton();
        }
        return oInstance;
    }

    private void loadJsonFromAssets(String sFileName) {
        AssetManager oAm = oGlobalContext.getAssets();
        InputStream oInputStream = null;
        String sJson = null;
        try {
            oInputStream = oAm.open(sFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ByteArrayOutputStream oByteStream = new ByteArrayOutputStream();
        int i;
        try {
            i = oInputStream.read();
            while (i != -1) {
                oByteStream.write(i);
                i = oInputStream.read();
            }
            oInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sJson = oByteStream.toString();
        System.out.println("JSON: " + sJson);
        jsonStringToStringArray(sJson);
    }

    private void jsonStringToStringArray(String sJson) {
        JSONArray oArray = null;
        try {
            oArray = new JSONArray(sJson);
            int n = oArray.length();
            oList = new ArrayList<String>(n);
            for (int i = 0; i < n; i++) {
                String sTmp = oArray.getString(i);
                oList.add(sTmp);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getItemsForParentId(String sParentId) {
        int n = oList.size();
        JSONObject oTmp = null;
        oListByParent = new ArrayList<String>(n);
        for (int i = 0; i < n; i++) {
            try {
                oTmp = new JSONObject(oList.get(i));
                if (oTmp.getString("subtype").equals(sParentId)) {
                    oListByParent.add(oList.get(i));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return oListByParent;
    }

    public String getItemByItemId(String sItemId) {
        int n = oList.size();
        JSONObject oTmp = null;
        try {
            for (int i = 0; i < n; i++) {
                oTmp = new JSONObject(oList.get(i));
                if (oTmp.getString("id").equals(sItemId)) {
                    return oList.get(i);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void downloadJson(String sJsonPath, String sJsonStorageName) {
        new downloadJsonTask(sJsonPath, sJsonStorageName).execute(sJsonPath);
    }

    private class downloadJsonTask extends AsyncTask<String, Void, Void> {
        String sJsonPath;
        String sJsonStorageName;

        public downloadJsonTask(String sJsonPath, String sJsonStorageName) {
            this.sJsonPath = sJsonPath;
            this.sJsonStorageName = sJsonStorageName;
        }

        protected Void doInBackground(String... urls) {
            String url = urls[0];
            HTTPHandler oHTTPHandler = new HTTPHandler();
            String sJson = oHTTPHandler.makeHTTPCall(url);
            jsonStringToStringArray(sJson);
            return null;
        }
    }
    public void checkJson() {
        loadJsonFromAssets(JSON_FILE);
        downloadJson(JSON_PATH,JSON_FILE);
    }
}
