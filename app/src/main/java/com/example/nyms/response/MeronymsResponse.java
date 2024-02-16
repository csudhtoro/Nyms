package com.example.nyms.response;

import com.example.nyms.models.MeronymModel;
import com.google.gson.annotations.Expose;

import java.util.List;

public class MeronymsResponse {
    @Expose
    private List<MeronymModel> meronyms;

    public List<MeronymModel> getMeronyms() {
        return meronyms;
    }

    @Override
    public String toString() {
        return "MeronymsResponse{" + "meronyms=" + meronyms + '}';
    }
}
