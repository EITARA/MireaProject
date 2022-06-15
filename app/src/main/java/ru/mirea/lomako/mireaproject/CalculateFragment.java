package ru.mirea.lomako.mireaproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalculateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalculateFragment extends Fragment {
    private double valueOne = Double.NaN;
    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION = '/';
    private char CURRENT_ACTION;
    private double valueTwo;
    Button one;
    Button two;
    Button three;
    Button four;
    Button five;
    Button six;
    Button seven;
    Button eight;
    Button nine;
    Button zero;
    Button buttonSubtract;
    Button buttonMultiply;
    Button buttonDivide;
    EditText editText;
    Button buttonAdd;
    TextView infoTextView;
    Button buttonEqual;
    Button buttonClear;
    Button buttonDot;
    // TODO: Rename parameter arguments, choose names that match
    private String mParam1;
    private String mParam2;

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    public CalculateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalculateFragment.
     */
    // TODO: Rename and change types and number of parameters
  public static CalculateFragment newInstance(String param1, String param2) {
        CalculateFragment fragment = new CalculateFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_calculate, null);
        zero=v.findViewById(R.id.buttonZero);
        one=v.findViewById(R.id.buttonOne);
        two=v.findViewById(R.id.buttonTwo);
        three=v.findViewById(R.id.buttonThree);
        four=v.findViewById(R.id.buttonFour);
        five=v.findViewById(R.id.buttonFive);
        six=v.findViewById(R.id.buttonSix);
        seven=v.findViewById(R.id.buttonSeven);
        eight=v.findViewById(R.id.buttonEight);
        nine=v.findViewById(R.id.buttonNine);
        buttonSubtract=v.findViewById(R.id.buttonSubtract);
        buttonMultiply=v.findViewById(R.id.buttonMultiply);
        buttonDivide=v.findViewById(R.id.buttonDivide);
        buttonAdd=v.findViewById(R.id.buttonAdd);
        buttonEqual=v.findViewById(R.id.buttonEqual);
        editText=v.findViewById(R.id.editText);
        infoTextView=v.findViewById(R.id.infoTextView);
        buttonClear=v.findViewById(R.id.buttonClear);
        buttonDot=v.findViewById(R.id.buttonDot);
        DecimalFormat decimalFormat = new DecimalFormat("#.##########");
        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(editText.getText() + ".");
            }
        });
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(editText.getText() + "0");
            }
        });
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(editText.getText() + "1");
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(editText.getText() + "2");
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(editText.getText() + "3");
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(editText.getText() + "4");
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(editText.getText() + "5");
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(editText.getText() + "6");
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(editText.getText() + "7");
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(editText.getText() + "8");
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(editText.getText() + "9");
            }
        });
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();
                CURRENT_ACTION = ADDITION;
                infoTextView.setText(decimalFormat.format(valueOne) + "+");
                editText.setText(null);
            }
        });
        buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();
                CURRENT_ACTION = SUBTRACTION;
                infoTextView.setText(decimalFormat.format(valueOne) + "-");
                editText.setText(null);
            }
        });
        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();
                CURRENT_ACTION = MULTIPLICATION;
                infoTextView.setText(decimalFormat.format(valueOne) + "*");
                editText.setText(null);
            }
        });
        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();
                CURRENT_ACTION = DIVISION;
                infoTextView.setText(decimalFormat.format(valueOne) + "/");
                editText.setText(null);
            }
        });
        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();
                infoTextView.setText(infoTextView.getText().toString() +
                        decimalFormat.format(valueTwo) + " = " + decimalFormat.format(valueOne));
                valueOne = Double.NaN;
                CURRENT_ACTION = '0';
            }
        });
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoTextView.setText(null);
                editText.setText(null);
            }
        });
        return v;
    }
    private void computeCalculation() {
        if(!Double.isNaN(valueOne)) {
            valueTwo = Double.parseDouble(editText.getText().toString());
            editText.setText(null);
            if(CURRENT_ACTION == ADDITION)
                valueOne = this.valueOne + valueTwo;
            else if(CURRENT_ACTION == SUBTRACTION)
                valueOne = this.valueOne - valueTwo;
            else if(CURRENT_ACTION == MULTIPLICATION)
                valueOne = this.valueOne * valueTwo;
            else if(CURRENT_ACTION == DIVISION)
                valueOne = this.valueOne / valueTwo;
        }
        else {
            try {
                valueOne = Double.parseDouble(editText.getText().toString());
            }
            catch (Exception e){}
        }
    }}

