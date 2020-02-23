package fr.kyllian.tp2android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static int rand_1;
    static int rand_2;

    static List<String> listOperation = Arrays.asList("+","x");
    static int signeRandom = (int)(Math.random() * (1 - 0 + 1) + 0);
    static String signe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int nb_attemps = GameActivity.attemps;

        TextView te = findViewById(R.id.score);
        te.setText("Nombre de tentatives : " + nb_attemps + " essai(s)");

        final Button btn = findViewById(R.id.button_start);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                rand_1 = (int)(Math.random() * (9 - 1 + 1) + 1);
                rand_2 = (int)(Math.random() * (9 - 1 + 1) + 1);
                signe = listOperation.get(signeRandom);

                GameActivity.attemps = 0;

                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }

}
