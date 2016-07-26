package com.winnipegapp.examples;

/**
 * Created by Mauricio on 2016-07-22.
 */

import java.util.ArrayList;
import java.util.Arrays;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;

public class MapMenu extends DialogFragment {

    boolean[] selectedFilters;
    private CharSequence[] items = { "Emergency Rooms", "Pools", "Golf Courses", "Post Offices" };

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        initialiseData();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Map Filters").setMultiChoiceItems(items, selectedFilters, new DialogInterface.OnMultiChoiceClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int position, boolean isChecked) {

                                if (isChecked)

                                    selectedFilters[position] = true;

                                else

                                    selectedFilters[position] = false;

                            }

                        })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int position) {

                        storeUserFilters(selectedFilters, "selectedFilters", getActivity());

                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int position) {

                                dialog.dismiss();

                            }

                        });

        return builder.create();

    }

    public void initialiseData() {

        if (selectedFilters == null)

            selectedFilters = new boolean[items.length];

        loadUserFilters("selectedFilters", getActivity());

    }

    public boolean storeUserFilters(boolean[] array, String arrayName, Context mContext) {

        SharedPreferences sharedPreferences = mContext.getSharedPreferences("selectedFilters", 0);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.clear().commit();

        editor.putInt(arrayName +"_size", array.length);

        for (int i = 0; i < array.length; i++)

            editor.putBoolean(arrayName + "_" + i, array[i]);

        return editor.commit();

    }

    public boolean[] loadUserFilters(String arrayName, Context mContext) {

        SharedPreferences sharedPreferences = mContext.getSharedPreferences("selectedFilters", 0);

        int size = sharedPreferences.getInt(arrayName + "_size", 0);

        selectedFilters = new boolean[size];

        for (int i = 0; i < size; i++)

            selectedFilters[i] = sharedPreferences.getBoolean(arrayName + "_" + i, false);

        return selectedFilters;

    }

}
