package com.example.nyms.response;

import com.example.nyms.models.SynonymModel;
import com.google.gson.annotations.Expose;

import java.util.List;

public class SynonymsResponse {
    @Expose
    private List<SynonymModel> synonyms;

    public List<SynonymModel> getSynonyms() {
        return synonyms;
    }

    @Override
    public String toString() {
        return "SynonymsResponse{" + "synonyms=" + synonyms + '}';
    }
}
