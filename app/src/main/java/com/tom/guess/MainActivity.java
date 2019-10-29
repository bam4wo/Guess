package com.tom.guess;

import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //int secret = new Random().nextInt(10)+1;
    //int c = 5;
    int secret;
    private EditText number;
    String TAG = MainActivity.class.getSimpleName();
    private TextView hint;
    private ImageView result;
    private TextView time;
    int counter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        number = findViewById(R.id.num);
        hint = findViewById(R.id.hint);
        result = findViewById(R.id.result);
        time = findViewById(R.id.time);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               reset();
            }
        });
        reset();
        Log.d(TAG,"SECRET:"+secret);
    }

    public void reset(){
        secret = new Random().nextInt(10)+1;
        counter = 0;
        time.setText(counter+"");
    }

    public void guess(View view){
        //c = c-1;
        int guessnum = Integer.parseInt(number.getText().toString());
        result.setVisibility(View.VISIBLE);
        counter++;
        time.setText(counter+"");
        DialogInterface.OnCancelListener listener = new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                reset();
            }
        };
        String message = "good";
        if (guessnum > secret){
            hint.setText("smaller");
            result.setImageResource(R.drawable.down);
            message = "smaller";
            //listener = null;
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Hint")
                    .setMessage("smaller" )
                    .setPositiveButton("ok",null)
                    .show();
        }else if (guessnum < secret){
            hint.setText("bigger");
            result.setImageResource(R.drawable.up);
            message = "bigger";
            //listener = null;
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Hint")
                    .setMessage("smaller" )
                    .setPositiveButton("ok",null)
                    .show();
        }else {
            hint.setText("bingo, the secret number is:" + secret);
            result.setImageResource(R.drawable.flower);
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Hint")
                    .setMessage("bingo" )
                    .setPositiveButton("ok",null)
                    .show();
        }


        /*
        if (c > 0 && c < 5){
            time.setText("remaining times:" + c);
        }else if (c < 0) {
            time.setText("game over");
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
