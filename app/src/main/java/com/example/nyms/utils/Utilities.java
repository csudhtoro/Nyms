package com.example.nyms.utils;

import java.util.Comparator;

public class Utilities {

    /*public Map<Integer, String> AddtoRelatedWordsMap(Object word, int highestScore, int index) {
        Map<Integer, String> tempMap = new HashMap<>();
        int score;
        if(word.getScore() != null) {
            if (index == 0) highestScore = word.getScore() + 100;
            double newScore = ((double) word.getScore() / highestScore) * 100;
            if (newScore >= 20) {
                tempMap.put((int) newScore, "similar");
            } else { //Generate random number between 5 and 10
                Random r = new Random();
                int low = 5;
                int high = 19;
                int randomNum = r.nextInt(high - low) + low;
                tempMap.put(randomNum, "similar");
            }
        }
        else { //Generate random number between 5 and 10
            Random r = new Random();
            int low = 5;
            int high = 19;
            int randomNum = r.nextInt(high - low) + low;
            tempMap.put(randomNum, "similar");
        }
        return tempMap;
    }*/
}
