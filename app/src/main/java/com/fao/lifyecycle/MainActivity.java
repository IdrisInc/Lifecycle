package com.fao.lifyecycle;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;
    private EditText nameEditText, hobby1EditText, hobby2EditText;
    private Button saveButton, viewRecordsButton;
    private static final String TAG = "ActivityLifecycle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate called");

        dbHelper = new MyDatabaseHelper(this);
        nameEditText = findViewById(R.id.nameEditText);
        hobby1EditText = findViewById(R.id.hobby1EditText);
        hobby2EditText = findViewById(R.id.hobby2EditText);
        saveButton = findViewById(R.id.saveButton);
        viewRecordsButton = findViewById(R.id.viewRecordsButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String hobby1 = hobby1EditText.getText().toString();
                String hobby2 = hobby2EditText.getText().toString();
                String hobbies = hobby1 + "," + hobby2;
                saveToDatabase(name, hobbies);
            }
        });

        viewRecordsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Records.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy called");
    }

    private void saveToDatabase(String name, String hobbies) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("hobbies", hobbies);
        db.insert("users", null, values);
        db.close();
    }
}
