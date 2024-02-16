package com.example.nyms.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SecretWordSyllableModel implements Serializable, Parcelable
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
    public final static Creator<SecretWordSyllableModel> CREATOR = new Creator<SecretWordSyllableModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SecretWordSyllableModel createFromParcel(android.os.Parcel in) {
            return new SecretWordSyllableModel(in);
        }

        public SecretWordSyllableModel[] newArray(int size) {
            return (new SecretWordSyllableModel[size]);
        }

    }
            ;
    private final static long serialVersionUID = 3407865365789785806L;

    protected SecretWordSyllableModel(android.os.Parcel in) {
        this.word = ((String) in.readValue((String.class.getClassLoader())));
        this.score = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.numSyllables = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public SecretWordSyllableModel() {
    }

    /**
     *
     * @param score
     * @param numSyllables
     * @param word
     */
    public SecretWordSyllableModel(String word, Integer score, Integer numSyllables) {
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

    public SecretWordSyllableModel withWord(String word) {
        this.word = word;
        return this;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public SecretWordSyllableModel withScore(Integer score) {
        this.score = score;
        return this;
    }

    public Integer getNumSyllables() {
        return numSyllables;
    }

    public void setNumSyllables(Integer numSyllables) {
        this.numSyllables = numSyllables;
    }

    public SecretWordSyllableModel withNumSyllables(Integer numSyllables) {
        this.numSyllables = numSyllables;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SecretWordSyllableModel.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
