package com.example.nyms.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RhymingWordsModel implements Serializable, Parcelable
{

    @SerializedName("word")
    @Expose
    private String word;
    @SerializedName("score")
    @Expose
    private Integer score;
    @SerializedName("numSyllables")
    @Expose
    private Integer numSyllables;
    public final static Creator<RhymingWordsModel> CREATOR = new Creator<RhymingWordsModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public RhymingWordsModel createFromParcel(android.os.Parcel in) {
            return new RhymingWordsModel(in);
        }

        public RhymingWordsModel[] newArray(int size) {
            return (new RhymingWordsModel[size]);
        }

    }
            ;
    private final static long serialVersionUID = -5254205367575037381L;

    protected RhymingWordsModel(android.os.Parcel in) {
        this.word = ((String) in.readValue((String.class.getClassLoader())));
        this.score = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.numSyllables = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public RhymingWordsModel() {
    }

    /**
     *
     * @param score
     * @param numSyllables
     * @param word
     */
    public RhymingWordsModel(String word, Integer score, Integer numSyllables) {
        super();
        this.word = word;
        this.score = score;
        this.numSyllables = numSyllables;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public RhymingWordsModel withWord(String word) {
        this.word = word;
        return this;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public RhymingWordsModel withScore(Integer score) {
        this.score = score;
        return this;
    }

    public Integer getNumSyllables() {
        return numSyllables;
    }

    public void setNumSyllables(Integer numSyllables) {
        this.numSyllables = numSyllables;
    }

    public RhymingWordsModel withNumSyllables(Integer numSyllables) {
        this.numSyllables = numSyllables;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(RhymingWordsModel.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("word");
        sb.append('=');
        sb.append(((this.word == null)?"<null>":this.word));
        sb.append(',');
        sb.append("score");
        sb.append('=');
        sb.append(((this.score == null)?"<null>":this.score));
        sb.append(',');
        sb.append("numSyllables");
        sb.append('=');
        sb.append(((this.numSyllables == null)?"<null>":this.numSyllables));
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
        dest.writeValue(numSyllables);
    }

    public int describeContents() {
        return 0;
    }

}