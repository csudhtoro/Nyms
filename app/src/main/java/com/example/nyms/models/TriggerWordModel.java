package com.example.nyms.models;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

public class TriggerWordModel implements Parcelable {

    private String word;
    private Integer score;

    protected TriggerWordModel(Parcel in) {
        word = in.readString();
        if (in.readByte() == 0) {
            score = null;
        } else {
            score = in.readInt();
        }
    }

    public static final Creator<TriggerWordModel> CREATOR = new Creator<TriggerWordModel>() {
        @Override
        public TriggerWordModel createFromParcel(Parcel in) {
            return new TriggerWordModel(in);
        }

        @Override
        public TriggerWordModel[] newArray(int size) {
            return new TriggerWordModel[size];
        }
    };

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(TriggerWordModel.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("word");
        sb.append('=');
        sb.append(((this.word == null)?"<null>":this.word));
        sb.append(',');
        sb.append("score");
        sb.append('=');
        sb.append(((this.score == null)?"<null>":this.score));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(word);
        if (score == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(score);
        }
    }
}