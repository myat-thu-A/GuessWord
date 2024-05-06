package it.myatthu.guessword;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView tvScore, tvGuessWord, tvMatchPlayed;
    private EditText etYourGuess;
    private Button btGuess;
    private int score, matchPlayed, played;
    private String guessWord, guess;
    private final String[] words = {"apple", "banana", "game", "computer",
            "orange", "flower", "cat", "ball", "bell", "iron"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
        initListeners();
    }

    private void initListeners() {
        btGuess.setOnClickListener(v -> {


            if(played == 5){
                return;
            }
            played++;
            String myGuess = etYourGuess.getText().toString();
            if (guessWord.equals(myGuess)) {
                // CORRECT
                score++;
                tvScore.setText(score + "/5");
            }
            matchPlayed++;
            tvMatchPlayed.setText("Match Played : " + matchPlayed);
            nextRound();

            if(matchPlayed == 5) {

                if (score > 2) {
                    tvGuessWord.setText("WINNER");
                } else {
                    tvGuessWord.setText("TRY AGAIN!");
                }
                etYourGuess.setText("");
                return;
            }
        });
    }

    private void nextRound() {
        String guessWord = getRandomWordFromWords();
        String word = guessWord;
        tvGuessWord.setText(wordShuffle(word));
        etYourGuess.setText("");
    }

    private String wordShuffle(String str) {
        List<Character> chars = new ArrayList<>();
        for(char c : str.toCharArray()) {
            chars.add(c);
        }

        Collections.shuffle(chars);

        StringBuilder shuffle = new StringBuilder();
        for(char c : chars) {
            shuffle.append(c);
        }
        return shuffle.toString();
    }

    private void initUi() {
        tvScore = findViewById(R.id.tvScore);
        tvGuessWord = findViewById(R.id.tvGuessWord);
        tvMatchPlayed = findViewById(R.id.tvMatchPlayed);
        etYourGuess = findViewById(R.id.etYourGuess);
        btGuess = findViewById(R.id.btGuess);
        guess = getRandomWordFromWords();
        tvGuessWord.setText(wordShuffle(guess));

    }

    private String getRandomWordFromWords() {
        int rd = new Random().nextInt(words.length);
        guessWord = words[rd];
        return guessWord;
    }

}