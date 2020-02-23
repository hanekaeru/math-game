package fr.kyllian.tp2android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    private int result;

    private int number1;
    private int number2;
    private String signe;
    private Vibrator vibration;

    public static int attemps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        GameActivity.this.vibration = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        this.number1 = MainActivity.rand_1;
        this.number2 = MainActivity.rand_2;
        this.signe = MainActivity.signe;

        String recup = this.number1 + " " + this.signe + " " + this.number2;

        final TextView te = findViewById(R.id.operation);
        te.setText(recup);

        String[] operation = recup.split(" ");
        String signe = operation[1];

        switch (signe) {
            case "x":
                this.result = number1 * number2;
                break;
            case "+":
                this.result = number1 + number2;
                break;
            case "-":
                this.result = number1 - number2;
                break;
            case "/":
                this.result = number1 / number2;
                break;
        }

        final Button btn = findViewById(R.id.button_submit);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText input = findViewById(R.id.input_text);
                String value = input.getText().toString();
                String text = input.getText().toString().trim();

                if (TextUtils.isEmpty(text)){
                    input.setError("Veuillez entrer un nombre !");
                }else {
                    GameActivity.this.attemps += 1;

                    if (Integer.parseInt(value) == GameActivity.this.result) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            GameActivity.this.vibration.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));

                        } else {
                            //deprecated in API 26
                            GameActivity.this.vibration.vibrate(100);
                        }
                        Intent intent = new Intent(GameActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            GameActivity.this.vibration.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                        } else {
                            //deprecated in API 26
                            GameActivity.this.vibration.vibrate(500);
                        }
                        Intent intent = new Intent(GameActivity.this, LoseActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }

            }
        });
    }
}
