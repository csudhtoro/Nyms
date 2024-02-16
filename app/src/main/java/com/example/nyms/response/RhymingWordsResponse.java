package com.example.nyms.response;

import com.example.nyms.models.RhymingWordsModel;
import com.example.nyms.models.SimilarWordsModel;
import com.google.gson.annotations.Expose;

import java.util.List;

public class RhymingWordsResponse {

    @Expose
    private List<RhymingWordsModel> words;

    public List<RhymingWordsModel> getWords() { return words; }

    @Override
    public String toString() {
        return "RhymingWordsResponse{" +
                "word=" + words +
                '}';
    }
}