package com.example.nyms.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class SecretWordHintsModel implements Serializable, Parcelable
{

    @SerializedName("numOfWords")
    @Expose
    private String numOfWords;
    @SerializedName("numOfSyallables")
    @Expose
    private String numOfSyallables;
    @SerializedName("partsOfSpeech")
    @Expose
    private List<String> partsOfSpeech = null;
    @SerializedName("definitions")
    @Expose
    private List<String> definitions = null;
    public final static Creator<SecretWordHintsModel> CREATOR = new Creator<SecretWordHintsModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SecretWordHintsModel createFromParcel(android.os.Parcel in) {
            return new SecretWordHintsModel(in);
        }

        public SecretWordHintsModel[] newArray(int size) {
            return (new SecretWordHintsModel[size]);
        }

    }
            ;
    private final static long serialVersionUID = 631514129512430410L;

    protected SecretWordHintsModel(android.os.Parcel in) {
        this.numOfWords = ((String) in.readValue((String.class.getClassLoader())));
        this.numOfSyallables = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.partsOfSpeech, (java.lang.String.class.getClassLoader()));
        in.readList(this.definitions, (java.lang.String.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public SecretWordHintsModel() {
    }

    /**
     *
     * @param numOfSyallables
     * @param numOfWords
     * @param partsOfSpeech
     * @param definitions
     */
    public SecretWordHintsModel(String numOfWords, String numOfSyallables, List<String> partsOfSpeech, List<String> definitions) {
        super();
        this.numOfWords = numOfWords;
        this.numOfSyallables = numOfSyallables;
        this.partsOfSpeech = partsOfSpeech;
        this.definitions = definitions;
    }

    public String getNumOfWords() {
        return numOfWords;
    }

    public void setNumOfWords(String numOfWords) {
        this.numOfWords = numOfWords;
    }

    public SecretWordHintsModel withNumOfWords(String numOfWords) {
        this.numOfWords = numOfWords;
        return this;
    }

    public String getNumOfSyallables() {
        return numOfSyallables;
    }

    public void setNumOfSyallables(String numOfSyallables) {
        this.numOfSyallables = numOfSyallables;
    }

    public SecretWordHintsModel withNumOfSyallables(String numOfSyallables) {
        this.numOfSyallables = numOfSyallables;
        return this;
    }

    public List<String> getPartsOfSpeech() {
        return partsOfSpeech;
    }

    public void setPartsOfSpeech(List<String> partsOfSpeech) {
        this.partsOfSpeech = partsOfSpeech;
    }

    public SecretWordHintsModel withPartsOfSpeech(List<String> partsOfSpeech) {
        this.partsOfSpeech = partsOfSpeech;
        return this;
    }

    public List<String> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<String> definitions) {
        this.definitions = definitions;
    }

    public SecretWordHintsModel withDefinitions(List<String> definitions) {
        this.definitions = definitions;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SecretWordHintsModel.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("numOfWords");
        sb.append('=');
        sb.append(((this.numOfWords == null)?"<null>":this.numOfWords));
        sb.append(',');
        sb.append("numOfSyallables");
        sb.append('=');
        sb.append(((this.numOfSyallables == null)?"<null>":this.numOfSyallables));
        sb.append(',');
        sb.append("partsOfSpeech");
        sb.append('=');
        sb.append(((this.partsOfSpeech == null)?"<null>":this.partsOfSpeech));
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
        dest.writeValue(numOfWords);
        dest.writeValue(numOfSyallables);
        dest.writeList(partsOfSpeech);
        dest.writeList(definitions);
    }

    public int describeContents() {
        return 0;
    }

}
