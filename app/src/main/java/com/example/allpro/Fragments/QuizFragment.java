package com.example.allpro.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.allpro.R;

public class QuizFragment extends Fragment {

    TextView question, scoreText, teamNameText;
    ImageView stadiumImage;

    RadioGroup radioGroup;
    RadioButton option1, option2, option3;

    Button btnSubmit, btnNext, btnBack;

    int score = 0;
    int questionIndex = 0;

    int[] images = {
            R.drawable.camp_nou,
            R.drawable.anfield,
            R.drawable.allianz_arena,
            R.drawable.old_trafford,
            R.drawable.santiago_bernabeu,
            R.drawable.stamford_bridge,
            R.drawable.parc_des_princes
    };

    String[] teams = {
            "FC Barcelona",
            "Liverpool FC",
            "Bayern Munich",
            "Manchester United",
            "Real Madrid",
            "Chelsea FC",
            "Paris Saint-Germain"
    };

    String[][] options = {
            {"Camp Nou","Anfield","Allianz Arena"},
            {"Santiago Bernabeu","Anfield","Old Trafford"},
            {"Wembley Stadium","Allianz Arena","San Siro"},
            {"Old Trafford","Lusail Stadium","Stamford Bridge"},
            {"Santiago Bernabeu","Parc des Princes","Allianz Arena"},
            {"Stamford Bridge","Etihad Stadium","Signal Iduna Park"},
            {"Parc des Princes","San Siro","Emirates Stadium"}
    };

    int[] answers = {0,1,1,0,0,0,0};

    public QuizFragment() {}

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        teamNameText = view.findViewById(R.id.teamNameText);
        question = view.findViewById(R.id.question);
        scoreText = view.findViewById(R.id.scoreText);
        stadiumImage = view.findViewById(R.id.stadiumImage);

        radioGroup = view.findViewById(R.id.radioGroup);

        option1 = view.findViewById(R.id.option1);
        option2 = view.findViewById(R.id.option2);
        option3 = view.findViewById(R.id.option3);

        btnSubmit = view.findViewById(R.id.btnSubmit);
        btnNext = view.findViewById(R.id.btnNext);
        btnBack = view.findViewById(R.id.btnBack);

        loadQuestion();

        btnSubmit.setOnClickListener(v -> checkAnswer());

        btnNext.setOnClickListener(v -> {
            questionIndex++;

            if(questionIndex < images.length){
                loadQuestion();
            }else{
                Toast.makeText(getActivity(),
                        "Quiz Finished! Score: "+score+" / "+images.length,
                        Toast.LENGTH_LONG).show();
            }
        });

        btnBack.setOnClickListener(v -> {
            getParentFragmentManager().popBackStack();
        });

        return view;
    }

    private void loadQuestion(){

        radioGroup.clearCheck();

        teamNameText.setText(teams[questionIndex]);
        stadiumImage.setImageResource(images[questionIndex]);

        option1.setText(options[questionIndex][0]);
        option2.setText(options[questionIndex][1]);
        option3.setText(options[questionIndex][2]);

        scoreText.setText("Score: "+score);
    }

    private void checkAnswer(){

        int selectedId = radioGroup.getCheckedRadioButtonId();

        if(selectedId == -1){
            Toast.makeText(getActivity(),"Choose an answer",Toast.LENGTH_SHORT).show();
            return;
        }

        int answerIndex = 0;

        if(selectedId == option1.getId()) answerIndex = 0;
        if(selectedId == option2.getId()) answerIndex = 1;
        if(selectedId == option3.getId()) answerIndex = 2;

        if(answerIndex == answers[questionIndex]){
            score++;
            Toast.makeText(getActivity(),"Correct ✅",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getActivity(),"Wrong ❌",Toast.LENGTH_SHORT).show();
        }

        scoreText.setText("Score: "+score);
    }
}