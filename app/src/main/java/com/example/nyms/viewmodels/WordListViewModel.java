package com.example.nyms.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

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
import com.example.nyms.repositories.WordRepository;

import java.util.List;

public class WordListViewModel extends ViewModel {

    private WordRepository wordRepository;

    //CONSTRUCTOR
    public WordListViewModel() {
        wordRepository = WordRepository.getInstance();
    }

    //GETTERS
    public LiveData<List<SimilarWordsModel>> getSimilarWords() { return wordRepository.getSimilarWords();}
    public LiveData<List<SynonymModel>> getSynonyms() { return wordRepository.getSynonyms();}
    public LiveData<List<AntonymModel>> getAntonyms() { return wordRepository.getAntonyms();}
    public LiveData<List<TriggerWordModel>> getTriggerWords() { return wordRepository.getTriggerWords();}
    public LiveData<List<HypernymModel>> getHypernyms() { return wordRepository.getHypernyms();}
    public LiveData<List<HyponymModel>> getHyponyms() { return wordRepository.getHyponyms();}
    public LiveData<List<MeronymModel>> getMeronyms() { return wordRepository.getMeronyms();}
    public LiveData<List<HolonymModel>> getHolonyms() { return wordRepository.getHolonyms();}
    public LiveData<List<RhymingWordsModel>> getRhymingWords() { return wordRepository.getRhymingWords();}
    public LiveData<List<HomophoneModel>> getHomophones() { return wordRepository.getHomophones();}
    public LiveData<List<FrequentFollowingWordsModel>> getFrequentFollowingWords() { return wordRepository.getFrequentFollowingWords();}
    public LiveData<List<FrequentPrecedingWordsModel>> getFrequentPrecedingWords() { return wordRepository.getFrequentPrecedingWords();}
    public LiveData<Integer> getSyllables() { return wordRepository.getSyllables();}
    public LiveData<List<String>> getPartsOfSpeech() { return wordRepository.getPartsOfSpeech();}
    public LiveData<List<String>> getDefinitions() { return wordRepository.getDefinitions();}
    public LiveData<List<SimilarWordsModel>> getTopicWords() { return wordRepository.getTopicWords();}

    //3 - CALLING METHOD IN VIEWMODEL
    public void searchSimilarWords(String query) {wordRepository.searchSimilarWords(query);}
    public void searchSynonyms(String query) {wordRepository.searchSynonyms(query);}
    public void searchAntonyms(String query) {wordRepository.searchAntonyms(query);}
    public void searchTriggerWords(String query) {wordRepository.searchTriggerWords(query);}
    public void searchHypernyms(String query) {wordRepository.searchHypernyms(query);}
    public void searchHyponyms(String query) {wordRepository.searchHyponyms(query);}
    public void searchMeronyms(String query) {wordRepository.searchMeronyms(query);}
    public void searchHolonyms(String query) {wordRepository.searchHolonyms(query);}
    public void searchRhymingWords(String query) {wordRepository.searchRhymingWords(query);}
    public void searchHomophones(String query) {wordRepository.searchHomophones(query);}
    public void searchFrequentFollowingWords(String query) {wordRepository.searchFrequentFollowingWords(query);}
    public void searchFrequentPrecedingWords(String query) {wordRepository.searchFrequentPrecedingWords(query);}
    public void searchSyllables(String query) {wordRepository.searchSyllables(query);}
    public void searchPartsOfSpeech(String query) {wordRepository.searchPartsOfSpeech(query);}
    public void searchDefinitions(String query) {wordRepository.searchDefinitions(query);}
    public void searchTopicWords(String query) {wordRepository.searchTopicWords(query);}
}
