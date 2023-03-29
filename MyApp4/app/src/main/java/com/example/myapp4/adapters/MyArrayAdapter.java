package com.example.myapp4.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.myapp4.R;
import com.example.myapp4.pojo.Item;

import java.util.List;

public class MyArrayAdapter extends ArrayAdapter<Item> {
    private LayoutInflater inflater;
    private int layout;
    private List<Item> items;
    public MyArrayAdapter(@NonNull Context context, int resource, List<Item> items) {
        super(context, resource, items);
        this.items = items;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    public View getView(int position, View convertView,
                        ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        Item item = getItem(position);

        ImageView iconView = convertView.findViewById(R.id.icon);
        iconView.setImageResource(item.getImg());

        TextView textView = convertView.findViewById(R.id.text);
        textView.setText(item.getText());

        return convertView;
    }
}
