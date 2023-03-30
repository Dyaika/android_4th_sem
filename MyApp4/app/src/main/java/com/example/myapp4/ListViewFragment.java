package com.example.myapp4;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapp4.adapters.MyArrayAdapter;
import com.example.myapp4.databinding.FragmentListViewBinding;
import com.example.myapp4.pojo.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ListViewFragment extends Fragment {


    private static final String TAG = "listfragment";

    public ListViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_view, container, false);
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < 200; i++){
            items.add(new Item(R.drawable.ic_launcher_background, "Элемент " + (i + 1)));
        }

        ListView itemsList = view.findViewById(R.id.itemsList);
        ArrayAdapter<Item> adapter = new MyArrayAdapter(getActivity(), R.layout.list_item, items);
        itemsList.setAdapter(adapter);
        itemsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), "" + l, Toast.LENGTH_SHORT).show();
                Log.i(TAG, l + "");
            }
        });
        return view;
    }
}