package com.example.nyms.response;

import com.example.nyms.models.TriggerWordModel;
import com.google.gson.annotations.Expose;

import java.util.List;

public class TriggerWordsResponse {
    @Expose
    private List<TriggerWordModel> triggerWords;

    public List<TriggerWordModel> getTriggerWords() {
        return triggerWords;
    }

    @Override
    public String toString() {
        return "TriggerWordsResponse{" + "trigger words=" + triggerWords + '}';
    }
}
