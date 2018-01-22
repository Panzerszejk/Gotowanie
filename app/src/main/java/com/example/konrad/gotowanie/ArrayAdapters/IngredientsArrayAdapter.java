package com.example.konrad.gotowanie.ArrayAdapters;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.konrad.gotowanie.R;

import java.util.ArrayList;


public class IngredientsArrayAdapter extends ArrayAdapter<String> {
    private Activity context;
    private ArrayList<ArrayList<String>> prodArray;

    public IngredientsArrayAdapter(Activity context,ArrayList<ArrayList<String>> prodArray) {
        super(context, R.layout.list_element,prodArray.get(0)); //mozliwe ze tu
        this.context = context;
        this.prodArray = prodArray;
    }

    static class ViewHolder {
        public TextView ingList;
        public TextView ingCount;
        public Button ingDelete;
    }

    @Override
    public View getView(final int position,View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        View rowView = convertView;
        if(rowView == null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            rowView =  layoutInflater.inflate(R.layout.list_element, null, true);
            viewHolder = new ViewHolder();
            viewHolder.ingList =  rowView.findViewById(R.id.tekstLista);
            viewHolder.ingCount =  rowView.findViewById(R.id.tekstLiczba);
            viewHolder.ingDelete =  rowView.findViewById(R.id.przyciskLista);
            rowView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) rowView.getTag();
        }
        viewHolder.ingList.setText(prodArray.get(0).get(position));
        viewHolder.ingCount.setText(prodArray.get(1).get(position));

        viewHolder.ingDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                prodArray.get(0).remove(position);
                prodArray.get(1).remove(position); //z shared prefs trzeba usunac i z bazy
                notifyDataSetChanged();
            }
        });


        return rowView;
    }
}
