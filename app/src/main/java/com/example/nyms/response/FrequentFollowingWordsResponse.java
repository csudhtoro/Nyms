package com.example.nyms.response;

import com.example.nyms.models.FrequentFollowingWordsModel;
import com.example.nyms.models.HolonymModel;
import com.google.gson.annotations.Expose;

import java.util.List;

public class FrequentFollowingWordsResponse {
    @Expose
    private List<FrequentFollowingWordsModel> frequentFollowingWords;

    public List<FrequentFollowingWordsModel> getFrequentFollowingWords() {
        return frequentFollowingWords;
    }

    @Override
    public String toString() {
        return "FrequentFollowingWordsResponse{" + "frequentFollowingWords=" + frequentFollowingWords + '}';
    }
}