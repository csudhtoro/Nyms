package com.example.nyms.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class VictoryInfoModel implements Serializable, Parcelable
{

    @SerializedName("wordGuess")
    @Expose
    private String wordGuess;
    @SerializedName("timeTaken")
    @Expose
    private Integer timeTaken;
    @SerializedName("numOfGuesses")
    @Expose
    private Integer numOfGuesses;
    @SerializedName("currentStreak")
    @Expose
    private Integer currentStreak;
    @SerializedName("relatedWords")
    @Expose
    private List<String> relatedWords = null;
    @SerializedName("definitions")
    @Expose
    private List<String> definitions = null;
    public final static Creator<VictoryInfoModel> CREATOR = new Creator<VictoryInfoModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public VictoryInfoModel createFromParcel(android.os.Parcel in) {
            return new VictoryInfoModel(in);
        }

        public VictoryInfoModel[] newArray(int size) {
            return (new VictoryInfoModel[size]);
        }

    }
            ;
    private final static long serialVersionUID = 2481648664303878590L;

    protected VictoryInfoModel(android.os.Parcel in) {
        this.wordGuess = ((String) in.readValue((String.class.getClassLoader())));
        this.timeTaken = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.numOfGuesses = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.currentStreak = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.relatedWords, (java.lang.String.class.getClassLoader()));
        in.readList(this.definitions, (java.lang.String.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public VictoryInfoModel() {
    }

    /**
     *
     * @param wordGuess
     * @param timeTaken
     * @param currentStreak
     * @param numOfGuesses
     * @param relatedWords
     * @param definitions
     */
    public VictoryInfoModel(String wordGuess, Integer timeTaken, Integer numOfGuesses, Integer currentStreak, List<String> relatedWords, List<String> definitions) {
        super();
        this.wordGuess = wordGuess;
        this.timeTaken = timeTaken;
        this.numOfGuesses = numOfGuesses;
        this.currentStreak = currentStreak;
        this.relatedWords = relatedWords;
        this.definitions = definitions;
    }

    public String getWordGuess() {
        return wordGuess;
    }

    public void setWordGuess(String wordGuess) {
        this.wordGuess = wordGuess;
    }

    public VictoryInfoModel withWordGuess(String wordGuess) {
        this.wordGuess = wordGuess;
        return this;
    }

    public Integer getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(Integer timeTaken) {
        this.timeTaken = timeTaken;
    }

    public VictoryInfoModel withTimeTaken(Integer timeTaken) {
        this.timeTaken = timeTaken;
        return this;
    }

    public Integer getNumOfGuesses() {
        return numOfGuesses;
    }

    public void setNumOfGuesses(Integer numOfGuesses) {
        this.numOfGuesses = numOfGuesses;
    }

    public VictoryInfoModel withNumOfGuesses(Integer numOfGuesses) {
        this.numOfGuesses = numOfGuesses;
        return this;
    }

    public Integer getCurrentStreak() {
        return currentStreak;
    }

    public void setCurrentStreak(Integer currentStreak) {
        this.currentStreak = currentStreak;
    }

    public VictoryInfoModel withCurrentStreak(Integer currentStreak) {
        this.currentStreak = currentStreak;
        return this;
    }

    public List<String> getRelatedWords() {
        return relatedWords;
    }

    public List<String> getDefinitions() {return definitions;}

    public void setRelatedWords(List<String> relatedWords) {
        this.relatedWords = relatedWords;
    }

    public void setDefinitions(List<String> definitions) {this.definitions = definitions;}

    public VictoryInfoModel withRelatedWords(List<String> relatedWords) {
        this.relatedWords = relatedWords;
        return this;
    }

    public VictoryInfoModel withDefinitions(List<String> definitions) {
        this.definitions = definitions;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(VictoryInfoModel.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("wordGuess");
        sb.append('=');
        sb.append(((this.wordGuess == null)?"<null>":this.wordGuess));
        sb.append(',');
        sb.append("timeTaken");
        sb.append('=');
        sb.append(((this.timeTaken == null)?"<null>":this.timeTaken));
        sb.append(',');
        sb.append("numOfGuesses");
        sb.append('=');
        sb.append(((this.numOfGuesses == null)?"<null>":this.numOfGuesses));
        sb.append(',');
        sb.append("currentStreak");
        sb.append('=');
        sb.append(((this.currentStreak == null)?"<null>":this.currentStreak));
        sb.append(',');
        sb.append("relatedWords");
        sb.append('=');
        sb.append(((this.relatedWords == null)?"<null>":this.relatedWords));
        sb.append(',');
        sb.append("definitions");
        sb.append('=');
        sb.append(((this.definitions == null)?"<null>":this.definitions));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(wordGuess);
        dest.writeValue(timeTaken);
        dest.writeValue(numOfGuesses);
        dest.writeValue(currentStreak);
        dest.writeList(relatedWords);
        dest.writeList(definitions);
    }

    public int describeContents() {
        return 0;
    }

}
