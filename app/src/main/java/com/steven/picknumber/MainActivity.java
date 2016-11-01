package com.steven.picknumber;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "PICK_NUMBER";

    private EditText numberText, alphabetText;
    private Spinner formatSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        NumberFormat numberFormat = NumberFormat.getInstance();
        Log.d(TAG, "getMinDigitSize: " + numberFormat.getMinDigitSize());
        Log.d(TAG, "getMaxDigitSize: " + numberFormat.getMaxDigitSize());
        Log.d(TAG, "getMinAlphabeticSize: " + numberFormat.getMinAlphabeticSize());
        Log.d(TAG, "getMaxAlphabeticSize: " + numberFormat.getMaxAlphabeticSize());

        String tip = String.format("在目前可自选的号牌格式中你必须选择 ( %d - %d ) 个字母，同时选择 ( %d - %d ) 个数字。",
                numberFormat.getMinAlphabeticSize(), numberFormat.getMaxAlphabeticSize(),
                numberFormat.getMinDigitSize(), numberFormat.getMaxDigitSize());

        TextView tips = (TextView) findViewById(R.id.number_tip);
        tips.setText(tip);

        //
        numberText = (EditText) findViewById(R.id.number_pick);
        alphabetText = (EditText) findViewById(R.id.alphabet_pick);

        //
        formatSpinner = (Spinner) findViewById(R.id.spinner_format);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapter.add("尝试所有格式");
        adapter.addAll(NumberFormat.AVAILABLE_RESOURCES_FORMAT);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        formatSpinner.setAdapter(adapter);

        formatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void onSubmitClicked(View view) {
        String digit = numberText.getText().toString();
        String alphabet = alphabetText.getText().toString();
        String format = formatSpinner.getSelectedItem().toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
