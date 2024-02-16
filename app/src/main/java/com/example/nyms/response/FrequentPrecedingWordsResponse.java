package com.example.nyms.response;

import com.example.nyms.models.FrequentPrecedingWordsModel;
import com.google.gson.annotations.Expose;

import java.util.List;

public class FrequentPrecedingWordsResponse {
    @Expose
    private List<FrequentPrecedingWordsModel> frequentPrecedingWords;

    public List<FrequentPrecedingWordsModel> getFrequentPrecedingWords() {
        return frequentPrecedingWords;
    }

    @Override
    public String toString() {
        return "FrequentPrecedingWordsResponse{" + "frequentPrecedingWords=" + frequentPrecedingWords + '}';
    }
}
