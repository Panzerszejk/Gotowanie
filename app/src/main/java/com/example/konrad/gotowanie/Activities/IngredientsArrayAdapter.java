package com.example.konrad.gotowanie.Activities;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.konrad.gotowanie.R;

import java.util.ArrayList;


public class IngredientsArrayAdapter extends ArrayAdapter<String> {
    private Activity context;
    private ArrayList<String> prodArray;
    //private static int counter;

    public IngredientsArrayAdapter(Activity context,ArrayList<String> prodArray) {
        super(context, R.layout.list_element, prodArray); //mozliwe ze tu
        this.context = context;
        this.prodArray = prodArray;
        Log.d("Dupa",prodArray.get(0));
        //counter=0;
    }

    @Override
    public View getView(int position,View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View rowView =  layoutInflater.inflate(R.layout.list_element, null, true);

        TextView tvLanguage =  rowView.findViewById(R.id.tekstLista);
        //Button tvItemNumber =  rowView.findViewById(R.id.przyciskLista);

        //tvItemNumber.setText(Integer.toString(position));
        tvLanguage.setText(prodArray.get(position));
        //counter ++;
        return rowView;
    }
}
