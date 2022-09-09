package com.example.myapplicationwer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class OperacionAdapter extends BaseAdapter {
    //Atributos
    Context context;
    int imgOperaciones[];
    String[] nombreOperaciones;
    LayoutInflater inflter;

    public OperacionAdapter(Context applicationContext, int[] imgOperaciones, String[] nombreOperaciones) {
        this.context = applicationContext;
        this.imgOperaciones = imgOperaciones;
        this.nombreOperaciones = nombreOperaciones;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return imgOperaciones.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.item_operacion, null);
        ImageView icon = (ImageView) view.findViewById(R.id.imageView);
        TextView names = (TextView) view.findViewById(R.id.textView);
        icon.setImageResource(imgOperaciones[i]);
        names.setText(nombreOperaciones[i]);
        return view;
    }
}
