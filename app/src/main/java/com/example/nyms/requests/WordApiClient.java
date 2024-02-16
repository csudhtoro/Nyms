package com.example.nyms.requests;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.nyms.models.AntonymModel;
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
import com.example.nyms.response.AntonymsResponse;
import com.example.nyms.response.FrequentFollowingWordsResponse;
import com.example.nyms.response.FrequentPrecedingWordsResponse;
import com.example.nyms.response.HolonymsResponse;
import com.example.nyms.response.HomophonesResponse;
import com.example.nyms.response.HypernymsResponse;
import com.example.nyms.response.HyponymsResponse;
import com.example.nyms.response.MeronymsResponse;
import com.example.nyms.response.RhymingWordsResponse;
import com.example.nyms.response.SimilarWordsResponse;
import com.example.nyms.response.SynonymsResponse;
import com.example.nyms.response.TriggerWordsResponse;
import com.example.nyms.utils.AppExecutors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import retrofit2.Call;
import retrofit2.Response;

public class WordApiClient {

    private static WordApiClient instance;

    //liveData
    private MutableLiveData<List<SimilarWordsModel>> mSimilarWords;
    private RetrieveSimilarWordsRunnable retrieveSimilarWordsRunnable;

    private MutableLiveData<List<SynonymModel>> mSynonyms;
    private RetrieveSynonymsRunnable retrieveSynonymsRunnable;

    private MutableLiveData<List<AntonymModel>> mAntonyms;
    private RetrieveAntonymsRunnable retrieveAntonymsRunnable;

    private MutableLiveData<List<TriggerWordModel>> mTriggerWords;
    private RetrieveTriggerWordsRunnable retrieveTriggerWordsRunnable;

    private MutableLiveData<List<HypernymModel>> mHypernyms;
    private RetrieveHypernymsRunnable retrieveHypernymsRunnable;

    private MutableLiveData<List<HyponymModel>> mHyponyms;
    private RetrieveHyponymsRunnable retrieveHyponymsRunnable;

    private MutableLiveData<List<MeronymModel>> mMeronyms;
    private RetrieveMeronymsRunnable retrieveMeronymsRunnable;

    private MutableLiveData<List<HolonymModel>> mHolonyms;
    private RetrieveHolonymsRunnable retrieveHolonymsRunnable;

    private MutableLiveData<List<RhymingWordsModel>> mRhymingWords;
    private RetrieveRhymingWordsRunnable retrieveRhymingWordsRunnable;

    private MutableLiveData<List<HomophoneModel>> mHomophones;
    private RetrieveHomophopnesRunnable retrieveHomophopnesRunnable;

    private MutableLiveData<List<FrequentFollowingWordsModel>> mFrequentFollowers;
    private RetrieveFrequentFolloweringWords retrieveFrequentFolloweringWords;

    private MutableLiveData<List<FrequentPrecedingWordsModel>> mFrequentpredecessors;
    private RetrieveFrequentPrecedingWords retrieveFrequentPrecedingWords;

    private MutableLiveData<Integer> mSyllables;
    private RetrieveSyllables retrieveSyllables;

    private MutableLiveData<List<String>> mPartsOfSpeech;
    private RetrievePartsOfSpeech retrievePartsOfSpeech;

    private MutableLiveData<List<String>> mDefinitions;
    private RetrieveDefinitions retrieveDefinitions;

    private MutableLiveData<List<SimilarWordsModel>> mTopicWords;
    private RetrieveTopicWordsRunnable retrieveTopicWordsRunnable;


    //Singleton method
    public static WordApiClient getInstance() {
        if(instance == null) {
            instance = new WordApiClient();
        }
        return instance;
    }

