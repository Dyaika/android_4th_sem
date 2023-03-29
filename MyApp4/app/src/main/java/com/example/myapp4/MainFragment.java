package com.example.myapp4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class MainFragment extends Fragment {

    private static final String TAG = "mainfragment";

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        Button listBtn =  view.findViewById(R.id.button_list);
        listBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment listfrag = new ListViewFragment();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_view, listfrag)
                        .addToBackStack(null)
                        .commit();
            }
        });
        Button recycBtn =  view.findViewById(R.id.button_recycler);
        recycBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "to recycler", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}