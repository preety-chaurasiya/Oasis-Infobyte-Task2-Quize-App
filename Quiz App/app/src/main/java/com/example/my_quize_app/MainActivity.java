package com.example.my_quize_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private TextView questionTextView;
    private RadioGroup choicesRadioGroup;
    private Button submitButton;

    private String[] questions = {
            "Which planet is known as the Red Planet?",
            "What is the largest mammal in the world?",
            "Which gas do plants use for photosynthesis?",
            "which is the largest country in the world"
    };

    private String[] correctAnswers = {
            "Mars",
            "Blue Whale",
            "Carbon Dioxide",
            "Russia"
    };

    private int currentQuestionIndex = 0; // Index to track the current question

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = findViewById(R.id.questionTextView);
        choicesRadioGroup = findViewById(R.id.choicesRadioGroup);
        submitButton = findViewById(R.id.submitButton);

        // Set the initial question
        setQuestionAndChoices(currentQuestionIndex);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = choicesRadioGroup.getCheckedRadioButtonId();
                if (selectedId == -1) {
                    Toast.makeText(MainActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                } else {
                    RadioButton selectedRadioButton = findViewById(selectedId);
                    String userAnswer = selectedRadioButton.getText().toString();
                    checkAnswer(userAnswer);

                    // Move to the next question
                    currentQuestionIndex++;
                    if (currentQuestionIndex < questions.length) {
                        setQuestionAndChoices(currentQuestionIndex);
                    } else {
                        Toast.makeText(MainActivity.this, "Quiz completed!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void setQuestionAndChoices(int index) {
        questionTextView.setText(questions[index]);
        RadioButton choice1 = findViewById(R.id.choice1RadioButton);
        RadioButton choice2 = findViewById(R.id.choice2RadioButton);
        RadioButton choice3 = findViewById(R.id.choice3RadioButton);
        RadioButton choice4 = findViewById(R.id.choice4RadioButton);

        choice1.setText("Mars"); // Update with your choices
        choice2.setText("Blue Whale");
        choice3.setText("Carbon Dioxide");
        choice4.setText("Russia");

        choicesRadioGroup.clearCheck();
    }

    private void checkAnswer(String userAnswer) {
        if (userAnswer.equals(correctAnswers[currentQuestionIndex])) {
            Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Incorrect. The correct answer is " + correctAnswers[currentQuestionIndex], Toast.LENGTH_SHORT).show();
        }
    }
}