package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mylauncher.R;

import java.util.List;

public class AppAdapter extends ArrayAdapter<ApplicationInfo> {
    public AppAdapter(
            @NonNull Context context,
            int resource,
            @NonNull List<ApplicationInfo> objects
    ) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(
            int position,
            @Nullable View convertView,
            @NonNull ViewGroup parent
    ) {
        final LayoutInflater inflater = LayoutInflater.from(getContext());

        @SuppressLint("ViewHolder") final View view = inflater.inflate(
                com.example.mylauncher.R.layout.item_lista,
                parent,
                false
        );

        final ApplicationInfo app = getItem(position);

        if (app != null) {
            final TextView text = view.findViewById(com.example.mylauncher.R.id.app_name);
            final ImageView image = view.findViewById(com.example.mylauncher.R.id.app_icon);

            text.setText(app.loadLabel(getContext().getPackageManager()));
            image.setImageDrawable(app.loadIcon(getContext().getPackageManager()));
        }

        return view;
    }
}
