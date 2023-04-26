package com.example.myapp7;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapp7.adapters.ItemRecyclerAdapter;

import java.util.List;


public class CollectionFragment extends Fragment {

    private List<Item> items;
    private AccountViewModel accountViewModel;
    private ItemsViewModel itemsViewModel;

    public CollectionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accountViewModel = ((MainActivity)requireActivity())
                .getViewModelProvider()
                .get(AccountViewModel.class);
        itemsViewModel = ((MainActivity)requireActivity())
                .getViewModelProvider()
                .get(ItemsViewModel.class);
        items = Repository.getFumosByLogin(accountViewModel.getUser().getValue().getLogin());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_collection, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.itemsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //int space = getResources().getDimensionPixelSize(R.dimen.spacing);
        //recyclerView.addItemDecoration(new SpaceItemDecoration(space));
        ItemRecyclerAdapter adapter = new ItemRecyclerAdapter(items);
        recyclerView.setAdapter(adapter);
    }
}