    //Constructor
    private WordApiClient() {
        mSimilarWords = new MutableLiveData<>();
        mSynonyms = new MutableLiveData<>();
        mAntonyms = new MutableLiveData<>();
        mTriggerWords = new MutableLiveData<>();
        mHypernyms = new MutableLiveData<>();
        mHyponyms = new MutableLiveData<>();
        mMeronyms = new MutableLiveData<>();
        mHolonyms = new MutableLiveData<>();
        mRhymingWords = new MutableLiveData<>();
        mHomophones = new MutableLiveData<>();
        mFrequentFollowers = new MutableLiveData<>();
        mFrequentpredecessors = new MutableLiveData<>();
        mSyllables = new MutableLiveData();
        mPartsOfSpeech = new MutableLiveData<>();
        mDefinitions = new MutableLiveData<>();
        mTopicWords = new MutableLiveData<>();
    }

    //GETTERS
    public LiveData<List<SimilarWordsModel>> getSimilar() { return mSimilarWords; }
    public LiveData<List<SynonymModel>> getSynonyms() { return mSynonyms; }
    public LiveData<List<AntonymModel>> getAntonyms() { return mAntonyms; }
    public LiveData<List<TriggerWordModel>> getTriggerWords() { return mTriggerWords; }
    public LiveData<List<HypernymModel>> getHypernyms() { return mHypernyms; }
    public LiveData<List<HyponymModel>> getHyponyms() { return mHyponyms; }
    public LiveData<List<MeronymModel>> getMeronyms() { return mMeronyms; }
    public LiveData<List<HolonymModel>> getHolonyms() { return mHolonyms; }
    public LiveData<List<RhymingWordsModel>> getRhyming() { return mRhymingWords; }
    public LiveData<List<HomophoneModel>> getHomophones() { return mHomophones; }
    public LiveData<List<FrequentFollowingWordsModel>> getFrequentFollowingWords() { return mFrequentFollowers; }
    public LiveData<List<FrequentPrecedingWordsModel>> getFrequentPrecedingWords() { return mFrequentpredecessors; }
    public LiveData<Integer> getSyllables() { return mSyllables; }
    public LiveData<List<String>> getPartsOfSpeech() { return mPartsOfSpeech; }
    public LiveData<List<String>> getDefinitions() { return mDefinitions; }
    public LiveData<List<SimilarWordsModel>> getTopics() { return mTopicWords; }


