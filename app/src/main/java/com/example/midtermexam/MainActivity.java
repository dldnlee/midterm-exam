package com.example.midtermexam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.midtermexam.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private int count = 0, win_count = 0;
    private float percentage;
    Random rand = new Random();
    int rock = 0, scissor = 1, paper = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        newGame();

        binding.rockButton.setOnClickListener(v -> {
            playGame(this.rock);
            binding.userMove.setImageResource(R.drawable.rock);
        });
        binding.scissorButton.setOnClickListener(v -> {
            playGame(this.scissor);
            binding.userMove.setImageResource(R.drawable.scissors);
        });
        binding.paperButton.setOnClickListener(v -> {
            playGame(this.paper);
            binding.userMove.setImageResource(R.drawable.paper);
        });

        binding.playAgain.setOnClickListener(v -> newGame());

    }

    /*새로운 게임 시작*/
    private void newGame() {
        binding.imageRock.setVisibility(View.VISIBLE);
        binding.imageScissor.setVisibility(View.VISIBLE);
        binding.imagePaper.setVisibility(View.VISIBLE);
        binding.playAgain.setVisibility(View.GONE);
        binding.userMove.setVisibility(View.INVISIBLE);
        binding.aiMove.setVisibility(View.INVISIBLE);
        binding.userText.setVisibility(View.INVISIBLE);
        binding.aiText.setVisibility(View.INVISIBLE);

        binding.startText.setText("게임을 시작합니다");
        binding.rpsText.setText("가위 바위 보!");
    }

    /*게임 본격 시작*/
    private void gameStart() {
        binding.imageRock.setVisibility(View.INVISIBLE);
        binding.imageScissor.setVisibility(View.INVISIBLE);
        binding.imagePaper.setVisibility(View.INVISIBLE);
        binding.playAgain.setVisibility(View.VISIBLE);
        binding.playAgain.setEnabled(true);
        binding.userMove.setVisibility(View.VISIBLE);
        binding.aiText.setVisibility(View.VISIBLE);
        binding.userText.setVisibility(View.VISIBLE);
        binding.aiMove.setVisibility(View.VISIBLE);

        this.percentage = (this.win_count*100)/(this.count);

        binding.startText.setText(String.format("%d번중 %d번 이겼습니다. 승률은 %.2f%%입니다 ", this.count, this.win_count, this.percentage));
    }

    /*게임 우승여부 함수*/
    private void playGame(int pick) {
        int ai = rand.nextInt(3);
        /* rock = 0, scissor = 1, paper = 2 */

        if(ai == 0) {
            binding.aiMove.setImageResource(R.drawable.rock);
        } else if(ai == 1) {
            binding.aiMove.setImageResource(R.drawable.scissors);
        } else if(ai == 2) {
            binding.aiMove.setImageResource(R.drawable.paper);
        }

        if((pick == 0 && ai == 0) || (pick == 1 && ai == 1) || (pick == 2 && ai == 2)) {
            draw();
        } else if((pick == 0 && ai == 1) || (pick == 1 && ai == 2) || (pick == 2 && ai == 0)) {
            win();
        } else if((pick == 0 && ai == 2) || (pick == 1 && ai == 0) || (pick == 2 && ai == 1)) {
            lose(); }


        gameStart();
    }

    private void draw() {
        binding.startText.setVisibility(View.INVISIBLE);
        binding.rpsText.setText("다시 한번 가위바위보!");
    }

    private void win() {
        binding.startText.setVisibility(View.VISIBLE);
        this.count += 1;
        this.win_count += 1;
        binding.rpsText.setText("당신이 이겼습니다!!");
    }

    private void lose() {
        binding.startText.setVisibility(View.VISIBLE);
        this.count += 1;
        binding.rpsText.setText("당신이 졌어요ㅠ");
    }

}

