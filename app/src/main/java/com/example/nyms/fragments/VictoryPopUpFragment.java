package com.example.nyms.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.nyms.R;
import com.example.nyms.models.VictoryInfoModel;

public class VictoryPopUpFragment extends DialogFragment {

    TextView word;
    TextView timeTaken;
    TextView numOfGuesses;
    TextView streak;
    TextView definition;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.victory_popup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        word = (TextView) getView().findViewById(R.id.secretWord_victory);
        timeTaken = (TextView) getView().findViewById(R.id.time_taken);
        timeTaken = (TextView) getView().findViewById(R.id.time_taken);
        numOfGuesses = (TextView) getView().findViewById(R.id.numOfGuesses_victory);
        streak = (TextView) getView().findViewById(R.id.currentStreak);
        definition = (TextView) getView().findViewById(R.id.definition);


        Bundle bundle = getArguments();
        if(bundle != null) {
            VictoryInfoModel victoryInfo = bundle.getParcelable("victoryInfo");

            word.setText(victoryInfo.getWordGuess());
            numOfGuesses.setText(victoryInfo.getNumOfGuesses().toString());
            if(victoryInfo.getDefinitions() != null) definition.setText(victoryInfo.getDefinitions().get(0));
        }
        onDismiss();
    }

    private void onDismiss() {
        newGame();
    }

    private void newGame() {
        GameFragment gameFragment = new GameFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentFrameLayout, gameFragment)
                .addToBackStack(null)
                .commit();
    }
}