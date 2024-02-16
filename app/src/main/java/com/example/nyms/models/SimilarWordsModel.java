package com.example.nyms.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class SimilarWordsModel implements Parcelable
{

    @SerializedName("word")
    @Expose
    private String word;
    @SerializedName("score")
    @Expose
    private Integer score;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;
    public final static Creator<SimilarWordsModel> CREATOR = new Creator<SimilarWordsModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SimilarWordsModel createFromParcel(android.os.Parcel in) {
            return new SimilarWordsModel(in);
        }
        @Override
        public SimilarWordsModel[] newArray(int size) { return new SimilarWordsModel[size]; }

    }
            ;
    private final static long serialVersionUID = 1103834887463933044L;

    protected SimilarWordsModel(android.os.Parcel in) {
        this.word = ((String) in.readValue((String.class.getClassLoader())));
        this.score = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.tags, (java.lang.String.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public SimilarWordsModel() {
    }

    /**
     *
     * @param score
     * @param word
     * @param tags
     */
    public SimilarWordsModel(String word, Integer score, List<String> tags) {
        super();
        this.word = word;
        this.score = score;
        this.tags = tags;
    }

    public String getWord() { return word; }

    public void setWord(String word) { this.word = word; }

    public SimilarWordsModel withWord(String word) {
        this.word = word;
        return this;
    }

    public Integer getScore() { return score; }

    public void setScore(Integer score) { this.score = score; }

    public SimilarWordsModel withScore(Integer score) {
        this.score = score;
        return this;
    }

    public List<String> getTags() { return tags; }

    public void setTags(List<String> tags) { this.tags = tags; }

    public SimilarWordsModel withTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SimilarWordsModel.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("word");
        sb.append('=');
        sb.append(((this.word == null)?"<null>":this.word));
        sb.append(',');
        sb.append("score");
        sb.append('=');
        sb.append(((this.score == null)?"<null>":this.score));
        sb.append(',');
        sb.append("tags");
        sb.append('=');
        sb.append(((this.tags == null)?"<null>":this.tags));
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
        dest.writeList(tags);
    }

    public int describeContents() {
        return 0;
    }

}
