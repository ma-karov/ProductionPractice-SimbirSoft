package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class ContentTableRow_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_table_row);
        CinInputs();
    }

    @Override
    protected void onDestroy() { super.onDestroy(); }

    private void CinInputs() { final Intent SendIntent = getIntent(); EditText ID_EditText = findViewById(R.id.EditText_NameJob);
        ID_EditText.setText(SendIntent.getStringExtra("Name_Job") + ""); ID_EditText = findViewById(R.id.EditText_DescriptionJob);
        ID_EditText.setText(SendIntent.getStringExtra("Description_Job") + ""); ID_EditText = findViewById(R.id.EditText_StartJob);
        ID_EditText.setText(SendIntent.getStringExtra("StartJob") + ""); ID_EditText = findViewById(R.id.EditText_EndJob);
        ID_EditText.setText(SendIntent.getStringExtra("EndJob") + ""); }
}