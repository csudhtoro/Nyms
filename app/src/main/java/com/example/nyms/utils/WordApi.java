package com.example.nyms.utils;

import android.util.Log;

import com.example.nyms.models.AntonymModel;
import com.example.nyms.models.CategoryModel;
import com.example.nyms.models.FrequentFollowingWordsModel;
import com.example.nyms.models.FrequentPrecedingWordsModel;
import com.example.nyms.models.HolonymModel;
import com.example.nyms.models.HomophoneModel;
import com.example.nyms.models.HypernymModel;
import com.example.nyms.models.HyponymModel;
import com.example.nyms.models.MeronymModel;
import com.example.nyms.models.RhymingWordsModel;
import com.example.nyms.models.SecretWordDefinitionModel;
import com.example.nyms.models.SecretWordPartOfSpeechModel;
import com.example.nyms.models.SecretWordSyllableModel;
import com.example.nyms.models.SimilarWordsModel;
import com.example.nyms.models.SynonymModel;
import com.example.nyms.models.TriggerWordModel;
import com.example.nyms.response.SimilarWordsResponse;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/*ADDING NEW API SEARCHES
-First, Update this file first with the correct url path (below),
-Second, update "ShowApiClient" file (Add to constructor, create LiveData get method, create searchShowApi method, create RetrieveShowRunnable method),
-Third, update "Show Repository" file (Create live data getter, search method and search next page method),
-Fourth, update "ShowListViewModel" file (Create live data getter and search method),
-Fifth, use the search method created in the last step in the main activity (TVShowDashboardActivity). (In main activity, create a boolean, create an Observe changes method,
create search method, create actual query),
-Sixth, create an adapter for the recyclerview to display the correct item in the relevant recyclerview created below
-Seventh, create a recyclerView to display it in on the main activity (TVShowDashboardActivity)???
-   To setup the onclick correctly
        -Interface -> OnShowListener: to create onClick for relevant api. ex: "onShowAiringTodayClick(int position)".
        Once added, the other method will bark to implement the method
        -Then create a new ShowViewHolder class for the relevant api. ex:ShowAiringTodayViewHolder, then add new onShow""Listener to relevant
        areas in the relevant api recyclerview.java*/


public interface WordApi {
    //Get words with similar meaning to secret word - "https://api.datamuse.com/words?ml=cow"
    @GET("/words?")
    Call<List<SimilarWordsModel>> getSimilarWords(
            @Query("ml") String word,
            @Query("max") int maxResults
    );

    //Get synonyms of the secret word - "https://api.datamuse.com/words?rel_syn=cow"
    @GET("/words?")
    Call<List<SynonymModel>> getSynonymWords(
            @Query("rel_syn") String word,
            @Query("max") int maxResults
    );

    //Get antonyms of the secret word - "https://api.datamuse.com/words?rel_ant=cow"
    @GET("/words?")
    Call<List<AntonymModel>> getAntonymWords(
            @Query("rel_ant") String word,
            @Query("max") int maxResults
    );

    //Get trigger words of the secret word - "https://api.datamuse.com/words?rel_trg=cow"
    @GET("/words?")
    Call<List<TriggerWordModel>> getTriggerWords(
            @Query("rel_trg") String word,
            @Query("max") int maxResults
    );

    //Get hypernyms ("kind of like") of the secret word - "https://api.datamuse.com/words?rel_spc=cow"
    @GET("/words?")
    Call<List<HypernymModel>> getHypernyms(
            @Query("rel_spc") String word,
            @Query("max") int maxResults
    );

    //Get hyponyms ("more general than") of the secret word - "https://api.datamuse.com/words?rel_gen=cow"
    @GET("/words?")
    Call<List<HyponymModel>> getHyponyms(
            @Query("rel_gen") String word,
            @Query("max") int maxResults
    );


    //Get meronyms ("part of") of the secret word - "https://api.datamuse.com/words?rel_par=cow"
    @GET("/words?")
    Call<List<MeronymModel>> getMeronyms(
            @Query("rel_par") String word,
            @Query("max") int maxResults
    );

    //Get holonyms ("comprises") of the secret word - "https://api.datamuse.com/words?rel_com=cow"
    @GET("/words?")
    Call<List<HolonymModel>> getHolonyms(
            @Query("rel_com") String word,
            @Query("max") int maxResults
    );

    //Get rhyming words ("rhymes with") of the secret word - "https://api.datamuse.com/words?rel_rhy=cow"
    @GET("/words?")
    Call<List<RhymingWordsModel>> getRhymingWords(
            @Query("rel_rhy") String word,
            @Query("max") int maxResults
    );

    //Get homophones ("sounds-alike") of the secret word - "https://api.datamuse.com/words?rel_rhy=cow"
    @GET("/words?")
    Call<List<HomophoneModel>> getHomophonesWords(
            @Query("rel_hom") String word,
            @Query("max") int maxResults
    );

    //Get frequent following words of the secret word - "https://api.datamuse.com/words?rel_bga=cow"
    @GET("/words?")
    Call<List<FrequentFollowingWordsModel>> getFrequentFollowingWords(
            @Query("rel_bga") String word,
            @Query("max") int maxResults
    );

    //Get frequent preceding words of the secret word - "https://api.datamuse.com/words?rel_bgb=cow"
    @GET("/words?")
    Call<List<FrequentPrecedingWordsModel>> getFrequentPrecedingWords(
            @Query("rel_bgb") String word,
            @Query("max") int maxResults
    );

    //Get secret word syllables - https://api.datamuse.com/words?sp=apprentice&md=s
    @GET("/words?")
    Call<List<SecretWordSyllableModel>> getSyllables(
            @Query("sp") String word,
            @Query("md") String metaDataCode,
            @Query("max") Integer max
    );

    //Get secret word parts of speech - https://api.datamuse.com/words?sp=apprentice&md=p
    @GET("/words?")
    Call<List<SecretWordPartOfSpeechModel>> getPartsOfSpeech(
            @Query("sp") String word,
            @Query("md") String metaDataCode,
            @Query("max") Integer max
    );

    //Get secret word definition - https://api.datamuse.com/words?sp=apprentice&md=d
    @GET("/words?")
    Call<List<SecretWordDefinitionModel>> getDefinitions(
            @Query("sp") String word,
            @Query("md") String metaDataCode,
            @Query("max") Integer max
    );
    //Get words with similar topics to secret word - "https://api.datamuse.com/words?&topics=apprentice"
    @GET("/words?")
    Call<List<SimilarWordsModel>> getSimilarTopics(
            @Query("topics") String word,
            @Query("max") int maxResults
    );
}
