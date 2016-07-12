package com.winnipegapp.examples;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class RTInquiryFragment extends Fragment {


    public RTInquiryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_rtinquiry, container, false);

        TextView textView = (TextView) rootView.findViewById(R.id.exTextView);

        Bundle bundle = this.getArguments();

        String serviceID = bundle.getString("RT", "default");

        if (bundle != null) {

            textView.setText(serviceID);

        }

        return rootView;
    }

}
