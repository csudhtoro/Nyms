package com.example.nyms.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.TransitionRes;
import androidx.recyclerview.widget.RecyclerView;

import com.akexorcist.roundcornerprogressbar.TextRoundCornerProgressBar;
import com.example.nyms.R;
import com.example.nyms.models.GuessModel;
import com.example.nyms.viewholders.GuessViewHolder;
import com.example.nyms.viewholders.LastGuessViewHolder;

import java.util.List;

public class GuessAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<GuessModel> mGuesses;
    private RecyclerView guessRecyclerView;
    private OnGuessListener onGuessListener;

    public GuessAdapter(List<GuessModel> mGuesses, RecyclerView guessRecyclerView) {
        this.mGuesses = mGuesses;
        this.guessRecyclerView = guessRecyclerView;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.guess_card_item, parent, false);

        return new GuessViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        //((GuessViewHolder)holder).guessNumber.setText(mGuesses.get(position).getGuessNumber());
        //((GuessViewHolder)holder).guessName.setText(mGuesses.get(position).getGuessName());
        ((GuessViewHolder)holder).guessProgressBar.setProgress((int)(mGuesses.get(position).getGuessScore()));
        ((GuessViewHolder)holder).wordHint.setText(mGuesses.get(position).getGuessRelation());
        ((GuessViewHolder)holder).guessName.setText((mGuesses.get(position).getGuessName()));

        String GuessScore = String.format("%.2f", mGuesses.get(position).getGuessScore());
        ((GuessViewHolder)holder).guessScore.setText(GuessScore);

        //progress bar settings
        if(mGuesses.get(position).getGuessScore() >= 0 && mGuesses.get(position).getGuessScore() <= 10) {
            ((GuessViewHolder)holder).guessProgressBar.setProgressColor(Color.argb(140,255,0,0));
        }
        else if(mGuesses.get(position).getGuessScore() >= 10 && mGuesses.get(position).getGuessScore() <= 20) {
            ((GuessViewHolder)holder).guessProgressBar.setProgressColor(Color.argb(140,255,51,0));
        }
        else if(mGuesses.get(position).getGuessScore() > 20 && mGuesses.get(position).getGuessScore() <= 30) {
            ((GuessViewHolder)holder).guessProgressBar.setProgressColor(Color.argb(140,255,102,0));
        }
        else if(mGuesses.get(position).getGuessScore() > 30 && mGuesses.get(position).getGuessScore() <= 40) {
            ((GuessViewHolder)holder).guessProgressBar.setProgressColor(Color.argb(140,255,153,0));
        }
        else if(mGuesses.get(position).getGuessScore() > 40 && mGuesses.get(position).getGuessScore() <= 50) {
            ((GuessViewHolder)holder).guessProgressBar.setProgressColor(Color.argb(140,255,204,0));
        }
        else if(mGuesses.get(position).getGuessScore() > 50 && mGuesses.get(position).getGuessScore() <= 60) {
            ((GuessViewHolder)holder).guessProgressBar.setProgressColor(Color.argb(140,255,255,0));
        }
        else if(mGuesses.get(position).getGuessScore() > 60 && mGuesses.get(position).getGuessScore() <= 70) {
            ((GuessViewHolder)holder).guessProgressBar.setProgressColor(Color.argb(140,204,255,0));
        }
        else if(mGuesses.get(position).getGuessScore() > 70 && mGuesses.get(position).getGuessScore() <= 80) {
            ((GuessViewHolder)holder).guessProgressBar.setProgressColor(Color.argb(140,153,255,0));
        }
        else if(mGuesses.get(position).getGuessScore() > 80 && mGuesses.get(position).getGuessScore() <= 90) {
            ((GuessViewHolder)holder).guessProgressBar.setProgressColor(Color.argb(140,102,255,0));
        }
        else ((GuessViewHolder)holder).guessProgressBar.setProgressColor(Color.argb(140,51,255,0));

        //Word settings
        //((GuessViewHolder)holder).guessProgressBar.setProgressText(mGuesses.get(position).getGuessName());
        //((GuessViewHolder)holder).guessProgressBar.setTextProgressColor(Color.BLACK);
        //((GuessViewHolder)holder).guessProgressBar.setTextProgressSize(36);


        //expandable recyclerview
        boolean isExpanded = mGuesses.get(position).getExpanded();
        ((GuessViewHolder)holder).expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        ((GuessViewHolder)holder).guessProgressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GuessModel currGuess = mGuesses.get(((GuessViewHolder)holder).getAdapterPosition());
                currGuess.setExpanded(!currGuess.getExpanded());
                notifyItemChanged((((GuessViewHolder)holder).getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        if(mGuesses != null) return mGuesses.size();
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if(mGuesses.get(position).getExpanded() == false) return 0;
        else return 1;
    }

    public GuessModel getGuess(int position) {
        return mGuesses.get(position);
    }
}
