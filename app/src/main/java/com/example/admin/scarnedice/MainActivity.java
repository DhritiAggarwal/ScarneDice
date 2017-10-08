package com.example.admin.scarnedice;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


  TextView  scoreText;

    Button roll;
    Button hold;
    Button reset;
    ImageView i;
    boolean chance;
    boolean won;
    int yourscore,cpuscore,turnscore,dice_no;
    Random rn= new Random();
    int images[]={R.drawable.dice1,R.drawable.dice2,R.drawable.dice3,R.drawable.dice4,R.drawable.dice5,R.drawable.dice6};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       i=(ImageView)findViewById(R.id.dice_image);
        scoreText=(TextView)findViewById(R.id.scoreText);
        reset=(Button)findViewById(R.id.reset);
        hold=(Button)findViewById(R.id.hold);
        roll=(Button)findViewById(R.id.roll);
        yourscore=cpuscore=turnscore=0;
        dice_no=1;
        chance=true;
won=false;
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this, R.anim.animation);
        i.startAnimation(hyperspaceJumpAnimation);
    }
    public void roll(View view){
        dice_no=rn.nextInt(6)+1;
       if(dice_no==1){
           turnscore=0;
           hold(null);
       }
       else{

           turnscore+=dice_no;
           if(chance) {
               if (turnscore + yourscore > 50) {
                   yourscore += turnscore;
                   won = true;

                   Toast t = Toast.makeText(getApplicationContext(), "YOU WON!", Toast.LENGTH_SHORT);
                   t.show();
               }
           }
           else{

           if (turnscore + cpuscore > 50) {
                  cpuscore+=turnscore;
               won=true;
                   Toast t = Toast.makeText(getApplicationContext(), "COMPUTER WON!", Toast.LENGTH_SHORT);
                   t.show();
               }
           }
           updateUi();}
       }


    public void hold(View view){
        if(chance){
        yourscore+=turnscore;
        chance=false;
        turnscore=0;
            updateUi();
        computerTurn();
        }
        else{
            cpuscore+=turnscore;
            turnscore=0;
            chance=true;
            updateUi();

        }



    }
    public void reset(View view){
        yourscore=cpuscore=turnscore =0;
        chance=true;
        won=false;
        updateUi();
    }
    public void computerTurn(){
        if(!chance)
        {
            if(turnscore<20){
                roll(null);
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        computerTurn();
                    }
                },3000);
            }
            else
            {
                hold(null);

            }
        }
    }
    public void updateUi(){
        scoreText.setText("YOUR SCORE:"+yourscore+" \n COMPUTER'S SCORE:"+cpuscore+ "\n TURN SCORE:"+turnscore);
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this, R.anim.animation);
        i.startAnimation(hyperspaceJumpAnimation);
        i.setImageResource(images[dice_no-1]);
if(won){
    roll.setEnabled(false);
    hold.setEnabled(false);
}
else
{
    roll.setEnabled(true);
    hold.setEnabled(true);
}




    }


}
