package com.example.myapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private Button button;
    private EditText editText;
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        editText = (EditText)findViewById(R.id.input);
        editText.setText(getIntent().getExtras().get("text").toString());
        button = (Button)findViewById(R.id.button);
    }
    public void onButtonClick(View view){
        Toast toast = Toast.makeText(context, editText.getText().toString().replace('\n', ' '), Toast.LENGTH_SHORT);
        toast.show();
    }
}