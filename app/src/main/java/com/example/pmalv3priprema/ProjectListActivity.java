package com.example.pmalv3priprema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProjectListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ArrayList<String> oItemList;
    private ListView lvProjectListView;
    private AdapterProjects oAdapter;
    private String sTitle;
    private String sParentId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);
        final Bundle oExtras = getIntent().getExtras();
        sParentId = oExtras.getString("parentId");
        oItemList = ApiSingleton.getInstance().getItemsForParentId(sParentId);
        lvProjectListView = (ListView) findViewById(R.id.listViewProjects);
        oAdapter = new AdapterProjects(this, oItemList);
        lvProjectListView.setAdapter(oAdapter);
        lvProjectListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        JSONObject oItem = null;
        String sHasChild = "0";
        String sId = "0";
        try {
            oItem = new JSONObject(oItemList.get(position));
            sHasChild = oItem.getString("has_child");
            sId = oItem.getString("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (sHasChild.equals("1")) {
            Intent oProjectListActivityIntent = new Intent(getApplicationContext(), ProjectListActivity.class);oProjectListActivityIntent.putExtra("parentId", sId);
            startActivity(oProjectListActivityIntent);
        } else {
            Intent oProjectActivityIntent = new Intent(getApplicationContext(), ProjectActivity.class);oProjectActivityIntent.putExtra("projectId", sId);
            startActivity(oProjectActivityIntent);
        }
    }
}