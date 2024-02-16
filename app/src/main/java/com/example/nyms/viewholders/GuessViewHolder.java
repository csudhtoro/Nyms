package com.example.nyms.viewholders;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.akexorcist.roundcornerprogressbar.TextRoundCornerProgressBar;
import com.example.nyms.R;
import com.example.nyms.adapters.OnGuessListener;
import com.example.nyms.models.GuessModel;
import com.example.nyms.viewmodels.WordListViewModel;


public class GuessViewHolder extends RecyclerView.ViewHolder {

    //Widgets
    //public TextView guessNumber;
    public TextView wordHint;
    public TextRoundCornerProgressBar guessProgressBar;
    public ConstraintLayout expandableLayout;

    public TextView guessName;

    public TextView guessScore;

    //click listener
    OnGuessListener onGuessListener;


    public GuessViewHolder(@NonNull View itemView) {
        super(itemView);

        // = itemView.findViewById(R.id.guess_number);
        guessName = itemView.findViewById(R.id.guess_name);
        guessProgressBar = itemView.findViewById(R.id.score_progressbar);
        guessScore = itemView.findViewById(R.id.guess_score);
        expandableLayout = itemView.findViewById(R.id.expandableLayout);
        wordHint = itemView.findViewById(R.id.wordHint);

    }


}
