package com.wmii.kgm.calculatored;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.wmii.kgm.calculatored.db.DatabaseHelper;

public class resultActivity extends AppCompatActivity {

    DatabaseHelper DbHelper;
    StringBuilder  builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXPRESSION_RESULT);

        getDbHistory();

        setTextResult(builder, message);
    }

    private void getDbHistory(){
        DbHelper = new DatabaseHelper(this);
        Cursor cursor = DbHelper.getAllData();

        if(cursor.getCount() == 0) { return; }

        builder = new StringBuilder();

        cursorGetData(cursor, builder);
    }

    private void cursorGetData(Cursor cursor, StringBuilder builder) {

        while(cursor.moveToNext())
        {
            builder.append(cursor.getString(1)).append("\n");
        }
    }

    private void setTextResult(StringBuilder builder, String message) {
        TextView historyTextView = findViewById(R.id.textViewHistory);
        historyTextView.setMovementMethod(new ScrollingMovementMethod());
        historyTextView.append(builder.toString());

        TextView resultView = findViewById(R.id.textViewResult);
        resultView.setText(message);
    }

}
