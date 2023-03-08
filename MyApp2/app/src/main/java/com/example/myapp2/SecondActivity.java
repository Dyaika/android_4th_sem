package com.example.myapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "activity_second";
    private Button button;
    private EditText editText;
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        editText = (EditText)findViewById(R.id.input);
        editText.setBackgroundColor(getResources().getColor(R.color.cirno));
        editText.setTextColor(getResources().getColor(R.color.white));
        editText.setText(getIntent().getExtras().get("text").toString());
        button = (Button)findViewById(R.id.button);
    }
    public void onButtonClick(View view){
        Log.d(TAG, editText.getText().toString().replace('\n', ' '));
        Toast toast = Toast.makeText(context, editText.getText().toString().replace('\n', ' '), Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onBackPressed() {
        Intent data = new Intent();
        data.putExtra("text_back", editText.getText().toString());
        setResult(RESULT_OK, data);
        finish();
    }
}