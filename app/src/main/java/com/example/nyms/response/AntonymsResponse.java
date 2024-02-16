package com.example.nyms.response;

import com.example.nyms.models.AntonymModel;
import com.google.gson.annotations.Expose;

import java.util.List;

public class AntonymsResponse {
    @Expose
    private List<AntonymModel> antonyms;

    public List<AntonymModel> getAntonyms() {
        return antonyms;
    }

    @Override
    public String toString() {
        return "AntonymsResponse{" + "antonyms=" + antonyms + '}';
    }
}
