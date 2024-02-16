package com.example.nyms.response;

import com.example.nyms.models.HypernymModel;
import com.google.gson.annotations.Expose;

import java.util.List;

public class HypernymsResponse {
    @Expose
    private List<HypernymModel> hypernyms;

    public List<HypernymModel> getHypernyms() {
        return hypernyms;
    }

    @Override
    public String toString() {
        return "HypernymsWordsResponse{" + "hypernyms words=" + hypernyms + '}';
    }
}
