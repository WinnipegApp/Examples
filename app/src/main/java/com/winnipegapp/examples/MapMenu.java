package com.winnipegapp.examples;

/**
 * Created by Mauricio on 2016-07-22.
 */

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
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
    private static CharSequence[] items = { "Pools", "Golf Courses", "Libraries", "Emergency Rooms" };


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        initialiseData();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Map Filters").setMultiChoiceItems(items, selectedFilters, new DialogInterface.OnMultiChoiceClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int position, boolean isChecked) {

                if (isChecked) {
                    selectedFilters[position] = true;
                }else {
                    selectedFilters[position] = false;
                }
            }

        })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int position) {

                        storeUserFilters("selectedFilters", getActivity());
                        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, null);

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

        if (selectedFilters == null || selectedFilters.length == 0) {

            selectedFilters = new boolean[items.length];

            Arrays.fill(selectedFilters, true);

        }
        loadUserFilters("selectedFilters", getActivity());


    }

    public boolean storeUserFilters(String arrayName, Context mContext) {

        SharedPreferences sharedPreferences = mContext.getSharedPreferences("selectedFilters",0);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.clear().commit();

        editor.putInt(arrayName +"_size", selectedFilters.length);

        for (int i = 0; i < selectedFilters.length; i++)
            editor.putBoolean(arrayName + "_" + i, selectedFilters[i]);

        return editor.commit();

    }

    public void loadUserFilters(String arrayName, Context mContext) {

        SharedPreferences sharedPreferences = mContext.getSharedPreferences("selectedFilters", 0);


        for (int i = 0; i < selectedFilters.length; i++)
            selectedFilters[i] = sharedPreferences.getBoolean(arrayName + "_" + i, true);

    }
    public static boolean[] getSelectedFilters (Context context)
    {
        boolean[] selectedFilters = new boolean[items.length];
        SharedPreferences sharedPreferences = context.getSharedPreferences("selectedFilters", 0);


        for (int i = 0; i < selectedFilters.length; i++)
            selectedFilters[i] = sharedPreferences.getBoolean("selectedFilters_" + i, true);

        return selectedFilters;
    }
}
