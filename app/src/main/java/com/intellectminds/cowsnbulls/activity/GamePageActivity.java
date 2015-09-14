package com.intellectminds.cowsnbulls.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.intellectminds.cowsnbulls.R;
import com.intellectminds.cowsnbulls.logic.GameLogic;

public class GamePageActivity extends Activity {

    private static boolean createRow = false;
    String msg = "Android : ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(msg, "The onCreate() event");

        setContentView(R.layout.game_page_activity);

        final EditText wordText = (EditText) findViewById(R.id.word_text);
        wordText.setPaintFlags(wordText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        InputFilter[] filterArray = new InputFilter[2];
        filterArray[0] = new InputFilter.AllCaps();
        filterArray[1] = new InputFilter.LengthFilter(10);
        wordText.setGravity(Gravity.CENTER);
        wordText.setFilters(filterArray);

        final TextView word = (TextView) findViewById(R.id.guess_word);
        final TextView cows = (TextView) findViewById(R.id.cows);
        final TextView bulls = (TextView) findViewById(R.id.bulls);
        TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        final TableLayout table = (TableLayout) findViewById(R.id.result_layout);
        //table.setLayoutParams(layoutParams);

        final GameLogic logic = new GameLogic();
        Button button = (Button) findViewById(R.id.match_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logic.matchWord(wordText.getText().toString());

                if(!createRow) {
                    word.setText(wordText.getText().toString());
                    cows.setText(String.valueOf(logic.getCows()));
                    bulls.setText(String.valueOf(logic.getBulls()));
                    createRow = true;
                }
                else {
                    TextView guessWord = new TextView(getApplicationContext());
                    TextView cowsCount = new TextView(getApplicationContext());
                    TextView bullsCount = new TextView(getApplicationContext());
                    guessWord.setText(wordText.getText().toString());
                    cowsCount.setText(String.valueOf(logic.getCows()));
                    bullsCount.setText(String.valueOf(logic.getBulls()));

                    TableRow row = new TableRow(getApplicationContext());
                    row.addView(guessWord);
                    row.addView(cowsCount);
                    row.addView(bullsCount);

                    table.addView(row);
                }

                wordText.setText("");

            }
        });

        ImageButton pauseButton = (ImageButton)findViewById(R.id.pause);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GamePageActivity.this,PauseWindow.class));
                



            }
        });

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(msg, "The onPause() event");




    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(msg, "The onStart() event");
    }

    /** Called when the activity has become visible. */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(msg, "The onResume() event");
    }

    /** Called when the activity is no longer visible. */
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(msg, "The onStop() event");
    }

    /** Called just before the activity is destroyed. */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(msg, "The onDestroy() event");
    }


    public void showGamePageActivity(View view) {
    }
}
