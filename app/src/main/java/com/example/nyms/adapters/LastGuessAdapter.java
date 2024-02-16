package com.example.nyms.adapters;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nyms.R;
import com.example.nyms.models.GuessModel;
import com.example.nyms.viewholders.GuessViewHolder;
import com.example.nyms.viewholders.LastGuessViewHolder;

public class LastGuessAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private GuessModel lastGuess;
    private RecyclerView lastGuessRecyclerView;

    public LastGuessAdapter(GuessModel lastGuess, RecyclerView lastGuessRecyclerView) {
        this.lastGuess = lastGuess;
        this.lastGuessRecyclerView = lastGuessRecyclerView;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.last_guess_card_item, parent, false);
        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.last_guess_card_item_hint, parent, false);

        if(lastGuess.getExpanded() == false) {
            return new LastGuessViewHolder(view);
        }
        else return new LastGuessViewHolder(view2);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((LastGuessViewHolder)holder).guessProgressBar.setProgress((int)(lastGuess.getGuessScore()));
        ((LastGuessViewHolder)holder).guessName.setText(lastGuess.getGuessName());


        String lastGuessScore = String.format("%.2f", lastGuess.getGuessScore());
        ((LastGuessViewHolder)holder).lastGuessScore.setText(lastGuessScore);

        if(lastGuess.getGuessScore() >= 0 && lastGuess.getGuessScore() <= 10) {
            ((LastGuessViewHolder)holder).guessProgressBar.setProgressColor(Color.argb(140,255,0, 0));
        }
        else if(lastGuess.getGuessScore() >= 10 && lastGuess.getGuessScore() <= 20) {
            ((LastGuessViewHolder)holder).guessProgressBar.setProgressColor(Color.argb(140,255,51,0));
        }
        else if(lastGuess.getGuessScore() > 20 && lastGuess.getGuessScore() <= 30) {
            ((LastGuessViewHolder)holder).guessProgressBar.setProgressColor(Color.argb(140,255,102,0));
        }
        else if(lastGuess.getGuessScore() > 30 && lastGuess.getGuessScore() <= 40) {
            ((LastGuessViewHolder)holder).guessProgressBar.setProgressColor(Color.argb(140,255,153,0));
        }
        else if(lastGuess.getGuessScore() > 40 && lastGuess.getGuessScore() <= 50) {
            ((LastGuessViewHolder)holder).guessProgressBar.setProgressColor(Color.argb(140,255,204,0));
        }
        else if(lastGuess.getGuessScore() > 50 && lastGuess.getGuessScore() <= 60) {
            ((LastGuessViewHolder)holder).guessProgressBar.setProgressColor(Color.argb(140,255,255,0));
        }
        else if(lastGuess.getGuessScore() > 60 && lastGuess.getGuessScore() <= 70) {
            ((LastGuessViewHolder)holder).guessProgressBar.setProgressColor(Color.argb(140,204,255,0));
        }
        else if(lastGuess.getGuessScore() > 70 && lastGuess.getGuessScore() <= 80) {
            ((LastGuessViewHolder)holder).guessProgressBar.setProgressColor(Color.argb(140,153,255,0));
        }
        else if(lastGuess.getGuessScore() > 80 && lastGuess.getGuessScore() <= 90) {
            ((LastGuessViewHolder)holder).guessProgressBar.setProgressColor(Color.argb(140,102,255,0));
        }
        else ((LastGuessViewHolder)holder).guessProgressBar.setProgressColor(Color.argb(140,51,255,0));

        //((LastGuessViewHolder)holder).guessProgressBar.setProgressText(lastGuess.getGuessName());
        //((LastGuessViewHolder)holder).guessProgressBar.setTextProgressColor(Color.BLACK);
        //((LastGuessViewHolder)holder).guessProgressBar.setTextProgressSize(36);


    }

    @Override
    public int getItemCount() {
        if(lastGuess != null) return 1;
        return 0;
    }
}
