package com.example.nyms.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.akexorcist.roundcornerprogressbar.TextRoundCornerProgressBar;
import com.example.nyms.R;

public class LastGuessViewHolder extends RecyclerView.ViewHolder {
    //Widgets
    public TextView guessNumber;
    public TextView guessName;
    public TextView lastGuessScore;
    public TextRoundCornerProgressBar guessProgressBar;


    public LastGuessViewHolder(@NonNull View itemView) {
        super(itemView);

        lastGuessScore = itemView.findViewById(R.id.last_guess_score);
        guessName = itemView.findViewById(R.id.last_guess_name);
        guessProgressBar = itemView.findViewById(R.id.score_progressbar);
    }
}
