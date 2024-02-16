package com.example.nyms.response;

import com.example.nyms.models.HyponymModel;
import com.google.gson.annotations.Expose;

import java.util.List;

public class HyponymsResponse {
    @Expose
    private List<HyponymModel> hyponyms;

    public List<HyponymModel> getHyponyms() {
        return hyponyms;
    }

    @Override
    public String toString() {
        return "HyponymsResponse{" + "hyponyms=" + hyponyms + '}';
    }
}
