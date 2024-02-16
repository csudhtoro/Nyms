package com.example.nyms.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SecretWordDefinitionModel implements Serializable, Parcelable
{

    @SerializedName("word")
    @Expose
    private String word;
    @SerializedName("score")
    @Expose
    private Integer score;
    @SerializedName("defs")
    @Expose
    private List<String> defs = null;
    @SerializedName("defHeadword")
    @Expose
    private String defHeadword;
    public final static Creator<SecretWordDefinitionModel> CREATOR = new Creator<SecretWordDefinitionModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SecretWordDefinitionModel createFromParcel(android.os.Parcel in) {
            return new SecretWordDefinitionModel(in);
        }

        public SecretWordDefinitionModel[] newArray(int size) {
            return (new SecretWordDefinitionModel[size]);
        }

    }
            ;
    private final static long serialVersionUID = 1392754197203456903L;

    protected SecretWordDefinitionModel(android.os.Parcel in) {
        this.word = ((String) in.readValue((String.class.getClassLoader())));
        this.score = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.defs, (java.lang.String.class.getClassLoader()));
        this.defHeadword = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public SecretWordDefinitionModel() {
    }

    /**
     *
     * @param defs
     * @param score
     * @param defHeadword
     * @param word
     */
    public SecretWordDefinitionModel(String word, Integer score, List<String> defs, String defHeadword) {
        super();
        this.word = word;
        this.score = score;
        this.defs = defs;
        this.defHeadword = defHeadword;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public SecretWordDefinitionModel withWord(String word) {
        this.word = word;
        return this;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public SecretWordDefinitionModel withScore(Integer score) {
        this.score = score;
        return this;
    }

    public List<String> getDefs() {
        return defs;
    }

    public void setDefs(List<String> defs) {
        this.defs = defs;
    }

    public SecretWordDefinitionModel withDefs(List<String> defs) {
        this.defs = defs;
        return this;
    }

    public String getDefHeadword() {
        return defHeadword;
    }

    public void setDefHeadword(String defHeadword) {
        this.defHeadword = defHeadword;
    }

    public SecretWordDefinitionModel withDefHeadword(String defHeadword) {
        this.defHeadword = defHeadword;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SecretWordDefinitionModel.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("word");
        sb.append('=');
        sb.append(((this.word == null)?"<null>":this.word));
        sb.append(',');
        sb.append("score");
        sb.append('=');
        sb.append(((this.score == null)?"<null>":this.score));
        sb.append(',');
        sb.append("defs");
        sb.append('=');
        sb.append(((this.defs == null)?"<null>":this.defs));
        sb.append(',');
        sb.append("defHeadword");
        sb.append('=');
        sb.append(((this.defHeadword == null)?"<null>":this.defHeadword));
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
        dest.writeList(defs);
        dest.writeValue(defHeadword);
    }

    public int describeContents() {
        return 0;
    }

}
