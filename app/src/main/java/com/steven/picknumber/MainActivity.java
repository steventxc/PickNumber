package com.steven.picknumber;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.mikepenz.fastadapter.adapters.FastItemAdapter;
import com.mikepenz.fastadapter_extensions.items.SingleLineItem;

import static com.steven.picknumber.NumberFormat.TOTAL_NUMBER_SIZE;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "PICK_NUMBER";

    private EditText numberText, alphabetText;
    private Spinner formatSpinner;

    private FastItemAdapter<SingleLineItem> fastItemAdapter;

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
                onSubmitClicked(view);
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

        //
        fastItemAdapter = new FastItemAdapter();

        RecyclerView resultList = (RecyclerView) findViewById(R.id.result_list);
        resultList.setLayoutManager(new LinearLayoutManager(this));
        resultList.setAdapter(fastItemAdapter);
    }

    public void onSubmitClicked(View view) {
        NumberFormat numberFormat = NumberFormat.getInstance();

        numberText.clearFocus();
        alphabetText.clearFocus();
        // 隐藏软键盘
        // TODO: 16/11/3  

        // 数字
        String digit = numberText.getText().toString();
        if (digit.length() > numberFormat.getMaxDigitSize()) {
            String msg = String.format("数字最多不能超过%d位", numberFormat.getMaxDigitSize());
            new MaterialDialog.Builder(this).
                    content(msg).positiveText("确定").show();
            return;

        } else if (digit.length() < numberFormat.getMinDigitSize()) {
            String msg = String.format("数字最少不能小于%d位", numberFormat.getMinDigitSize());
            new MaterialDialog.Builder(this).
                    content(msg).positiveText("确定").show();
            return;
        }

        int availableAlphSize = NumberFormat.TOTAL_NUMBER_SIZE - digit.length();

        // 字母
        String alphabet = alphabetText.getText().toString();
        if (alphabet.length() != availableAlphSize) {
            String msg = String.format("还需要%d个字母", availableAlphSize);
            new MaterialDialog.Builder(this).
                    content(msg).positiveText("确定").show();
            return;
        }

        alphabet = alphabet.toLowerCase();
        if (alphabet.contains("i") || alphabet.contains("o")) {
            new MaterialDialog.Builder(this).
                    content("只能选择除\"I、O\"以外的24个字母").positiveText("确定").show();
            return;
        }

        int formatIdx = formatSpinner.getSelectedItemPosition();
        if (formatIdx == 0) {
            // 尝试所有可能
        } else {
            String format = formatSpinner.getSelectedItem().toString();

        }

        for (int i = 0; i<11; i++) {
            SingleLineItem item = new SingleLineItem().withName("" + i);
            fastItemAdapter.add(item);
        }
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
