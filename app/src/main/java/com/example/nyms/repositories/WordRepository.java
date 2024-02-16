package com.example.nyms.repositories;

import androidx.lifecycle.LiveData;

import com.example.nyms.models.AntonymModel;
import com.example.nyms.models.FrequentFollowingWordsModel;
import com.example.nyms.models.FrequentPrecedingWordsModel;
import com.example.nyms.models.HolonymModel;
import com.example.nyms.models.HomophoneModel;
import com.example.nyms.models.HypernymModel;
import com.example.nyms.models.HyponymModel;
import com.example.nyms.models.MeronymModel;
import com.example.nyms.models.RhymingWordsModel;
import com.example.nyms.models.SecretWordSyllableModel;
import com.example.nyms.models.SimilarWordsModel;
import com.example.nyms.models.SynonymModel;
import com.example.nyms.models.TriggerWordModel;
import com.example.nyms.requests.WordApiClient;

import java.util.List;


public class WordRepository {

    private static WordRepository instance;
    private WordApiClient wordApiClient;
    private String mQuery;

    //SINGLETON
    public static WordRepository getInstance() {
        if(instance == null) {
            instance = new WordRepository();
        }
        return  instance;
    }

    //INSTANCE
    private WordRepository() {
        wordApiClient = WordApiClient.getInstance();
    }

    //GETTER
    public LiveData<List<SimilarWordsModel>> getSimilarWords() {return wordApiClient.getSimilar();}
    public LiveData<List<SynonymModel>> getSynonyms() {return wordApiClient.getSynonyms();}
    public LiveData<List<AntonymModel>> getAntonyms() {return wordApiClient.getAntonyms();}
    public LiveData<List<TriggerWordModel>> getTriggerWords() {return wordApiClient.getTriggerWords();}
    public LiveData<List<HypernymModel>> getHypernyms() {return wordApiClient.getHypernyms();}
    public LiveData<List<HyponymModel>> getHyponyms() {return wordApiClient.getHyponyms();}
    public LiveData<List<MeronymModel>> getMeronyms() {return wordApiClient.getMeronyms();}
    public LiveData<List<HolonymModel>> getHolonyms() {return wordApiClient.getHolonyms();}
    public LiveData<List<RhymingWordsModel>> getRhymingWords() {return wordApiClient.getRhyming();}
    public LiveData<List<HomophoneModel>> getHomophones() {return wordApiClient.getHomophones();}
    public LiveData<List<FrequentFollowingWordsModel>> getFrequentFollowingWords() {return wordApiClient.getFrequentFollowingWords();}
    public LiveData<List<FrequentPrecedingWordsModel>> getFrequentPrecedingWords() {return wordApiClient.getFrequentPrecedingWords();}
    public LiveData<Integer> getSyllables() {return wordApiClient.getSyllables();}
    public LiveData<List<String>> getPartsOfSpeech() {return wordApiClient.getPartsOfSpeech();}
    public LiveData<List<String>> getDefinitions() {return wordApiClient.getDefinitions();}
    public LiveData<List<SimilarWordsModel>> getTopicWords() {return wordApiClient.getTopics();}


    //SEARCH METHOD
    //2 - Calling the method
    public void searchSimilarWords(String query) {
        mQuery = query;
        wordApiClient.searchSimilars(mQuery);
    }
    public void searchSynonyms(String query) {
        mQuery = query;
        wordApiClient.searchSynonyms(mQuery);
    }
    public void searchAntonyms(String query) {
        mQuery = query;
        wordApiClient.searchAntonyms(mQuery);
    }
    public void searchTriggerWords(String query) {
        mQuery = query;
        wordApiClient.searchTrggers(mQuery);
    }
    public void searchHypernyms(String query) {
        mQuery = query;
        wordApiClient.searchHypernyms(mQuery);
    }
    public void searchHyponyms(String query) {
        mQuery = query;
        wordApiClient.searchHyponyms(mQuery);
    }
    public void searchMeronyms(String query) {
        mQuery = query;
        wordApiClient.searchMeronyms(mQuery);
    }
    public void searchHolonyms(String query) {
        mQuery = query;
        wordApiClient.searchHolonyms(mQuery);
    }
    public void searchRhymingWords(String query) {
        mQuery = query;
        wordApiClient.searchRhyming(mQuery);
    }
    public void searchHomophones(String query) {
        mQuery = query;
        wordApiClient.searchHomophones(mQuery);
    }
    public void searchFrequentFollowingWords(String query) {
        mQuery = query;
        wordApiClient.searchFrequentFollowingWords(mQuery);
    }
    public void searchFrequentPrecedingWords(String query) {
        mQuery = query;
        wordApiClient.searchFrequentPrecedingWords(mQuery);
    }
    public void searchSyllables(String query) {
        mQuery = query;
        wordApiClient.searchSyllables(mQuery);
    }
    public void searchPartsOfSpeech(String query) {
        mQuery = query;
        wordApiClient.searchPartsOfSpeech(mQuery);
    }
    public void searchDefinitions(String query) {
        mQuery = query;
        wordApiClient.searchDefinitions(mQuery);
    }
    public void searchTopicWords(String query) {
        mQuery = query;
        wordApiClient.searchTopics(mQuery);
    }
}
