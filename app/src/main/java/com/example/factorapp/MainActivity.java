package com.example.factorapp;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ArrayList<Integer> factors = new ArrayList<>();
    ArrayList<Integer> nonFactors = new ArrayList<>();
    ArrayList<Integer> newList = new ArrayList<>();
    int finalValue;
    ArrayList<Integer> myAnswerList = new ArrayList<>();
    int rightAnswer;
    int optionOne;
    int optionTwo;
    int optionThree;
    int i;
    int scoreValue;
    CountDownTimer yourCountDownTimer;
    TextView textViewOne;
    TextView textViewTwo;
    TextView textViewThree;
    ConstraintLayout thisLayout;
    Handler handler;
    TextView countTime;
    Vibrator vibrator;
    TextView score;
    EditText e1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewOne = findViewById(R.id.textview_one);
        textViewTwo = findViewById(R.id.textView_2);
        textViewThree = findViewById(R.id.textView_3);
        final Button buttonView = findViewById(R.id.testbutton);
        final Button reset = findViewById(R.id.reset_button);
        score = findViewById(R.id.score_view);
        thisLayout = (ConstraintLayout) findViewById(R.id.layout_id);
        final TextView scoreLabel=findViewById(R.id.score_label);
        handler = new Handler();
        countTime=findViewById(R.id.timer_view);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        e1 = (EditText) findViewById(R.id.editText);
        TextView s1= findViewById(R.id.scorelabel);

        scoreLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("key", 0);
                SharedPreferences.Editor sedt = sp.edit();
                sedt.putInt("key",scoreValue);
                sedt.apply();
                scoreLabel.setText("saved");
            }
        });

        SharedPreferences sp = getSharedPreferences("key", 0);
        scoreValue = sp.getInt("key",scoreValue);
        score.setText(String.valueOf(scoreValue));

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewOne.setText("0");
                textViewTwo.setText("0");
                textViewThree.setText("0");
                e1.setText(null);
                factors.clear();
                nonFactors.clear();
                newList.clear();
                myAnswerList.clear();
                optionOne = 0;
                optionTwo = 0;
                optionThree = 0;
                thisLayout.setBackgroundColor(Color.WHITE);
                scoreValue=0;
                score.setText(String.valueOf(scoreValue));
            }
        });


        textViewTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finalValue % myAnswerList.get(1) == 0) {
                    scoreValue = scoreValue + 1;
                    Toast.makeText(getApplicationContext(), "Your answer is correct, current score is " + scoreValue, Toast.LENGTH_SHORT).show();
                    score.setText(String.valueOf(scoreValue));
                    thisLayout.setBackgroundColor(Color.GREEN);
                    yourCountDownTimer.cancel();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            reset();
                        }
                    }, 3000);

                } else {
                    Toast.makeText(getApplicationContext(), "Your answer is wrong , the right answer is " + rightAnswer, Toast.LENGTH_SHORT).show();
                    score.setText(String.valueOf(scoreValue));
                    thisLayout.setBackgroundColor(Color.RED);
                    if (Build.VERSION.SDK_INT >= 26) {
                        assert vibrator != null;
                        vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        assert vibrator != null;
                        vibrator.vibrate(500);
                    }
                    yourCountDownTimer.cancel();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            reset();
                        }
                    }, 3000);
                }
            }
        });

        textViewThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finalValue % myAnswerList.get(2) == 0) {
                    scoreValue = scoreValue + 1;
                    Toast.makeText(getApplicationContext(), "Your answer is correct, current score is "+ scoreValue, Toast.LENGTH_SHORT).show();
                    score.setText(String.valueOf(scoreValue));
                    thisLayout.setBackgroundColor(Color.GREEN);
                    yourCountDownTimer.cancel();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            reset();
                        }
                    }, 3000);

                } else {
                    Toast.makeText(getApplicationContext(), "Your answer is wrong , the right answer is " + rightAnswer, Toast.LENGTH_SHORT).show();
                    score.setText(String.valueOf(scoreValue));
                    thisLayout.setBackgroundColor(Color.RED);
                    if (Build.VERSION.SDK_INT >= 26) {
                        assert vibrator != null;
                        vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        assert vibrator != null;
                        vibrator.vibrate(500);
                    }
                    yourCountDownTimer.cancel();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            reset();
                        }
                    }, 3000);
                }
            }
        });

        textViewOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finalValue % myAnswerList.get(0) == 0) {
                    scoreValue = scoreValue + 1;
                    Toast.makeText(getApplicationContext(), "Your answer is correct, current score is " + scoreValue, Toast.LENGTH_SHORT).show();

                    score.setText(String.valueOf(scoreValue));
                    thisLayout.setBackgroundColor(Color.GREEN);
                    yourCountDownTimer.cancel();

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            reset();
                        }
                    }, 3000);
                } else {
                    Toast.makeText(getApplicationContext(), "Your answer is wrong , the right answer is " + rightAnswer, Toast.LENGTH_SHORT).show();
                    score.setText(String.valueOf(scoreValue));
                    thisLayout.setBackgroundColor(Color.RED);
                    if (Build.VERSION.SDK_INT >= 26) {
                        assert vibrator != null;
                        vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        assert vibrator != null;
                        vibrator.vibrate(500);
                    }
                    yourCountDownTimer.cancel();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            reset();
                        }
                    }, 3000);
                }
            }
        });


    }

    public void generateRandom() {
        for (i = 1; i <= finalValue; i++) {
            if (finalValue % i == 0) {
                factors.add(i);
            } else {
                nonFactors.add(i);
            }
        }
        Random random = new Random();
        optionOne = factors.get(random.nextInt(factors.size()));
        nonFactors.add(100);
        nonFactors.add(200);
        Collections.shuffle(nonFactors);
        for (int j = 0; j < 2; j++) {
            newList.add(nonFactors.get(j));
        }
        optionTwo = newList.get(0);
        optionThree = newList.get(1);
        myAnswerList.add(optionOne);
        myAnswerList.add(optionTwo);
        myAnswerList.add(optionThree);
        Collections.shuffle(myAnswerList);
        textViewOne.setText(String.valueOf(myAnswerList.get(0)));
        textViewTwo.setText(String.valueOf(myAnswerList.get(1)));
        textViewThree.setText(String.valueOf(myAnswerList.get(2)));
    }

    public void reset()
    {
        textViewOne.setText("0");
        textViewTwo.setText("0");
        textViewThree.setText("0");
        e1.setText(null);
        factors.clear();
        nonFactors.clear();
        newList.clear();
        myAnswerList.clear();
        optionOne = 0;
        optionTwo = 0;
        optionThree = 0;
        thisLayout.setBackgroundColor(Color.WHITE);
        countTime.setText("00:00:00");

    }

    public void enterText(View view) {
        Editable nameEditable = e1.getText();
        String number = nameEditable.toString();
        if (number.equals(""))
        { Toast.makeText(getApplicationContext(), "You have entered null value", Toast.LENGTH_SHORT).show();}
        else {
            finalValue = Integer.parseInt(number);
            generateRandom();
            yourCountDownTimer = new CountDownTimer(10000,1000) {
                public void onTick(long millisUntilFinished) {

                    NumberFormat f = new DecimalFormat("00");
                    long hour = (millisUntilFinished / 3600000) % 24;
                    long min = (millisUntilFinished / 60000) % 60;
                    long sec = (millisUntilFinished / 1000) % 60;

                    countTime.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
                }

                public void onFinish() {
                    countTime.setText("00:00:00");
                    ConstraintLayout thisLayout = (ConstraintLayout) findViewById(R.id.layout_id);
                    textViewOne.setText("0");
                    textViewTwo.setText("0");
                    textViewThree.setText("0");
                    EditText e1 = (EditText) findViewById(R.id.editText);
                    e1.setText(null);
                    factors.clear();
                    nonFactors.clear();
                    newList.clear();
                    myAnswerList.clear();
                    optionOne = 0;
                    optionTwo = 0;
                    optionThree = 0;
                    thisLayout.setBackgroundColor(Color.WHITE);
                    Toast.makeText(getApplicationContext(), "Timer has ended,current score is  " + scoreValue, Toast.LENGTH_SHORT).show();


                }
            }.start();
            for (int k = 0; k < 3; k++) {
                if (finalValue % myAnswerList.get(k) == 0) {
                    rightAnswer = myAnswerList.get(k);
                }
            }
        }
    }


}







