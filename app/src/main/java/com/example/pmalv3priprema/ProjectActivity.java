package com.example.pmalv3priprema;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class ProjectActivity extends AppCompatActivity {
    TextView txtDesc;
    private String sProjectId;
    private String sDesc;
    private String sTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        final Bundle oExtras = getIntent().getExtras();
        sProjectId = oExtras.getString("projectId");
        txtDesc = (TextView) findViewById(R.id.textViewProjectDesc);
        String sJson = ApiSingleton.getInstance().getItemByItemId(sProjectId);
        JSONObject oJson = null;
        try {
            oJson = new JSONObject(sJson);
            sTitle = oJson.getJSONObject("translations").getJSONObject("1").getString("title");setTitle(sTitle);
            sDesc = oJson.getJSONObject("translations").getJSONObject("1").getString("content");txtDesc.setText(Html.fromHtml(sDesc));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}