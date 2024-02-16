package com.example.nyms.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CategoryModel implements Serializable, Parcelable
{

    @SerializedName("word")
    @Expose
    private String word;
    @SerializedName("typeOf")
    @Expose
    private List<String> typeOf = null;
    public final static Creator<CategoryModel> CREATOR = new Creator<CategoryModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CategoryModel createFromParcel(android.os.Parcel in) {
            return new CategoryModel(in);
        }

        public CategoryModel[] newArray(int size) {
            return (new CategoryModel[size]);
        }

    }
            ;
    private final static long serialVersionUID = 8897261534213095984L;

    protected CategoryModel(android.os.Parcel in) {
        this.word = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.typeOf, (java.lang.String.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public CategoryModel() {
    }

    /**
     *
     * @param word
     * @param typeOf
     */
    public CategoryModel(String word, List<String> typeOf) {
        super();
        this.word = word;
        this.typeOf = typeOf;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public CategoryModel withWord(String word) {
        this.word = word;
        return this;
    }

    public List<String> getTypeOf() {
        return typeOf;
    }

    public void setTypeOf(List<String> typeOf) {
        this.typeOf = typeOf;
    }

    public CategoryModel withTypeOf(List<String> typeOf) {
        this.typeOf = typeOf;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(CategoryModel.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("word");
        sb.append('=');
        sb.append(((this.word == null)?"<null>":this.word));
        sb.append(',');
        sb.append("typeOf");
        sb.append('=');
        sb.append(((this.typeOf == null)?"<null>":this.typeOf));
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
        dest.writeList(typeOf);
    }

    public int describeContents() {
        return 0;
    }

}
