package com.example.nyms.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class FrequentFollowingWordsModel implements Serializable, Parcelable
{

    @SerializedName("word")
    @Expose
    private String word;
    @SerializedName("score")
    @Expose
    private Integer score;
    public final static Creator<FrequentFollowingWordsModel> CREATOR = new Creator<FrequentFollowingWordsModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public FrequentFollowingWordsModel createFromParcel(android.os.Parcel in) {
            return new FrequentFollowingWordsModel(in);
        }

        public FrequentFollowingWordsModel[] newArray(int size) {
            return (new FrequentFollowingWordsModel[size]);
        }

    }
            ;
    private final static long serialVersionUID = 3677625200977216400L;

    protected FrequentFollowingWordsModel(android.os.Parcel in) {
        this.word = ((String) in.readValue((String.class.getClassLoader())));
        this.score = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public FrequentFollowingWordsModel() {
    }

    /**
     *
     * @param score
     * @param word
     */
    public FrequentFollowingWordsModel(String word, Integer score) {
        super();
        this.word = word;
        this.score = score;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public FrequentFollowingWordsModel withWord(String word) {
        this.word = word;
        return this;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public FrequentFollowingWordsModel withScore(Integer score) {
        this.score = score;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(FrequentFollowingWordsModel.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(word);
        dest.writeValue(score);
    }

    public int describeContents() {
        return 0;
    }

}
