package com.wmii.kgm.calculatored;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wmii.kgm.calculatored.db.DatabaseHelper;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {
    public static final String EXPRESSION_RESULT = "com.wmii.kgm.calculatored.MESSAGE";

    DatabaseHelper DbHelper;

    private TextView textMathExpression;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DbHelper = new DatabaseHelper(this);
        setContentView(R.layout.activity_main);
        textMathExpression = findViewById(R.id.editTextMathExpression);
    }

    public void sendResult(View view) {
        Intent intent = new Intent(this, resultActivity.class);
        EditText resultText = (EditText) findViewById(R.id.editTextMathExpression);

        String expressionString = resultText.getText().toString();
        DbHelper.insertData(expressionString);

        String resultString = mathCreate(expressionString);
        intent.putExtra(EXPRESSION_RESULT, resultString);
        startActivity(intent);
    }

    private String mathCreate(String expressionString) {
        Expression expression = new ExpressionBuilder(expressionString).build();

        return ((Double) expression.evaluate()).toString();
    }

    public void ClearHistory(View view) {
        DbHelper.DeleteData();

        Toast.makeText(MainActivity.this, "History cleared", Toast.LENGTH_LONG).show();
    }

    // Button Append Methods

    public void ButtonMath1OnClick(View view)        { textMathExpression.append("1"); }
    public void ButtonMath2OnClick(View view)        { textMathExpression.append("2"); }
    public void ButtonMath3OnClick(View view)        { textMathExpression.append("3"); }
    public void ButtonMath4OnClick(View view)        { textMathExpression.append("4"); }
    public void ButtonMath5OnClick(View view)        { textMathExpression.append("5"); }
    public void ButtonMath6OnClick(View view)        { textMathExpression.append("6"); }
    public void ButtonMath7OnClick(View view)        { textMathExpression.append("7"); }
    public void ButtonMath8OnClick(View view)        { textMathExpression.append("8"); }
    public void ButtonMath9OnClick(View view)        { textMathExpression.append("9"); }
    public void ButtonMath0OnClick(View view)        { textMathExpression.append("0"); }
    public void ButtonMathDotOnClick(View view)      { textMathExpression.append("."); }
    public void ButtonMathPlusOnClick(View view)     { textMathExpression.append("+"); }
    public void ButtonMathMinusOnClick(View view)    { textMathExpression.append("-"); }
    public void ButtonMathMultiplyOnClick(View view) { textMathExpression.append("*"); }
    public void ButtonMathDivideOnClick(View view)   { textMathExpression.append("/"); }
    public void ButtonMathPercentOnClick(View view)  { textMathExpression.append("%"); }
    public void ButtonMathClearOnClick(View view)    { textMathExpression.setText(""); }

}


