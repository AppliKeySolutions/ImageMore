package com.applikeysolutions.imagemore.example;

import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.applikeysolutions.imagemore.ImageMoreAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends BaseAdapter implements ImageMoreAdapter {

    private static final String TAG = "Adapter";
    private final List<Item> items;

    public Adapter() {items = new ArrayList<>();}

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Item getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    @SuppressWarnings("ViewHolder")
    public View getView(int position, View convertView, ViewGroup parent) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View root = inflater.inflate(R.layout.view_item, parent, false);
        final ImageView view = (ImageView) root.findViewById(R.id.image);
        Picasso.with(parent.getContext())
                .load(items.get(position).getUrl())
                .transform(new PicassoCircularTransformation())
                .into(view);
        return root;
    }

    public void update(List<Item> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void add(Item item) {
        this.items.add(item);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        if (this.items.size() <= position) {
            return;
        }
        this.items.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public View getMoreView(int moreItemsCount, View convertView, ViewGroup parent) {
        final View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_counter, parent, false);
        final TextView view = (TextView) root.findViewById(R.id.text);
        view.setBackground(ContextCompat.getDrawable(parent.getContext(), R.drawable.round_counter));
        view.setText(String.valueOf(moreItemsCount));
        return root;
    }
}
