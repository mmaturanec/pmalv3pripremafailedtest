package com.example.pmalv3priprema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ApiSingleton.getInstance();
        ApiSingleton.oGlobalContext = getApplicationContext();
        ApiSingleton.getInstance().checkJson();
        Button oBtnListaProjekata = findViewById(R.id.btnListaProjekata);
        oBtnListaProjekata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent oProjectListActivityIntent = new Intent(getApplicationContext(), ProjectListActivity.class);oProjectListActivityIntent.putExtra("parentId", "0");
                startActivity(oProjectListActivityIntent);
            }
        });
    }
}