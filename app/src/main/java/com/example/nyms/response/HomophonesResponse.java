package com.example.nyms.response;

import com.example.nyms.models.HomophoneModel;
import com.google.gson.annotations.Expose;

import java.util.List;

public class HomophonesResponse {
    @Expose
    private List<HomophoneModel> homophones;

    public List<HomophoneModel> getHomophones() {
        return homophones;
    }

    @Override
    public String toString() {
        return "HomophonesResponse{" + "homophones=" + homophones + '}';
    }
}
