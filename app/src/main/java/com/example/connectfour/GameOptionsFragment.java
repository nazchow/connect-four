package com.example.connectfour;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class GameOptionsFragment extends Fragment {

    private static final String PREFS_NAME = "gamePrefs";
    private static final String PREF_DIFFICULTY = "selectedDifficulty";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the fragment layout
        View view = inflater.inflate(R.layout.fragment_game_options, container, false);

        // Initialize SharedPreferences
        SharedPreferences sharedPref = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        int savedDifficulty = sharedPref.getInt(PREF_DIFFICULTY, R.id.easyButton);

        // Set the selected radio button based on saved difficulty level
        RadioGroup difficultyGroup = view.findViewById(R.id.difficultyRadioGroup);
        difficultyGroup.check(savedDifficulty);

        // Set up the click listener for radio buttons
        for (int i = 0; i < difficultyGroup.getChildCount(); i++) {
            View radioButton = difficultyGroup.getChildAt(i);
            radioButton.setOnClickListener(this::onLevelSelected);
        }

        return view;
    }

    // Method to handle level selection and save it to SharedPreferences
    private void onLevelSelected(View view) {
        int selectedLevelId = view.getId();

        // Save the selected level in SharedPreferences
        SharedPreferences sharedPref = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(PREF_DIFFICULTY, selectedLevelId);
        editor.apply();
    }
}



