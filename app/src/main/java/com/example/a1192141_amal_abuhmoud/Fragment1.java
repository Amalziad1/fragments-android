package com.example.a1192141_amal_abuhmoud;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.view.inputmethod.InputMethodManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment1 extends Fragment {
    private TextView resultTextView;

    public Fragment1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_1, container, false);

        Button start = rootView.findViewById(R.id.buttonStart);
        EditText inputNumber = rootView.findViewById(R.id.NumberInputField);

        final FragmentManager fragmentManager =  getParentFragmentManager();
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = inputNumber.getText().toString();
                if (!inputText.isEmpty()) {
                    if (inputText.matches("^[0-9]+(\\.[0-9]+)?$")) { //matches numbers with decimal
                        try {
                            //to make hte keyboard go down if the button was clciked
                            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(inputNumber.getWindowToken(), 0);
                            double inputValue = Double.parseDouble(inputText);
                            Fragment2 fragmentTwo = (Fragment2) fragmentManager.findFragmentByTag("SecondFrag");
                            if (fragmentTwo == null) {//if fragment doesn't appear, add one
                                fragmentTwo = Fragment2.newInstance(inputValue,0);
                                fragmentManager.beginTransaction()
                                        .add(R.id.layout, fragmentTwo, "SecondFrag")
                                        .commit();
                            }else{//if it does exist, replace it with new fragment
                                resultTextView.setText("");
                                fragmentManager.beginTransaction()
                                        .remove(fragmentTwo)
                                        .commit();
                                fragmentTwo = Fragment2.newInstance(inputValue,0);
                                fragmentManager.beginTransaction()
                                        .add(R.id.layout, fragmentTwo, "SecondFrag")
                                        .commit();
                            }
                        } catch (NumberFormatException e) {
                            Toast.makeText(getActivity(), "Invalid number format", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getActivity(), "Please enter numbers only", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Please enter a number", Toast.LENGTH_SHORT).show();
                }
            }
        });
        resultTextView = rootView.findViewById(R.id.result2);
        return rootView;
    }
    public void updateResult(double value) {
        resultTextView.setText(String.valueOf(value));
    }
}
