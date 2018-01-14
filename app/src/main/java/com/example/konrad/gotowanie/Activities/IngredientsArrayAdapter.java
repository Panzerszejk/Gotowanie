package com.example.konrad.gotowanie.Activities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.konrad.gotowanie.R;

import java.util.ArrayList;


public class IngredientsArrayAdapter extends ArrayAdapter<String> {
    private Activity context;
    private ArrayList<ArrayList<String>> prodArray;

    private SharedPreferences ingName;
    private SharedPreferences ingPrefCount;
    private static final String PREF_NAME = "prefName";
    private static final String PREF_COUNT = "prefCount";

    public IngredientsArrayAdapter(Activity context,ArrayList<ArrayList<String>> prodArray) {
        super(context, R.layout.list_element,prodArray.get(0)); //mozliwe ze tu
        this.context = context;
        this.prodArray = prodArray;
        ingName = this.context.getSharedPreferences(PREF_NAME , Activity.MODE_PRIVATE);
        ingPrefCount = this.context.getSharedPreferences(PREF_COUNT , Activity.MODE_PRIVATE);
    }

    @Override
    public View getView(int position,View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View rowView =  layoutInflater.inflate(R.layout.list_element, null, true);

        TextView ingList =  rowView.findViewById(R.id.tekstLista);
        TextView ingCount =  rowView.findViewById(R.id.tekstLiczba);

        ingList.setText(prodArray.get(0).get(position));
        ingCount.setText(prodArray.get(1).get(position));

        return rowView;
    }
}
