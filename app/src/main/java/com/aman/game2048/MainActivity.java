package com.aman.game2048;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int[] rainbowColor = {
            R.drawable.ic_adjust,
            R.drawable.ic_beach,
            R.drawable.ic_adjust,
            R.drawable.ic_beach,
            R.drawable.ic_adjust,
            R.drawable.ic_beach
    };
    ArrayList<TextView> square = new ArrayList<>();
    int widthOfBlock, noOfBlock = 4;
    int widthOfScreen;
    LinearLayout linLay;
    TextView score;
    int scoreValue = 0;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linLay = findViewById(R.id.linLay);
        score = findViewById(R.id.score);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        widthOfScreen = displayMetrics.widthPixels;
        int heightOfScreen = displayMetrics.heightPixels;
        widthOfBlock = widthOfScreen / noOfBlock;
        linLay.getLayoutParams().height = (heightOfScreen - widthOfScreen) / 2;
        createBoard();
        for (final TextView textView : square) {
            textView.setOnTouchListener(new OnSwipeTouchListener(this) {
                public void onSwipeTop() {
                    Log.e("Top", "True");
                    for (int i = 12; i < 16; i++) {
                        for (int j = i; j > i - 9; j = j - noOfBlock) {
                            if (!(j < i - 9)) {
                                if (square.get(j).getText().toString().equals(square.get(j - noOfBlock).getText().toString())
                                        || square.get(j - noOfBlock).getText().toString().equals("1")) {
                                    int result;
                                    if (square.get(j - noOfBlock).getText().toString().equals("1")) {
                                        result = Integer.parseInt(square.get(j).getText().toString())
                                                * Integer.parseInt(square.get(j - noOfBlock).getText().toString());
                                    } else {
                                        result = Integer.parseInt(square.get(j).getText().toString())
                                                + Integer.parseInt(square.get(j - noOfBlock).getText().toString());
                                        scoreValue += result;
                                        score.setText(String.valueOf(scoreValue));
                                    }
                                    square.get(j - noOfBlock).setText(String.valueOf(result));
                                    square.get(j).setText("1");
                                }
                            }
                        }
                    }
                    mustNeededCode();
                }

                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                public void onSwipeRight() {
                    Log.d("Right", "True");
                    for (int i = 0; i < 13; i = i + noOfBlock) {
                        for (int j = i; j < i + 3; j++) {
                            if (!(j > i + 3)) {
                                if (square.get(j).getText().toString().equals(square.get(j + 1).getText().toString())
                                        || square.get(j + 1).getText().toString().equals("1")) {
                                    int result = 0;
                                    if (square.get(j + 1).getText().toString().equals("1")) {
                                        result = Integer.parseInt(square.get(j).getText().toString())
                                                * Integer.parseInt(square.get(j + 1).getText().toString());
                                    } else {
                                        result = Integer.parseInt(square.get(j).getText().toString())
                                                + Integer.parseInt(square.get(j + 1).getText().toString());
                                        scoreValue += result;
                                        score.setText(String.valueOf(scoreValue));
                                    }
                                    square.get(j + 1).setText(String.valueOf(result));
                                    square.get(j).setText("1");
                                }
                            }
                        }
                    }
                    mustNeededCode();
                }

                public void onSwipeLeft() {
                    Log.d("Left", "True");
                    for (int i = 3; i < 16; i = i + noOfBlock) {
                        for (int j = i; j > i - 3; j--) {
                            if (!(j < i - 3)) {
                                if (square.get(j).getText().toString().equals(square.get(j - 1).getText().toString())
                                        || square.get(j - 1).getText().toString().equals("1")) {
                                    int result;
                                    if (square.get(j - 1).getText().toString().equals("1")) {
                                        result = Integer.parseInt(square.get(j).getText().toString())
                                                * Integer.parseInt(square.get(j - 1).getText().toString());
                                    } else {
                                        result = Integer.parseInt(square.get(j).getText().toString())
                                                + Integer.parseInt(square.get(j - 1).getText().toString());
                                        scoreValue += result;
                                        score.setText(String.valueOf(scoreValue));
                                    }
                                    square.get(j - 1).setText(String.valueOf(result));
                                    square.get(j).setText("1");
                                }
                            }
                        }
                    }
                    mustNeededCode();
                }

                public void onSwipeBottom() {
                    Log.d("Bottom", "True");
                    for (int i = 0; i < 4; i++) {
                        for (int j = i; j < i + 9; j = j + noOfBlock) {
                            if (!(j > i + 9)) {
                                if (square.get(j).getText().toString().equals(square.get(j + noOfBlock).getText().toString())
                                        || square.get(j + noOfBlock).getText().toString().equals("1")) {
                                    int result;
                                    if (square.get(j + noOfBlock).getText().toString().equals("1")) {
                                        result = Integer.parseInt(square.get(j).getText().toString())
                                                * Integer.parseInt(square.get(j + noOfBlock).getText().toString());
                                    } else {
                                        result = Integer.parseInt(square.get(j).getText().toString())
                                                + Integer.parseInt(square.get(j + noOfBlock).getText().toString());
                                        scoreValue += result;
                                        score.setText(String.valueOf(scoreValue));
                                    }
                                    square.get(j + noOfBlock).setText(String.valueOf(result));
                                    square.get(j).setText("1");
                                }
                            }
                        }
                    }
                    mustNeededCode();
                }
            });
        }
    }

    void mustNeededCode()
    {
        int randomPostion = 0;
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            if (square.get(i).getText().toString().equals("1"))
                integers.add(i);
            randomPostion = (int) Math.floor(Math.random() * integers.size());
            Log.e("Integer ", integers.size() + "");
        }
        if (integers.size() != 0) {
            square.get(integers.get(randomPostion)).setText("2");
        } else {
            CustomDialogClass cdd = new CustomDialogClass(this);
            cdd.show();
        }
        for (int i = 0 ; i < 16 ; i ++)
        {
            if (!square.get(i).getText().toString().equals("1"))
            {
                square.get(i).setTextColor(Color.BLACK);
            }
            else
            {
                square.get(i).setTextColor(Color.TRANSPARENT);
            }
        }
    }

    private void createBoard() {
        GridLayout layout = findViewById(R.id.square);
        layout.setRowCount(noOfBlock);
        layout.setColumnCount(noOfBlock);
        layout.getLayoutParams().height = widthOfScreen;
        layout.getLayoutParams().width = widthOfScreen;
        boolean randomAdded = true;
        for (int i = 0; i < noOfBlock * noOfBlock; i++) {
            TextView textView = new TextView(this);
            textView.setLayoutParams(new android.view.ViewGroup.LayoutParams(widthOfBlock, widthOfBlock));
            textView.setMaxHeight(widthOfBlock);
            textView.setMaxWidth(widthOfBlock);
            textView.setTextSize(27);
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.BLACK);
            textView.setBackground(getResources().getDrawable(R.drawable.background_tv));
            //  int randomColor = (int) Math.floor(Math.random() * rainbowColor.length);
            if (!randomAdded) {
                textView.setText("1");
            } else {
                textView.setText("2");
                randomAdded = false;
            }
            if (textView.getText().toString().equals("1"))
                textView.setTextColor(Color.TRANSPARENT);
            square.add(textView);
            layout.addView(textView);
        }
    }

}