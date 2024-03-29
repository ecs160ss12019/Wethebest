package com.wethebest.spaceinvaders;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/*
Story gives the background story of the game
Started when Story button is pressed in Intro
 */

public class Story extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        final TextView PlayerName = findViewById(R.id.PlayerName);
        final EditText EnterName = findViewById(R.id.EnterName);
        final Button Nextbtn = findViewById(R.id.Next_btn);
        final TextView title = findViewById(R.id.textView2);
        final ImageView MainImage = findViewById(R.id.imageView3);
        final ImageView Seagullpoop = findViewById(R.id.imageView4);
        final ImageView usermessage = findViewById(R.id.imageView6);

        Nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainImage.setVisibility(ImageView.INVISIBLE);
                title.setVisibility(ImageView.INVISIBLE);
                Nextbtn.setVisibility(ImageView.INVISIBLE);
                EnterName.setVisibility(ImageView.VISIBLE);
                PlayerName.setVisibility(ImageView.VISIBLE);
                Seagullpoop.setVisibility(ImageView.VISIBLE);
                usermessage.setVisibility(ImageView.VISIBLE);

            }
        });

        //goes to next activity once name is entered
        EnterName.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:

                            Intent intent=new Intent(Story.this,SpaceInvaders.class);
                            startActivityForResult(intent,0);
                            overridePendingTransition( R.anim.trans_left_in, R.anim.trans_left_out );

                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });

    }

    //main menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    //items in the menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if(item.getItemId() == R.id.action_startover) {
            startActivity(new Intent(Story.this, SpaceInvaders.class));

        }
        if(item.getItemId() == R.id.action_newgame) {
            startActivity(new Intent(Story.this, Intro.class));
        }

        return super.onOptionsItemSelected(item);
    }

    //back button forces to go to intro activity and stops app from crashing
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {

            Intent intent=new Intent(Story.this,Intro.class);
            startActivityForResult(intent,0);
            overridePendingTransition( R.anim.trans_right_in, R.anim.trans_right_out );

        }
        return super.onKeyDown(keyCode, event);
    }

    private MediaPlayer mMediaPlayer;
    @Override
    protected void onResume() {
        super.onResume();
        mMediaPlayer = MediaPlayer.create(this,R.raw.example);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();
    }
    @Override
    protected void onPause() {
        mMediaPlayer.stop(); mMediaPlayer.release();
        super.onPause();
    }

}
