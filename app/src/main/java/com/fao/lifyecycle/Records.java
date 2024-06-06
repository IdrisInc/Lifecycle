package com.fao.lifyecycle;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Records extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;
    private static final String TAG = "ActivityLifecycle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        Log.d(TAG, "onCreate called");

        dbHelper = new MyDatabaseHelper(this);
        TableLayout tableLayout = findViewById(R.id.tableLayout);
        displayRecords(tableLayout);
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

    private void displayRecords(TableLayout tableLayout) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("users", new String[]{"id", "name", "hobbies"}, null, null, null, null, null);

        while (cursor.moveToNext()) {
            TableRow row = new TableRow(this);

            TextView idTextView = new TextView(this);
            idTextView.setText(String.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow("id"))));
            row.addView(idTextView);

            TextView nameTextView = new TextView(this);
            nameTextView.setText(cursor.getString(cursor.getColumnIndexOrThrow("name")));
            row.addView(nameTextView);

            TextView hobbiesTextView = new TextView(this);
            hobbiesTextView.setText(cursor.getString(cursor.getColumnIndexOrThrow("hobbies")));
            row.addView(hobbiesTextView);

            tableLayout.addView(row);
        }
        cursor.close();
        db.close();
    }
}
