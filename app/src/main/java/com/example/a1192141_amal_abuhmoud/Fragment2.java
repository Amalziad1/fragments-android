package com.example.a1192141_amal_abuhmoud;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment2 extends Fragment {
    private double inputValue;
    private double value;
    private TextView resultText;

    public Fragment2() {
        // Required empty public constructor
    }

    public static Fragment2 newInstance(double inputValue,double value) {
        Fragment2 fragment = new Fragment2();
        Bundle args = new Bundle();
        args.putDouble("inputValue", inputValue);
        args.putDouble("value", value);//will save the state of increment and decrement value, should be 0 at the beginning
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            inputValue = getArguments().getDouble("inputValue");
            value = getArguments().getDouble("value");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2, container, false);
        resultText = view.findViewById(R.id.result);
        Button incrementButton = view.findViewById(R.id.increment);
        Button decrementButton = view.findViewById(R.id.decrement);
        double result=inputValue * inputValue;//square the input value
        resultText.setText("Square: " + result);
        Fragment1 fragment1 = (Fragment1) getParentFragmentManager().findFragmentByTag("FirstFrag");
        value = result;
        incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputValue!=-1) {
                    value++;
                    fragment1.updateResult(value);//update result in fragment 1
                } else {
                    Toast.makeText(getActivity(), "Please enter an input number", Toast.LENGTH_SHORT).show();
                }
            }
        });
        decrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputValue!=-1) {
                    value--;
                    fragment1.updateResult(value); // Update with your calculation
                } else {
                    Toast.makeText(getActivity(), "Please enter an input number", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}