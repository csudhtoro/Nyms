package com.example.nyms.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nyms.R;
import com.example.nyms.adapters.GuessAdapter;
import com.example.nyms.adapters.LastGuessAdapter;
import com.example.nyms.models.AntonymModel;
import com.example.nyms.models.FrequentFollowingWordsModel;
import com.example.nyms.models.FrequentPrecedingWordsModel;
import com.example.nyms.models.GuessModel;
import com.example.nyms.models.HolonymModel;
import com.example.nyms.models.HomophoneModel;
import com.example.nyms.models.HypernymModel;
import com.example.nyms.models.HyponymModel;
import com.example.nyms.models.MeronymModel;
import com.example.nyms.models.RhymingWordsModel;
import com.example.nyms.models.SecretWordDefinitionModel;
import com.example.nyms.models.SimilarWordsModel;
import com.example.nyms.models.SynonymModel;
import com.example.nyms.models.TriggerWordModel;
import com.example.nyms.models.VictoryInfoModel;
import com.example.nyms.requests.Services;
import com.example.nyms.utils.WordApi;
import com.github.dhiraj072.randomwordgenerator.RandomWordGenerator;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputLayout;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

import kotlin.Triple;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class GameFragment extends Fragment {

    String secretWord;
    List<String> secretWordDefinitions;
    int guessCount = 0;



    PriorityQueue<Triple<String, String, Integer>> relatedWordsMaxHeap;
    Set<String> duplicateCheckerSet;
    Map<String, Triple<String, String, Integer>> relatedWords;
    ArrayList<GuessModel> guessList;
    Set<String> submittedGuesses;
    Map<String, String> topRelatedWords;

    String[] RESTRICTED_WORDS = new String[] {
            "this", "that", "with", "very", "from", "would", "wont", "just", "were", "shall",
            "which", "where", "when", "whom", "whose", "what", "who", "for", "how", "but", "and",
            "any", "for", "the", "thy", "nor", "yet", "nor", "our", "him", "her", "you", "she", "his",
            "has", "get"
    };
    Set<String> restrictedWords;
    GuessModel lastGuess;

    //RecyclerView
    private RecyclerView guessRecyclerView;
    private RecyclerView lastGuessRecyclerView;


    private TextInputLayout textInputField;
    private MaterialButton guessButton;
    private MaterialButton giveup_button;
    private MaterialCardView guessesRvCardView;
    private MaterialButton hintButton;


    private GuessAdapter guessRecyclerViewAdapter;
    private LastGuessAdapter lastGuessRecyclerViewAdapter;

    TextView meansLike;
    TextView partOfSpeech;
    TextView numOfWords;
    TextView borderDivider;
    TextView hintIcon;
    TextView lastGuessLbl;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<String> wordList = Arrays.asList(getResources().getStringArray(R.array.SecretWordList));
        Collections.shuffle(wordList);

        Stack<String> wordStack = new Stack<>();
        wordStack.addAll(wordList);


        //Generate random word
        secretWord = RandomWordGenerator.getRandomWord();
        secretWord = wordStack.pop();
        Log.v("Tag", "Secret Word: "+secretWord);


        relatedWordsMaxHeap = new PriorityQueue<Triple<String, String, Integer>>(new Comparator<Triple<String, String, Integer>>() {
            @Override
            public int compare(Triple<String, String, Integer> t1, Triple<String, String, Integer> t2) {
                if(t1.component3() < t2.component3()) return 1;
                else if(t1.component3() > t2.component3()) return -1;
                else return 0;
            }
        });

        //run api to retrieve related words and hint of the secret word
        GetRelatedWordsAndHints(secretWord);

        duplicateCheckerSet = new HashSet<>();
        guessList = new ArrayList<>();
        submittedGuesses = new HashSet<>();
        restrictedWords = new HashSet<>(Arrays.asList(RESTRICTED_WORDS));
        topRelatedWords = new HashMap<>();


        hintIcon = (TextView) getView().findViewById(R.id.hint_icon);

        meansLike = (TextView) getView().findViewById(R.id.means_like);
        meansLike.setVisibility(View.GONE);

        partOfSpeech = (TextView) getView().findViewById(R.id.part_of_speech);
        partOfSpeech.setVisibility(View.GONE);

        textInputField = (TextInputLayout) getView().findViewById(R.id.guess_text_field);
        textInputField.setVisibility(View.GONE);

        guessButton = (MaterialButton) getView().findViewById(R.id.guess_button);
        guessButton.setVisibility((View.GONE));

        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CheckGuess();
                clearText(textInputField);
            }
        });

        giveup_button = (MaterialButton) getView().findViewById(R.id.giveup_button);
        giveup_button.setVisibility(View.GONE);
        giveup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<String> relatedWordsList = new ArrayList<>();
                for (Map.Entry<String, Triple<String, String, Integer>> triplet : relatedWords.entrySet()) {
                    relatedWordsList.add(triplet.getKey());
                }
                VictoryInfoModel victoryInfo = new VictoryInfoModel(secretWord, 22, guessCount, 5, new ArrayList<>(relatedWordsList), secretWordDefinitions);
                openQuitDialog(victoryInfo);
                guessCount = 0;
            }
        });

        hintButton = (MaterialButton) getView().findViewById(R.id.hint_button);
        hintButton.setVisibility(View.GONE);
        hintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddHintToRecyclerView();
            }
        });

        guessesRvCardView = (MaterialCardView) getView().findViewById(R.id.guesses_recyclerview_card);
        guessesRvCardView.setVisibility(View.GONE);

        //Get guess recyclerview
        guessRecyclerView = (RecyclerView) getView().findViewById(R.id.guessRecyclerView);
        ((SimpleItemAnimator) guessRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        guessRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Get last guess recyclerview
        lastGuessRecyclerView = (RecyclerView) getView().findViewById(R.id.last_guess_recyclerview);
        ((SimpleItemAnimator) lastGuessRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        lastGuessRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


    }

    private Map<String, Triple<String, String, Integer>> setUpRelatedWordsMap (PriorityQueue<Triple<String, String, Integer>> relatedWordHeap) {
        int pop = 1;
        int size = relatedWordHeap.size();
        Map<String, Triple<String, String, Integer>> relatedList = new HashMap<>();


        while(!relatedWordHeap.isEmpty()) {
            Triple currTriple = relatedWordHeap.poll();
            Triple<String, String, Integer> tempTriple = new Triple<String, String, Integer>(currTriple.component1().toString(), currTriple.component2().toString(),size - pop);
            relatedList.put(tempTriple.component1(), tempTriple);
            pop++;
        }
        return relatedList;
    }

    private void GetRelatedWordsAndHints(String secretWord) {
        WordApi wordApi = Services.getWordApi();

        Call<List<SimilarWordsModel>> responseCall = wordApi
                .getSimilarWords(
                        secretWord,
                        1000
                );

        responseCall.enqueue(new Callback<List<SimilarWordsModel>>() {
            @Override
            public void onResponse(Call<List<SimilarWordsModel>> call, Response<List<SimilarWordsModel>> response) {
                if(response.code() == 200) {

                    List<SimilarWordsModel> SimilarWordList = new ArrayList<>(response.body());
                    for(SimilarWordsModel word : SimilarWordList) {
                        if(word.getScore() != null) {
                            Triple<String, String, Integer> tempTriple = new Triple<>(word.getWord(),"Similar to this", word.getScore());
                            if(!duplicateCheckerSet.contains(tempTriple.component1())) {
                                duplicateCheckerSet.add(tempTriple.component1());
                                relatedWordsMaxHeap.add(tempTriple);
                                if(!topRelatedWords.containsValue("Similar to this")) {
                                    if(tempTriple.getFirst().length() > 2 && !restrictedWords.contains(tempTriple.getFirst())) {
                                        Pair<String, String> tempPair = new Pair(tempTriple.getFirst(), tempTriple.getSecond());
                                        topRelatedWords.put(tempPair.first, tempPair.second);
                                    }
                                }
                            }
                        }
                        else {
                            Triple<String, String, Integer> tempTriple = new Triple<>(word.getWord(),"Similar to this", 1);
                            if(!duplicateCheckerSet.contains(tempTriple.component1())) {
                                duplicateCheckerSet.add(tempTriple.component1());
                                relatedWordsMaxHeap.add(tempTriple);
                                if(!topRelatedWords.containsValue("Similar to this")) {
                                    if(tempTriple.getFirst().length() > 2 && !restrictedWords.contains(tempTriple.getFirst())) {
                                        Pair<String, String> tempPair = new Pair(tempTriple.getFirst(), tempTriple.getSecond());
                                        topRelatedWords.put(tempPair.first, tempPair.second);
                                    }
                                }
                            }
                        }
                    }
                    GetSynonyms(secretWord);
                }
            }

            @Override
            public void onFailure(Call<List<SimilarWordsModel>> call, Throwable t) {
                Log.v("Tag", "Failed!");
                t.printStackTrace();
            }

        });
    }
    private void GetSynonyms(String secretWord) {
        WordApi wordApi = Services.getWordApi();

        Call<List<SynonymModel>> responseCall = wordApi
                .getSynonymWords(
                        secretWord,
                        1000
                );

        responseCall.enqueue(new Callback<List<SynonymModel>>() {
            @Override
            public void onResponse(Call<List<SynonymModel>> call, Response<List<SynonymModel>> response) {
                if(response.code() == 200) {

                    List<SynonymModel> SynonymList = new ArrayList<>(response.body());
                    for(SynonymModel word : SynonymList) {
                        boolean addedHint = false;
                        if(word.getScore() != null) {
                            Triple<String, String, Integer> tempTriple = new Triple<>(word.getWord(),"Synonym", word.getScore());
                            if(!duplicateCheckerSet.contains(tempTriple.component1())) {
                                duplicateCheckerSet.add(tempTriple.component1());
                                relatedWordsMaxHeap.add(tempTriple);
                                if(!topRelatedWords.containsValue("Synonym")) {
                                    if(tempTriple.getFirst().length() > 2 && !restrictedWords.contains(tempTriple.getFirst())) {
                                        Pair<String, String> tempPair = new Pair(tempTriple.getFirst(), tempTriple.getSecond());
                                        topRelatedWords.put(tempPair.first, tempPair.second);
                                    }
                                }
                            }
                        }
                        else {
                            Triple<String, String, Integer> tempTriple = new Triple<>(word.getWord(),"Synonym", 1);
                            if(!duplicateCheckerSet.contains(tempTriple.component1())) {
                                duplicateCheckerSet.add(tempTriple.component1());
                                relatedWordsMaxHeap.add(tempTriple);
                                if(!topRelatedWords.containsValue("Synonym")) {
                                    if(tempTriple.getFirst().length() > 2 && !restrictedWords.contains(tempTriple.getFirst())) {
                                        Pair<String, String> tempPair = new Pair(tempTriple.getFirst(), tempTriple.getSecond());
                                        topRelatedWords.put(tempPair.first, tempPair.second);
                                    }
                                }
                            }
                        }
                    }
                    GetAntonyms(secretWord);
                }
            }

            @Override
            public void onFailure(Call<List<SynonymModel>> call, Throwable t) {
                Log.v("Tag", "Failed!");
                t.printStackTrace();
            }
        });
    }
    private void GetAntonyms(String secretWord) {
        WordApi wordApi = Services.getWordApi();

        Call<List<AntonymModel>> responseCall = wordApi
                .getAntonymWords(
                        secretWord,
                        1000
                );

        responseCall.enqueue(new Callback<List<AntonymModel>>() {
            @Override
            public void onResponse(Call<List<AntonymModel>> call, Response<List<AntonymModel>> response) {
                if(response.code() == 200) {

                    List<AntonymModel> AntonymList = new ArrayList<>(response.body());
                    for(AntonymModel word : AntonymList) {
                        boolean addedHint = false;
                        if(word.getScore() != null) {
                            Triple<String, String, Integer> tempTriple = new Triple<>(word.getWord(),"Antonym", word.getScore());
                            if(!duplicateCheckerSet.contains(tempTriple.component1())) {
                                duplicateCheckerSet.add(tempTriple.component1());
                                relatedWordsMaxHeap.add(tempTriple);
                                if(!topRelatedWords.containsValue("Antonym")) {
                                    if(tempTriple.getFirst().length() > 2 && !restrictedWords.contains(tempTriple.getFirst())) {
                                        Pair<String, String> tempPair = new Pair(tempTriple.getFirst(), tempTriple.getSecond());
                                        topRelatedWords.put(tempPair.first, tempPair.second);
                                    }
                                }
                            }
                        }
                        else {
                            Triple<String, String, Integer> tempTriple = new Triple<>(word.getWord(),"Antonym", 1);
                            if(!duplicateCheckerSet.contains(tempTriple.component1())) {
                                duplicateCheckerSet.add(tempTriple.component1());
                                relatedWordsMaxHeap.add(tempTriple);
                                if(!topRelatedWords.containsValue("Antonym")) {
                                    if(tempTriple.getFirst().length() > 2 && !restrictedWords.contains(tempTriple.getFirst())) {
                                        Pair<String, String> tempPair = new Pair(tempTriple.getFirst(), tempTriple.getSecond());
                                        topRelatedWords.put(tempPair.first, tempPair.second);
                                    }
                                }
                            }
                        }
                    }
                    GetTriggerWords(secretWord);
                }
            }

            @Override
            public void onFailure(Call<List<AntonymModel>> call, Throwable t) {
                Log.v("Tag", "Failed!");
                t.printStackTrace();
            }
        });
    }
    private void GetTriggerWords(String secretWord) {
        WordApi wordApi = Services.getWordApi();

        Call<List<TriggerWordModel>> responseCall = wordApi
                .getTriggerWords(
                        secretWord,
                        1000
                );

        responseCall.enqueue(new Callback<List<TriggerWordModel>>() {
            @Override
            public void onResponse(Call<List<TriggerWordModel>> call, Response<List<TriggerWordModel>> response) {
                if(response.code() == 200) {

                    List<TriggerWordModel> TriggerWordList = new ArrayList<>(response.body());
                    for(TriggerWordModel word : TriggerWordList) {
                        boolean addedHint = false;
                        Triple<String, String, Integer> tempTriple = new Triple<>(word.getWord(),"Trigger Word", word.getScore());
                        if(!duplicateCheckerSet.contains(tempTriple.component1())) {
                            duplicateCheckerSet.add(tempTriple.component1());
                            relatedWordsMaxHeap.add(tempTriple);
                            if(meansLike.getText().toString().equals("") && (tempTriple.component3() > 1100 && tempTriple.component3() < 1700)) {
                                meansLike.setText(tempTriple.component1());
                                partOfSpeech.setText("(" + tempTriple.component2() + ")");
                            }
                            if(!topRelatedWords.containsValue("Trigger Word")) {
                                if(tempTriple.getFirst().length() > 2 && !restrictedWords.contains(tempTriple.getFirst())) {
                                    Pair<String, String> tempPair = new Pair(tempTriple.getFirst(), tempTriple.getSecond());
                                    topRelatedWords.put(tempPair.first, tempPair.second);
                                }
                            }
                        }
                    }
                    GetHypernyms(secretWord);
                }
            }

            @Override
            public void onFailure(Call<List<TriggerWordModel>> call, Throwable t) {
                Log.v("Tag", "Failed!");
                t.printStackTrace();
            }
        });
    }
    private void GetHypernyms(String secretWord) {
        WordApi wordApi = Services.getWordApi();

        Call<List<HypernymModel>> responseCall = wordApi
                .getHypernyms(
                        secretWord,
                        1000
                );

        responseCall.enqueue(new Callback<List<HypernymModel>>() {
            @Override
            public void onResponse(Call<List<HypernymModel>> call, Response<List<HypernymModel>> response) {
                if(response.code() == 200) {

                    List<HypernymModel> HypernymList = new ArrayList<>(response.body());
                    for(HypernymModel word : HypernymList) {
                        boolean addedHint = false;
                        if(word.getScore() != null) {
                            Triple<String, String, Integer> tempTriple = new Triple<>(word.getWord(),"Kind of like this", word.getScore());
                            if(!duplicateCheckerSet.contains(tempTriple.component1())) {
                                duplicateCheckerSet.add(tempTriple.component1());
                                relatedWordsMaxHeap.add(tempTriple);
                                if(!topRelatedWords.containsValue("Kind of like this")) {
                                    if(tempTriple.getFirst().length() > 2 && !restrictedWords.contains(tempTriple.getFirst())) {
                                        Pair<String, String> tempPair = new Pair(tempTriple.getFirst(), tempTriple.getSecond());
                                        topRelatedWords.put(tempPair.first, tempPair.second);
                                    }
                                }
                            }
                        }
                        else {
                            Triple<String, String, Integer> tempTriple = new Triple<>(word.getWord(),"Kind of like this", 1);
                            if(!duplicateCheckerSet.contains(tempTriple.component1())) {
                                duplicateCheckerSet.add(tempTriple.component1());
                                relatedWordsMaxHeap.add(tempTriple);
                                if(!topRelatedWords.containsValue("Kind of like this")) {
                                    if(tempTriple.getFirst().length() > 2 && !restrictedWords.contains(tempTriple.getFirst())) {
                                        Pair<String, String> tempPair = new Pair(tempTriple.getFirst(), tempTriple.getSecond());
                                        topRelatedWords.put(tempPair.first, tempPair.second);
                                    }
                                }
                            }
                        }
                    }
                    GetHyponyms(secretWord);
                }
            }

            @Override
            public void onFailure(Call<List<HypernymModel>> call, Throwable t) {
                Log.v("Tag", "Failed!");
                t.printStackTrace();
            }
        });
    }
    private void GetHyponyms(String secretWord) {
        WordApi wordApi = Services.getWordApi();

        Call<List<HyponymModel>> responseCall = wordApi
                .getHyponyms(
                        secretWord,
                        1000
                );

        responseCall.enqueue(new Callback<List<HyponymModel>>() {
            @Override
            public void onResponse(Call<List<HyponymModel>> call, Response<List<HyponymModel>> response) {
                if(response.code() == 200) {

                    List<HyponymModel> HyponymList = new ArrayList<>(response.body());
                    for(HyponymModel word : HyponymList) {
                        boolean addedHint = false;
                        if(word.getScore() != null) {
                            Triple<String, String, Integer> tempTriple = new Triple<>(word.getWord(),"More general than this", word.getScore());
                            if(!duplicateCheckerSet.contains(tempTriple.component1())) {
                                duplicateCheckerSet.add(tempTriple.component1());
                                relatedWordsMaxHeap.add(tempTriple);
                                if(!topRelatedWords.containsValue("More general than this")) {
                                    if(tempTriple.getFirst().length() > 2 && !restrictedWords.contains(tempTriple.getFirst())) {
                                        Pair<String, String> tempPair = new Pair(tempTriple.getFirst(), tempTriple.getSecond());
                                        topRelatedWords.put(tempPair.first, tempPair.second);
                                    }
                                }
                            }
                        }
                        else {
                            Triple<String, String, Integer> tempTriple = new Triple<>(word.getWord(),"More general than this", 1);
                            if(!duplicateCheckerSet.contains(tempTriple.component1())) {
                                duplicateCheckerSet.add(tempTriple.component1());
                                relatedWordsMaxHeap.add(tempTriple);
                                if(!topRelatedWords.containsValue("More general than this")) {
                                    if(tempTriple.getFirst().length() > 2 && !restrictedWords.contains(tempTriple.getFirst())) {
                                        Pair<String, String> tempPair = new Pair(tempTriple.getFirst(), tempTriple.getSecond());
                                        topRelatedWords.put(tempPair.first, tempPair.second);
                                    }
                                }
                            }
                        }
                    }
                    GetMeronyms(secretWord);
                }
            }

            @Override
            public void onFailure(Call<List<HyponymModel>> call, Throwable t) {
                Log.v("Tag", "Failed!");
                t.printStackTrace();
            }
        });
    }
    private void GetMeronyms(String secretWord) {
        WordApi wordApi = Services.getWordApi();

        Call<List<MeronymModel>> responseCall = wordApi
                .getMeronyms(
                        secretWord,
                        1000
                );

        responseCall.enqueue(new Callback<List<MeronymModel>>() {
            @Override
            public void onResponse(Call<List<MeronymModel>> call, Response<List<MeronymModel>> response) {
                if(response.code() == 200) {

                    List<MeronymModel> MeronymList = new ArrayList<>(response.body());
                    for(MeronymModel word : MeronymList) {
                        boolean addedHint = false;
                        if(word.getScore() != null) {
                            Triple<String, String, Integer> tempTriple = new Triple<>(word.getWord(),"A part of this", word.getScore());
                            if(!duplicateCheckerSet.contains(tempTriple.component1())) {
                                duplicateCheckerSet.add(tempTriple.component1());
                                relatedWordsMaxHeap.add(tempTriple);
                                if(!topRelatedWords.containsValue("A part of this")) {
                                    if(tempTriple.getFirst().length() > 2 && !restrictedWords.contains(tempTriple.getFirst())) {
                                        Pair<String, String> tempPair = new Pair(tempTriple.getFirst(), tempTriple.getSecond());
                                        topRelatedWords.put(tempPair.first, tempPair.second);
                                    }
                                }
                            }
                        }
                        else {
                            Triple<String, String, Integer> tempTriple = new Triple<>(word.getWord(),"A part of this", 1);
                            if(!duplicateCheckerSet.contains(tempTriple.component1())) {
                                duplicateCheckerSet.add(tempTriple.component1());
                                relatedWordsMaxHeap.add(tempTriple);
                                if(!topRelatedWords.containsValue("A part of this")) {
                                    if(tempTriple.getFirst().length() > 2 && !restrictedWords.contains(tempTriple.getFirst())) {
                                        Pair<String, String> tempPair = new Pair(tempTriple.getFirst(), tempTriple.getSecond());
                                        topRelatedWords.put(tempPair.first, tempPair.second);
                                    }
                                }
                            }
                        }
                    }
                    GetHolonyms(secretWord);
                }
            }

            @Override
            public void onFailure(Call<List<MeronymModel>> call, Throwable t) {
                Log.v("Tag", "Failed!");
                t.printStackTrace();
            }
        });
    }
    private void GetHolonyms(String secretWord) {
        WordApi wordApi = Services.getWordApi();

        Call<List<HolonymModel>> responseCall = wordApi
                .getHolonyms(
                        secretWord,
                        1000
                );

        responseCall.enqueue(new Callback<List<HolonymModel>>() {
            @Override
            public void onResponse(Call<List<HolonymModel>> call, Response<List<HolonymModel>> response) {
                if(response.code() == 200) {

                    List<HolonymModel> HolonymList = new ArrayList<>(response.body());
                    for(HolonymModel word : HolonymList) {
                        boolean addedHint = false;
                        if(word.getScore() != null) {
                            Triple<String, String, Integer> tempTriple = new Triple<>(word.getWord(),"Contains this", word.getScore());
                            if(!duplicateCheckerSet.contains(tempTriple.component1())) {
                                duplicateCheckerSet.add(tempTriple.component1());
                                relatedWordsMaxHeap.add(tempTriple);
                                if(!topRelatedWords.containsValue("Contains this")) {
                                    if(tempTriple.getFirst().length() > 2 && !restrictedWords.contains(tempTriple.getFirst())) {
                                        Pair<String, String> tempPair = new Pair(tempTriple.getFirst(), tempTriple.getSecond());
                                        topRelatedWords.put(tempPair.first, tempPair.second);
                                    }
                                }
                            }
                        }
                        else {
                            Triple<String, String, Integer> tempTriple = new Triple<>(word.getWord(),"Contains this", 1);
                            if(!duplicateCheckerSet.contains(tempTriple.component1())) {
                                duplicateCheckerSet.add(tempTriple.component1());
                                relatedWordsMaxHeap.add(tempTriple);
                                if(!topRelatedWords.containsValue("Contains this")) {
                                    if(tempTriple.getFirst().length() > 2 && !restrictedWords.contains(tempTriple.getFirst())) {
                                        Pair<String, String> tempPair = new Pair(tempTriple.getFirst(), tempTriple.getSecond());
                                        topRelatedWords.put(tempPair.first, tempPair.second);
                                    }
                                }
                            }
                        }
                    }
                    GetRhymingWords(secretWord);
                }
            }

            @Override
            public void onFailure(Call<List<HolonymModel>> call, Throwable t) {
                Log.v("Tag", "Failed!");
                t.printStackTrace();
            }
        });
    }
    private void GetRhymingWords(String secretWord) {
        WordApi wordApi = Services.getWordApi();

        Call<List<RhymingWordsModel>> responseCall = wordApi
                .getRhymingWords(
                        secretWord,
                        1000
                );

        responseCall.enqueue(new Callback<List<RhymingWordsModel>>() {
            @Override
            public void onResponse(Call<List<RhymingWordsModel>> call, Response<List<RhymingWordsModel>> response) {
                if(response.code() == 200) {

                    List<RhymingWordsModel> RhymingList = new ArrayList<>(response.body());
                    for(RhymingWordsModel word : RhymingList) {
                        if(word.getScore() != null) {
                            Triple<String, String, Integer> tempTriple = new Triple<>(word.getWord(),"Rhymes with this", word.getScore());
                            if(!duplicateCheckerSet.contains(tempTriple.component1())) {
                                duplicateCheckerSet.add(tempTriple.component1());
                                relatedWordsMaxHeap.add(tempTriple);
                                if(meansLike.getText().toString().equals("") && (tempTriple.component3() > 100 && tempTriple.component3() < 3500)) {
                                    meansLike.setText(tempTriple.component1());
                                    partOfSpeech.setText("(" + tempTriple.component2() + ")");
                                }
                                if(!topRelatedWords.containsValue("Rhymes with this")) {
                                    if(tempTriple.getFirst().length() > 2 && !restrictedWords.contains(tempTriple.getFirst())) {
                                        Pair<String, String> tempPair = new Pair(tempTriple.getFirst(), tempTriple.getSecond());
                                        topRelatedWords.put(tempPair.first, tempPair.second);
                                    }
                                }
                            }
                        }
                        else {
                            Triple<String, String, Integer> tempTriple = new Triple<>(word.getWord(),"Rhymes with this", 1);
                            if(!duplicateCheckerSet.contains(tempTriple.component1())) {
                                duplicateCheckerSet.add(tempTriple.component1());
                                relatedWordsMaxHeap.add(tempTriple);
                                if(!topRelatedWords.containsValue("Rhymes with this")) {
                                    if(tempTriple.getFirst().length() > 2 && !restrictedWords.contains(tempTriple.getFirst())) {
                                        Pair<String, String> tempPair = new Pair(tempTriple.getFirst(), tempTriple.getSecond());
                                        topRelatedWords.put(tempPair.first, tempPair.second);
                                    }
                                }
                            }
                        }
                    }
                    GetHomophones(secretWord);
                }
            }

            @Override
            public void onFailure(Call<List<RhymingWordsModel>> call, Throwable t) {
                Log.v("Tag", "Failed!");
                t.printStackTrace();
            }
        });
    }
    private void GetHomophones(String secretWord) {
        WordApi wordApi = Services.getWordApi();

        Call<List<HomophoneModel>> responseCall = wordApi
                .getHomophonesWords(
                        secretWord,
                        1000
                );

        responseCall.enqueue(new Callback<List<HomophoneModel>>() {
            @Override
            public void onResponse(Call<List<HomophoneModel>> call, Response<List<HomophoneModel>> response) {
                if(response.code() == 200) {

                    List<HomophoneModel> HomophoneList = new ArrayList<>(response.body());
                    for(HomophoneModel word : HomophoneList) {
                        boolean addedHint = false;
                        if(word.getScore() != null) {
                            Triple<String, String, Integer> tempTriple = new Triple<>(word.getWord(),"Sounds like this", word.getScore());
                            if(!duplicateCheckerSet.contains(tempTriple.component1())) {
                                duplicateCheckerSet.add(tempTriple.component1());
                                relatedWordsMaxHeap.add(tempTriple);
                                if(!topRelatedWords.containsValue("Sounds like this")) {
                                    if(tempTriple.getFirst().length() > 2 && !restrictedWords.contains(tempTriple.getFirst())) {
                                        Pair<String, String> tempPair = new Pair(tempTriple.getFirst(), tempTriple.getSecond());
                                        topRelatedWords.put(tempPair.first, tempPair.second);
                                    }
                                }
                            }
                        }
                        else {
                            Triple<String, String, Integer> tempTriple = new Triple<>(word.getWord(),"Sounds like this", 1);
                            if(!duplicateCheckerSet.contains(tempTriple.component1())) {
                                duplicateCheckerSet.add(tempTriple.component1());
                                relatedWordsMaxHeap.add(tempTriple);
                                if(!topRelatedWords.containsValue("Sounds like this")) {
                                    if(tempTriple.getFirst().length() > 2 && !restrictedWords.contains(tempTriple.getFirst())) {
                                        Pair<String, String> tempPair = new Pair(tempTriple.getFirst(), tempTriple.getSecond());
                                        topRelatedWords.put(tempPair.first, tempPair.second);
                                    }
                                }
                            }
                        }
                    }
                    GetSecretWordDefinitions(secretWord);
                }
            }

            @Override
            public void onFailure(Call<List<HomophoneModel>> call, Throwable t) {
                Log.v("Tag", "Failed!");
                t.printStackTrace();
            }
        });
    }

    private void GetTopics(String secretWord) {
        WordApi wordApi = Services.getWordApi();

        Call<List<SimilarWordsModel>> responseCall = wordApi
                .getSimilarTopics(
                        secretWord,
                        1000
                );

        responseCall.enqueue(new Callback<List<SimilarWordsModel>>() {
            @Override
            public void onResponse(Call<List<SimilarWordsModel>> call, Response<List<SimilarWordsModel>> response) {
                if(response.code() == 200) {

                    List<SimilarWordsModel> TopicList = new ArrayList<>(response.body());
                    for(SimilarWordsModel word : TopicList) {
                        boolean addedHint = false;
                        if(word.getScore() != null) {
                            Triple<String, String, Integer> tempTriple = new Triple<>(word.getWord(),"Similar topic to this", word.getScore());
                            if(!duplicateCheckerSet.contains(tempTriple.component1())) {
                                duplicateCheckerSet.add(tempTriple.component1());
                                relatedWordsMaxHeap.add(tempTriple);
                                if(!topRelatedWords.containsValue("Similar topic to this")) {
                                    if(tempTriple.getFirst().length() > 2 && !restrictedWords.contains(tempTriple.getFirst())) {
                                        Pair<String, String> tempPair = new Pair(tempTriple.getFirst(), tempTriple.getSecond());
                                        topRelatedWords.put(tempPair.first, tempPair.second);
                                    }
                                }
                            }
                        }
                        else {
                            Triple<String, String, Integer> tempTriple = new Triple<>(word.getWord(),"Similar topic to this", 1);
                            if(!duplicateCheckerSet.contains(tempTriple.component1())) {
                                duplicateCheckerSet.add(tempTriple.component1());
                                relatedWordsMaxHeap.add(tempTriple);
                                if(!topRelatedWords.containsValue("Similar topic to this")) {
                                    if(tempTriple.getFirst().length() > 2 && !restrictedWords.contains(tempTriple.getFirst())) {
                                        Pair<String, String> tempPair = new Pair(tempTriple.getFirst(), tempTriple.getSecond());
                                        topRelatedWords.put(tempPair.first, tempPair.second);
                                    }
                                }
                            }
                        }
                    }
                    relatedWords = setUpRelatedWordsMap(relatedWordsMaxHeap);
                    textInputField.setVisibility(View.VISIBLE);
                    guessButton.setVisibility((View.VISIBLE));
                    meansLike.setVisibility(View.VISIBLE);
                    partOfSpeech.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<SimilarWordsModel>> call, Throwable t) {
                Log.v("Tag", "Failed!");
                t.printStackTrace();
            }
        });
    }

    private void GetSecretWordDefinitions(String secretWord) {
        WordApi wordApi = Services.getWordApi();

        Call<List<SecretWordDefinitionModel>> responseCall = wordApi
                .getDefinitions(
                        secretWord,
                        "d",
                        1
                );

        responseCall.enqueue(new Callback<List<SecretWordDefinitionModel>>() {
            @Override
            public void onResponse(Call<List<SecretWordDefinitionModel>> call, Response<List<SecretWordDefinitionModel>> response) {
                if(response.code() == 200) {
                    if(!(response.body().size() <= 0)) {
                        if(response.body().get(0).getDefs() != null) {
                            secretWordDefinitions = new ArrayList<>(response.body().get(0).getDefs());
                        }
                        GetTopics(secretWord);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<SecretWordDefinitionModel>> call, Throwable t) {
                Log.v("Tag", "Failed!");
                t.printStackTrace();
            }
        });
    }


    private void DelayExecutionForTesting() {
        try {
            Thread.sleep(3000);
            Log.v("Tag", "Delay over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void CheckGuess() {
        String inputText = textInputField.getEditText().getText().toString().toLowerCase().trim();
        guessCount++;

        checkGuessedWordAndAddToRecyclerView(inputText, relatedWords, submittedGuesses, secretWord, guessCount, false);
    }

    private void PassGuessToRecyclerViews() {

        Collections.sort(guessList, new Comparator<GuessModel>() {
            @Override
            public int compare(GuessModel lhs, GuessModel rhs) {
                return lhs.getGuessScore() > rhs.getGuessScore() ? -1 :
                        (lhs.getGuessScore() < rhs.getGuessScore() ) ? 1 : 0;
            }
        });

        hintButton.setVisibility(View.VISIBLE);
        giveup_button.setVisibility(View.VISIBLE);

        //Set last guess recyclerview
        lastGuessRecyclerViewAdapter = new LastGuessAdapter(lastGuess, lastGuessRecyclerView);
        lastGuessRecyclerView.setAdapter(lastGuessRecyclerViewAdapter);
        lastGuessRecyclerViewAdapter.notifyDataSetChanged();

        //set guesslist recyclerview
        guessesRvCardView.setVisibility(View.VISIBLE);
        guessRecyclerViewAdapter = new GuessAdapter(guessList, guessRecyclerView);
        guessRecyclerView.setAdapter(guessRecyclerViewAdapter);
        guessRecyclerViewAdapter.notifyDataSetChanged();
    }

    public void clearText(View v) {
        textInputField.getEditText().getText().clear();
    }

    private void openVictoryDialog(VictoryInfoModel victoryInfo) {

        Bundle bundle = new Bundle();
        bundle.putParcelable("victoryInfo", victoryInfo);

        VictoryPopUpFragment victoryPopUpFragment = new VictoryPopUpFragment();
        victoryPopUpFragment.setArguments(bundle);
        victoryPopUpFragment.show(getFragmentManager(), "Victory");
    }

    private void AddHintToRecyclerView() {
        if(!topRelatedWords.isEmpty()) {
            guessCount++;
            String firstKey = topRelatedWords.keySet().iterator().next();
            topRelatedWords.remove(firstKey);

            checkGuessedWordAndAddToRecyclerView(firstKey, relatedWords, submittedGuesses, secretWord, guessCount, true);
        }
        else {
            Toast.makeText(getContext(), "No More Hints Available", Toast.LENGTH_LONG);
        }
    }

    private void checkGuessedWordAndAddToRecyclerView(String inputText, Map<String, Triple<String, String, Integer>> relatedWords, Set<String> submittedGuesses, String secretWord, int guessCount, boolean hint) {
        //If the current guess has not been guessed already
        if(!submittedGuesses.contains(inputText)) {
            submittedGuesses.add(inputText);

            //if the current guess == secret word
            if(inputText.equals(secretWord.toLowerCase().trim())) {
                //gather all the related words for victory fragment
                List<String> relatedWordsList = new ArrayList<>();
                for (Map.Entry<String, Triple<String, String, Integer>> triplet : relatedWords.entrySet()) {
                    relatedWordsList.add(triplet.getKey());
                }

                //create victory info object
                VictoryInfoModel victoryInfo = new VictoryInfoModel(secretWord, 22, guessCount, 5, new ArrayList<>(relatedWordsList), secretWordDefinitions);
                openVictoryDialog(victoryInfo);
                guessCount = 0;
            }

            //if current guess is a related word
            else if(relatedWords.containsKey(inputText)) {

                //Data to pass to guess card
                relatedWords.get(inputText);

                Triple guessedWord = relatedWords.get(inputText);
                String relation = guessedWord.component2().toString();
                int number = (Integer) guessedWord.component3();

                //calculating the guessed word score out of 100
                double score = ((double) number / (double) relatedWords.size()) * 100;


                lastGuess = new GuessModel((String.valueOf(guessCount)), inputText, relation, score);
                GuessModel currGuess = new GuessModel(String.valueOf(guessCount), inputText, relation, score);

                guessList.add(currGuess);

            }

            //the word is not the secret word, nor is it a related word
            else {

                double score = similarity("wave", "swim");

                GuessModel currGuess = new GuessModel(String.valueOf(guessCount), inputText, "Not Related", score);
                lastGuess = new GuessModel(String.valueOf(guessCount), inputText, "Not Related", score);
                guessList.add(currGuess);
            }
            PassGuessToRecyclerViews();
        } else {
            Toast.makeText(getContext(), "Guess already submitted", Toast.LENGTH_LONG);
        }
    }

    private void openQuitDialog(VictoryInfoModel victoryInfo) {

        Bundle bundle = new Bundle();
        bundle.putParcelable("quitInfo", victoryInfo);

        QuitPopUpFragment quitPopUpFragment = new QuitPopUpFragment();
        quitPopUpFragment.setArguments(bundle);
        quitPopUpFragment.show(getFragmentManager(), "Quitter");
    }

    public static double similarity(String s1, String s2) {
        String longer = s1, shorter = s2;
        if (s1.length() < s2.length()) { // longer should always have greater length
            longer = s2; shorter = s1;
        }
        int longerLength = longer.length();
        if (longerLength == 0) { return 1.0; /* both strings are zero length */ }
        return ((longerLength - editDistance(longer, shorter)) / (double) longerLength) * 100;

    }
    public static int editDistance(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        int[] costs = new int[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0)
                    costs[j] = j;
                else {
                    if (j > 0) {
                        int newValue = costs[j - 1];
                        if (s1.charAt(i - 1) != s2.charAt(j - 1))
                            newValue = Math.min(Math.min(newValue, lastValue),
                                    costs[j]) + 1;
                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if (i > 0)
                costs[s2.length()] = lastValue;
        }
        return costs[s2.length()];
    }

}