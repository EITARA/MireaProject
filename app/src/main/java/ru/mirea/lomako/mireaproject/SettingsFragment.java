package ru.mirea.lomako.mireaproject;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {
    private EditText historyprofile;
    private Button save;
    private Switch switch1;
    private Boolean theme = false;
    private SharedPreferences preferences;
    final String SAVED_Theme = "saved_sett_theme";
    final String SAVED_Sett_Profile = "saved_sett_profile";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
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
        View v = inflater.inflate(R.layout.fragment_settings, null);
        preferences = getActivity().getPreferences(MODE_PRIVATE);
        historyprofile = v.findViewById(R.id.historyprofile);
        save = v.findViewById(R.id.SettSave);
        switch1 = v.findViewById(R.id.switch1);
        theme = preferences.getBoolean(SAVED_Theme,false);
        switch1.setChecked(true);

        onLoadText(v);
            switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if (isChecked) {
                   theme=true;

               } else {
                   theme=false;

               }
           } });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveText(v);
            }
        });

        return v;
        }
    public void onSaveText(View view) {
        SharedPreferences.Editor editor = preferences.edit();
        // Сохранение значения по ключу SAVED_TEXT
        editor.putBoolean(SAVED_Theme, theme);
        editor.putString(SAVED_Sett_Profile, historyprofile.getText().toString());
        editor.apply();
        ((MainActivity) getActivity()).ToggleTheme(theme);
        Toast.makeText(getActivity(), "Settings Saved", Toast.LENGTH_SHORT).show();
    }
    public void onLoadText(View view) {
        // Загрузка значения по ключу SAVED_TEXT
        switch1.setChecked(preferences.getBoolean(SAVED_Theme,false));
        String text2 = preferences.getString(SAVED_Sett_Profile, "Название профиля");
        historyprofile.setText(text2);
    }

    }
