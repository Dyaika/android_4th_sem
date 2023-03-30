package com.example.myapp4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapp4.adapters.MyRecyclerAdapter;
import com.example.myapp4.pojo.Item;

import java.util.ArrayList;
import java.util.List;


public class RecyclerViewFragment extends Fragment {

    public RecyclerViewFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < 200; i++){
            items.add(new Item(R.drawable.ic_launcher_background, "Элемент " + (i + 1)));
        }
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.itemsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        int space = getResources().getDimensionPixelSize(R.dimen.spacing);
        recyclerView.addItemDecoration(new SpaceItemDecoration(space));
        MyRecyclerAdapter adapter = new MyRecyclerAdapter(items);
        recyclerView.setAdapter(adapter);
        return view;
    }
}