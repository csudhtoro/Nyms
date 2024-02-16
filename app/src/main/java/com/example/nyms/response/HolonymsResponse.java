package com.example.nyms.response;

import com.example.nyms.models.HolonymModel;
import com.google.gson.annotations.Expose;

import java.util.List;

public class HolonymsResponse {
    @Expose
    private List<HolonymModel> holonyms;

    public List<HolonymModel> getHolonyms() {
        return holonyms;
    }

    @Override
    public String toString() {
        return "HolonymsResponse{" + "holonyms=" + holonyms + '}';
    }
}