    //---SEARCH METHODS - These methods will be called through classes
    //1 - This method will be called through the classes
    public void searchSimilars(String word) {
        if(retrieveSimilarWordsRunnable != null) {
            retrieveSimilarWordsRunnable = null;
        }

        retrieveSimilarWordsRunnable = new RetrieveSimilarWordsRunnable(word);
        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveSimilarWordsRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //Cancelling the retrofit call requests
                myHandler.cancel(true);

            }
        },3000, TimeUnit.MILLISECONDS);
    }
    public void searchSynonyms(String word) {
        if(retrieveSynonymsRunnable != null) retrieveSynonymsRunnable = null;

        retrieveSynonymsRunnable = new RetrieveSynonymsRunnable(word);
        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveSynonymsRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //Cancelling the retrofit call requests
                myHandler.cancel(true);

            }
        },1000, TimeUnit.MILLISECONDS);
    }
    public void searchAntonyms(String word) {
        if(retrieveAntonymsRunnable != null) retrieveAntonymsRunnable = null;

        retrieveAntonymsRunnable = new RetrieveAntonymsRunnable(word);
        final Future myHandler1 = AppExecutors.getInstance().networkIO().submit(retrieveAntonymsRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //Cancelling the retrofit call requests
                myHandler1.cancel(true);

            }
        },1000, TimeUnit.MILLISECONDS);
    }
    public void searchTrggers(String word) {
        if(retrieveTriggerWordsRunnable != null) retrieveTriggerWordsRunnable = null;

        retrieveTriggerWordsRunnable = new RetrieveTriggerWordsRunnable(word);
        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveTriggerWordsRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //Cancelling the retrofit call requests
                myHandler.cancel(true);

            }
        },1000, TimeUnit.MILLISECONDS);
    }
    public void searchHypernyms(String word) {
        if(retrieveHypernymsRunnable != null) retrieveHypernymsRunnable = null;

        retrieveHypernymsRunnable = new RetrieveHypernymsRunnable(word);
        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveHypernymsRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //Cancelling the retrofit call requests
                myHandler.cancel(true);

            }
        },1000, TimeUnit.MILLISECONDS);
    }
    public void searchHyponyms(String word) {
        if(retrieveHyponymsRunnable != null) retrieveHyponymsRunnable = null;

        retrieveHyponymsRunnable = new RetrieveHyponymsRunnable(word);
        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveHyponymsRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //Cancelling the retrofit call requests
                myHandler.cancel(true);

            }
        },1000, TimeUnit.MILLISECONDS);
    }
    public void searchMeronyms(String word) {
        if(retrieveMeronymsRunnable != null) retrieveMeronymsRunnable = null;

        retrieveMeronymsRunnable = new RetrieveMeronymsRunnable(word);
        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveMeronymsRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //Cancelling the retrofit call requests
                myHandler.cancel(true);

            }
        },1000, TimeUnit.MILLISECONDS);
    }
    public void searchHolonyms(String word) {
        if(retrieveHolonymsRunnable != null) retrieveHolonymsRunnable = null;

        retrieveHolonymsRunnable = new RetrieveHolonymsRunnable(word);
        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveHolonymsRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //Cancelling the retrofit call requests
                myHandler.cancel(true);

            }
        },1000, TimeUnit.MILLISECONDS);
    }
    public void searchRhyming(String word) {
        if(retrieveRhymingWordsRunnable != null) retrieveRhymingWordsRunnable = null;

        retrieveRhymingWordsRunnable = new RetrieveRhymingWordsRunnable(word);
        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveRhymingWordsRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //Cancelling the retrofit call requests
                myHandler.cancel(true);

            }
        },1000, TimeUnit.MILLISECONDS);
    }
    public void searchHomophones(String word) {
        if(retrieveHomophopnesRunnable != null) retrieveHomophopnesRunnable = null;

        retrieveHomophopnesRunnable = new RetrieveHomophopnesRunnable(word);
        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveHomophopnesRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //Cancelling the retrofit call requests
                myHandler.cancel(true);

            }
        },1000, TimeUnit.MILLISECONDS);
    }
    public void searchFrequentFollowingWords(String word) {
        if(retrieveFrequentFolloweringWords != null) retrieveFrequentFolloweringWords = null;

        retrieveFrequentFolloweringWords = new RetrieveFrequentFolloweringWords(word);
        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveFrequentFolloweringWords);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //Cancelling the retrofit call requests
                myHandler.cancel(true);

            }
        },1000, TimeUnit.MILLISECONDS);
    }
    public void searchFrequentPrecedingWords(String word) {
        if(retrieveFrequentPrecedingWords != null) retrieveFrequentPrecedingWords = null;

        retrieveFrequentPrecedingWords = new RetrieveFrequentPrecedingWords(word);
        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveFrequentPrecedingWords);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //Cancelling the retrofit call requests
                myHandler.cancel(true);

            }
        },1000, TimeUnit.MILLISECONDS);
    }
    public void searchSyllables(String word) {
        if(retrieveSyllables != null) retrieveSyllables = null;

        retrieveSyllables = new RetrieveSyllables(word);
        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveSyllables);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //Cancelling the retrofit call requests
                myHandler.cancel(true);

            }
        },1000, TimeUnit.MILLISECONDS);
    }
    public void searchPartsOfSpeech(String word) {
        if(retrievePartsOfSpeech != null) retrievePartsOfSpeech = null;

        retrievePartsOfSpeech = new RetrievePartsOfSpeech(word);
        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrievePartsOfSpeech);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //Cancelling the retrofit call requests
                myHandler.cancel(true);

            }
        },1000, TimeUnit.MILLISECONDS);
    }
    public void searchDefinitions(String word) {
        if(retrieveDefinitions != null) retrieveDefinitions = null;

        retrieveDefinitions = new RetrieveDefinitions(word);
        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveDefinitions);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //Cancelling the retrofit call requests
                myHandler.cancel(true);

            }
        },1000, TimeUnit.MILLISECONDS);
    }
    public void searchTopics(String word) {
        if(retrieveTopicWordsRunnable != null) {
            retrieveTopicWordsRunnable = null;
        }

        retrieveTopicWordsRunnable = new RetrieveTopicWordsRunnable(word);
        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveTopicWordsRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //Cancelling the retrofit call requests
                myHandler.cancel(true);

            }
        },3000, TimeUnit.MILLISECONDS);
    }


    //---RUNNABLE METHODS

    //Retrieving REST API Similar words by runnable class
    private class RetrieveSimilarWordsRunnable implements Runnable {

        private String word;
        boolean cancelRequest;

        public RetrieveSimilarWordsRunnable(String word) {
            this.word = word;
            cancelRequest = false;
        }

        @Override
        public void run() {
            //Getting response objects
            try {
                Response response = getSimilarWords(word).execute();
                if (cancelRequest) {
                    return;
                }
                if(response.code() == 200) {
                    List<SimilarWordsModel> wordsList = new ArrayList<>(((SimilarWordsResponse)response.body()).getWords());
                    //Sending data to live data
                    //PostValue: used for background thread
                    //setValue: not used for background thread
                    mSimilarWords.postValue(wordsList);

                }
                else {
                    String error = response.errorBody().string();
                    Log.v("Tag", "Error: " + error);
                    mSimilarWords.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mSimilarWords.postValue(null);
            }
        }
        //Search query in background thread
        private Call<List<SimilarWordsModel>> getSimilarWords(String word) {
            return Services.getWordApi().getSimilarWords(word, 800);
        }
        private void cancelRequest() {
            Log.v("Tag", "Cancelling Search Request");
            cancelRequest = true;
        }
    }
    private class RetrieveSynonymsRunnable implements Runnable {

        private String word;
        boolean cancelRequest;

        public RetrieveSynonymsRunnable(String word) {
            this.word = word;
            cancelRequest = false;
        }

        @Override
        public void run() {
            //Getting response objects
            try {
                Response response = getSynonyms(word).execute();
                if (cancelRequest) {
                    return;
                }
                if(response.code() == 200) {
                    List<SynonymModel> wordsList = new ArrayList<>(((SynonymsResponse)response.body()).getSynonyms());
                    //Sending data to live data
                    //PostValue: used for background thread
                    //setValue: not used for background thread
                    mSynonyms.postValue(wordsList);

                }
                else {
                    String error = response.errorBody().string();
                    Log.v("Tag", "Error: " + error);
                    mSynonyms.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mSynonyms.postValue(null);
            }
        }
        //Search query in background thread
        private Call<List<SynonymModel>> getSynonyms(String word) {
            return Services.getWordApi().getSynonymWords(word, 200);
        }
        private void cancelRequest() {
            Log.v("Tag", "Cancelling Search Request");
            cancelRequest = true;
        }
    }
    private class RetrieveAntonymsRunnable implements Runnable {

        private String word;
        boolean cancelRequest;

        public RetrieveAntonymsRunnable(String word) {
            this.word = word;
            cancelRequest = false;
        }

        @Override
        public void run() {
            //Getting response objects
            try {
                Response response = getAntonyms(word).execute();
                if (cancelRequest) {
                    return;
                }
                if(response.code() == 200) {
                    List<AntonymModel> wordsList = new ArrayList<>(((AntonymsResponse)response.body()).getAntonyms());
                    //Sending data to live data
                    //PostValue: used for background thread
                    //setValue: not used for background thread
                    mAntonyms.postValue(wordsList);

                }
                else {
                    String error = response.errorBody().string();
                    Log.v("Tag", "Error: " + error);
                    mAntonyms.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mAntonyms.postValue(null);
            }
        }
        //Search query in background thread
        private Call<List<AntonymModel>> getAntonyms(String word) {
            return Services.getWordApi().getAntonymWords(word, 200);
        }
        private void cancelRequest() {
            Log.v("Tag", "Cancelling Search Request");
            cancelRequest = true;
        }
    }
    private class RetrieveTriggerWordsRunnable implements Runnable {

        private String word;
        boolean cancelRequest;

        public RetrieveTriggerWordsRunnable(String word) {
            this.word = word;
            cancelRequest = false;
        }

        @Override
        public void run() {
            //Getting response objects
            try {
                Response response = getTriggerWords(word).execute();
                if (cancelRequest) {
                    return;
                }
                if(response.code() == 200) {
                    List<TriggerWordModel> wordsList = new ArrayList<>(((TriggerWordsResponse)response.body()).getTriggerWords());
                    //Sending data to live data
                    //PostValue: used for background thread
                    //setValue: not used for background thread
                    mTriggerWords.postValue(wordsList);

                }
                else {
                    String error = response.errorBody().string();
                    Log.v("Tag", "Error: " + error);
                    mTriggerWords.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mTriggerWords.postValue(null);
            }
        }
        //Search query in background thread
        private Call<List<TriggerWordModel>> getTriggerWords(String word) {
            return Services.getWordApi().getTriggerWords(word, 200);
        }
        private void cancelRequest() {
            Log.v("Tag", "Cancelling Search Request");
            cancelRequest = true;
        }
    }
    private class RetrieveHypernymsRunnable implements Runnable {

        private String word;
        boolean cancelRequest;

        public RetrieveHypernymsRunnable(String word) {
            this.word = word;
            cancelRequest = false;
        }

        @Override
        public void run() {
            //Getting response objects
            try {
                Response response = getHypernyms(word).execute();
                if (cancelRequest) {
                    return;
                }
                if(response.code() == 200) {
                    List<HypernymModel> wordsList = new ArrayList<>(((HypernymsResponse)response.body()).getHypernyms());
                    //Sending data to live data
                    //PostValue: used for background thread
                    //setValue: not used for background thread
                    mHypernyms.postValue(wordsList);

                }
                else {
                    String error = response.errorBody().string();
                    Log.v("Tag", "Error: " + error);
                    mHypernyms.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mHypernyms.postValue(null);
            }
        }
        //Search query in background thread
        private Call<List<HypernymModel>> getHypernyms(String word) {
            return Services.getWordApi().getHypernyms(word, 200);
        }
        private void cancelRequest() {
            Log.v("Tag", "Cancelling Search Request");
            cancelRequest = true;
        }
    }
    private class RetrieveHyponymsRunnable implements Runnable {

        private String word;
        boolean cancelRequest;

        public RetrieveHyponymsRunnable(String word) {
            this.word = word;
            cancelRequest = false;
        }

        @Override
        public void run() {
            //Getting response objects
            try {
                Response response = getHyponyms(word).execute();
                if (cancelRequest) {
                    return;
                }
                if(response.code() == 200) {
                    List<HyponymModel> wordsList = new ArrayList<>(((HyponymsResponse)response.body()).getHyponyms());
                    //Sending data to live data
                    //PostValue: used for background thread
                    //setValue: not used for background thread
                    mHyponyms.postValue(wordsList);

                }
                else {
                    String error = response.errorBody().string();
                    Log.v("Tag", "Error: " + error);
                    mHyponyms.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mHyponyms.postValue(null);
            }
        }
        //Search query in background thread
        private Call<List<HyponymModel>> getHyponyms(String word) {
            return Services.getWordApi().getHyponyms(word, 200);
        }
        private void cancelRequest() {
            Log.v("Tag", "Cancelling Search Request");
            cancelRequest = true;
        }
    }
    private class RetrieveMeronymsRunnable implements Runnable {

        private String word;
        boolean cancelRequest;

        public RetrieveMeronymsRunnable(String word) {
            this.word = word;
            cancelRequest = false;
        }

        @Override
        public void run() {
            //Getting response objects
            try {
                Response response = getMeronyms(word).execute();
                if (cancelRequest) {
                    return;
                }
                if(response.code() == 200) {
                    List<MeronymModel> wordsList = new ArrayList<>(((MeronymsResponse)response.body()).getMeronyms());
                    //Sending data to live data
                    //PostValue: used for background thread
                    //setValue: not used for background thread
                    mMeronyms.postValue(wordsList);

                }
                else {
                    String error = response.errorBody().string();
                    Log.v("Tag", "Error: " + error);
                    mMeronyms.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mMeronyms.postValue(null);
            }
        }
        //Search query in background thread
        private Call<List<MeronymModel>> getMeronyms(String word) {
            return Services.getWordApi().getMeronyms(word, 200);
        }
        private void cancelRequest() {
            Log.v("Tag", "Cancelling Search Request");
            cancelRequest = true;
        }
    }
    private class RetrieveHolonymsRunnable implements Runnable {

        private String word;
        boolean cancelRequest;

        public RetrieveHolonymsRunnable(String word) {
            this.word = word;
            cancelRequest = false;
        }

        @Override
        public void run() {
            //Getting response objects
            try {
                Response response = getHolonyms(word).execute();
                if (cancelRequest) {
                    return;
                }
                if(response.code() == 200) {
                    List<HolonymModel> wordsList = new ArrayList<>(((HolonymsResponse)response.body()).getHolonyms());
                    //Sending data to live data
                    //PostValue: used for background thread
                    //setValue: not used for background thread
                    mHolonyms.postValue(wordsList);

                }
                else {
                    String error = response.errorBody().string();
                    Log.v("Tag", "Error: " + error);
                    mHolonyms.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mHolonyms.postValue(null);
            }
        }
        //Search query in background thread
        private Call<List<HolonymModel>> getHolonyms(String word) {
            return Services.getWordApi().getHolonyms(word, 200);
        }
        private void cancelRequest() {
            Log.v("Tag", "Cancelling Search Request");
            cancelRequest = true;
        }
    }
    private class RetrieveRhymingWordsRunnable implements Runnable {

        private String word;
        boolean cancelRequest;

        public RetrieveRhymingWordsRunnable(String word) {
            this.word = word;
            cancelRequest = false;
        }

        @Override
        public void run() {
            //Getting response objects
            try {
                Response response = getRhymingWords(word).execute();
                if (cancelRequest) {
                    return;
                }
                if(response.code() == 200) {
                    List<RhymingWordsModel> wordsList = new ArrayList<>(((RhymingWordsResponse)response.body()).getWords());
                    //Sending data to live data
                    //PostValue: used for background thread
                    //setValue: not used for background thread
                    mRhymingWords.postValue(wordsList);

                }
                else {
                    String error = response.errorBody().string();
                    Log.v("Tag", "Error: " + error);
                    mRhymingWords.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mRhymingWords.postValue(null);
            }
        }
        //Search query in background thread
        private Call<List<RhymingWordsModel>> getRhymingWords(String word) {
            return Services.getWordApi().getRhymingWords(word, 200);
        }
        private void cancelRequest() {
            Log.v("Tag", "Cancelling Search Request");
            cancelRequest = true;
        }
    }
    private class RetrieveHomophopnesRunnable implements Runnable {

        private String word;
        boolean cancelRequest;

        public RetrieveHomophopnesRunnable(String word) {
            this.word = word;
            cancelRequest = false;
        }

        @Override
        public void run() {
            //Getting response objects
            try {
                Response response = getHomophones(word).execute();
                if (cancelRequest) {
                    return;
                }
                if(response.code() == 200) {
                    List<HomophoneModel> wordsList = new ArrayList<>(((HomophonesResponse)response.body()).getHomophones());
                    //Sending data to live data
                    //PostValue: used for background thread
                    //setValue: not used for background thread
                    mHomophones.postValue(wordsList);

                }
                else {
                    String error = response.errorBody().string();
                    Log.v("Tag", "Error: " + error);
                    mHomophones.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mHomophones.postValue(null);
            }
        }
        //Search query in background thread
        private Call<List<HomophoneModel>> getHomophones(String word) {
            return Services.getWordApi().getHomophonesWords(word, 200);
        }
        private void cancelRequest() {
            Log.v("Tag", "Cancelling Search Request");
            cancelRequest = true;
        }
    }
    private class RetrieveFrequentFolloweringWords implements Runnable {
        private String word;
        boolean cancelRequest;

        public RetrieveFrequentFolloweringWords(String word) {
            this.word = word;
            cancelRequest = false;
        }

        @Override
        public void run() {
            //Getting response objects
            try {
                Response response = getFrequentFollowingWords(word).execute();
                if (cancelRequest) {
                    return;
                }
                if(response.code() == 200) {
                    List<FrequentFollowingWordsModel> wordsList = new ArrayList<>(((FrequentFollowingWordsResponse)response.body()).getFrequentFollowingWords());
                    //Sending data to live data
                    //PostValue: used for background thread
                    //setValue: not used for background thread
                    mFrequentFollowers.postValue(wordsList);

                }
                else {
                    String error = response.errorBody().string();
                    Log.v("Tag", "Error: " + error);
                    mFrequentFollowers.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mFrequentFollowers.postValue(null);
            }
        }
        //Search query in background thread
        private Call<List<FrequentFollowingWordsModel>> getFrequentFollowingWords(String word) {
            return Services.getWordApi().getFrequentFollowingWords(word, 200);
        }
        private void cancelRequest() {
            Log.v("Tag", "Cancelling Search Request");
            cancelRequest = true;
        }
    }
    private class RetrieveFrequentPrecedingWords implements Runnable {
        private String word;
        boolean cancelRequest;

        public RetrieveFrequentPrecedingWords(String word) {
            this.word = word;
            cancelRequest = false;
        }

        @Override
        public void run() {
            //Getting response objects
            try {
                Response response = getFrequentPrecedingWords(word).execute();
                if (cancelRequest) {
                    return;
                }
                if(response.code() == 200) {
                    List<FrequentPrecedingWordsModel> wordsList = new ArrayList<>(((FrequentPrecedingWordsResponse)response.body()).getFrequentPrecedingWords());
                    //Sending data to live data
                    //PostValue: used for background thread
                    //setValue: not used for background thread
                    mFrequentpredecessors.postValue(wordsList);

                }
                else {
                    String error = response.errorBody().string();
                    Log.v("Tag", "Error: " + error);
                    mFrequentpredecessors.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mFrequentpredecessors.postValue(null);
            }
        }
        //Search query in background thread
        private Call<List<FrequentPrecedingWordsModel>> getFrequentPrecedingWords(String word) {
            return Services.getWordApi().getFrequentPrecedingWords(word, 200);
        }
        private void cancelRequest() {
            Log.v("Tag", "Cancelling Search Request");
            cancelRequest = true;
        }
    }
    private class RetrieveSyllables implements Runnable {
        private String word;
        private String metaData;
        boolean cancelRequest;

        public RetrieveSyllables(String word) {
            this.word = word;
            this.metaData = metaData;
            cancelRequest = false;
        }

        @Override
        public void run() {
            //Getting response objects
            try {
                Response response = getSyllables(word).execute();
                if (cancelRequest) {
                    return;
                }
                if(response.code() == 200) {
                    Integer numOfSyllables = ((SecretWordSyllableModel)response.body()).getNumSyllables();
                    //Sending data to live data
                    //PostValue: used for background thread
                    //setValue: not used for background thread
                    mSyllables.postValue(numOfSyllables);

                }
                else {
                    String error = response.errorBody().string();
                    Log.v("Tag", "Error: " + error);
                    mSyllables.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mSyllables.postValue(null);
            }
        }
        //Search query in background thread
        private Call<List<SecretWordSyllableModel>> getSyllables(String word) {
            return Services.getWordApi().getSyllables(word, metaData, 1);
        }
        private void cancelRequest() {
            Log.v("Tag", "Cancelling Search Request");
            cancelRequest = true;
        }
    }
    private class RetrievePartsOfSpeech implements Runnable {
        private String word;
        private String metaData;
        boolean cancelRequest;

        public RetrievePartsOfSpeech(String word) {
            this.word = word;
            this.metaData = metaData;
            cancelRequest = false;
        }

        @Override
        public void run() {
            //Getting response objects
            try {
                Response response = getPartsOfSpeech(word).execute();
                if (cancelRequest) {
                    return;
                }
                if(response.code() == 200) {
                    List<String> partsOfSpeech = ((SecretWordPartOfSpeechModel)response.body()).getTags();
                    //Sending data to live data
                    //PostValue: used for background thread
                    //setValue: not used for background thread
                    mPartsOfSpeech.postValue(partsOfSpeech);

                }
                else {
                    String error = response.errorBody().string();
                    Log.v("Tag", "Error: " + error);
                    mPartsOfSpeech.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mPartsOfSpeech.postValue(null);
            }
        }
        //Search query in background thread
        private Call<List<SecretWordPartOfSpeechModel>> getPartsOfSpeech(String word) {
            return Services.getWordApi().getPartsOfSpeech(word, metaData, 1);
        }
        private void cancelRequest() {
            Log.v("Tag", "Cancelling Search Request");
            cancelRequest = true;
        }
    }
    private class RetrieveDefinitions implements Runnable {
        private String word;
        private String metaData;
        boolean cancelRequest;

        public RetrieveDefinitions(String word) {
            this.word = word;
            this.metaData = metaData;
            cancelRequest = false;
        }

        @Override
        public void run() {
            //Getting response objects
            try {
                Response response = getDefinitions(word).execute();
                if (cancelRequest) {
                    return;
                }
                if(response.code() == 200) {
                    List<String> definitions = ((SecretWordDefinitionModel)response.body()).getDefs();
                    //Sending data to live data
                    //PostValue: used for background thread
                    //setValue: not used for background thread
                    mDefinitions.postValue(definitions);

                }
                else {
                    String error = response.errorBody().string();
                    Log.v("Tag", "Error: " + error);
                    mDefinitions.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mDefinitions.postValue(null);
            }
        }
        //Search query in background thread
        private Call<List<SecretWordDefinitionModel>> getDefinitions(String word) {
            return Services.getWordApi().getDefinitions(word, metaData, 1);
        }
        private void cancelRequest() {
            Log.v("Tag", "Cancelling Search Request");
            cancelRequest = true;
        }
    }
    private class RetrieveTopicWordsRunnable implements Runnable {

        private String word;
        boolean cancelRequest;

        public RetrieveTopicWordsRunnable(String word) {
            this.word = word;
            cancelRequest = false;
        }

        @Override
        public void run() {
            //Getting response objects
            try {
                Response response = getTopicWords(word).execute();
                if (cancelRequest) {
                    return;
                }
                if(response.code() == 200) {
                    List<SimilarWordsModel> wordsList = new ArrayList<>(((SimilarWordsResponse)response.body()).getWords());
                    //Sending data to live data
                    //PostValue: used for background thread
                    //setValue: not used for background thread
                    mTopicWords.postValue(wordsList);

                }
                else {
                    String error = response.errorBody().string();
                    Log.v("Tag", "Error: " + error);
                    mTopicWords.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mTopicWords.postValue(null);
            }
        }
        //Search query in background thread
        private Call<List<SimilarWordsModel>> getTopicWords(String word) {
            return Services.getWordApi().getSimilarWords(word, 300);
        }
        private void cancelRequest() {
            Log.v("Tag", "Cancelling Search Request");
            cancelRequest = true;
        }
    }

}
