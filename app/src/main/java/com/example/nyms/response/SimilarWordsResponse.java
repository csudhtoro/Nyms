package com.example.nyms.response;

import com.example.nyms.models.SimilarWordsModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class SimilarWordsResponse {

    @Expose
    private List<SimilarWordsModel> words;

    public List<SimilarWordsModel> getWords() { return words; }

    @Override
    public String toString() {
        return "SimilarWordsResponse{" +
                "word=" + words +
                '}';
    }
}