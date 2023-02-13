package com.example.myapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toast = Toast.makeText(this, "ON CREATE", Toast.LENGTH_SHORT);
        toast.show();
        Log.d("lifecycle","ON CREATE");
    }

    @Override
    protected void onStart() {
        super.onStart();

        toast = Toast.makeText(this, "ON START", Toast.LENGTH_SHORT);
        toast.show();
        Log.d("lifecycle","ON START");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        toast = Toast.makeText(this, "ON RESTART", Toast.LENGTH_SHORT);
        toast.show();
        Log.w("lifecycle","ON RESTART");
    }

    @Override
    protected void onResume() {
        super.onResume();

        toast = Toast.makeText(this, "ON RESUME", Toast.LENGTH_SHORT);
        toast.show();
        Log.v("lifecycle","ON RESUME");
    }

    @Override
    protected void onPause() {
        super.onPause();
        toast = Toast.makeText(this, "ON PAUSE", Toast.LENGTH_SHORT);
        toast.show();
        Log.i("lifecycle","ON PAUSE");
    }

    @Override
    protected void onStop() {
        super.onStop();
        toast = Toast.makeText(this, "ON STOP", Toast.LENGTH_SHORT);
        toast.show();
        Log.e("lifecycle","ON STOP");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        toast = Toast.makeText(this, "ON DESTROY", Toast.LENGTH_SHORT);
        toast.show();
        Log.wtf("lifecycle","ON DESTROY");
    }
}