package edu.uta.mavs.jones.evan.rolltide;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends Activity {

    MediaPlayer fightSong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fightSong = MediaPlayer.create(MainActivity.this, R.raw.yeaalabama);
        fightSong.start();
        blinkText();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onPause(){
        super.onPause();
        fightSong.release();
        finish();
    }
    private void blinkText(){
        final Handler handler = new Handler();
            new Thread(new Runnable(){
                @Override
                public void run(){
                    int timeToBlink = 500;  //blink time in ms
                    try{
                        Thread.sleep(timeToBlink);
                    }catch(Exception e){

                    }
                    handler.post(new Runnable(){
                        @Override
                        public void run(){
                            TextView txt = (TextView) findViewById(R.id.RegText);
                            if(txt.getVisibility() == View.VISIBLE){
                                txt.setVisibility(View.INVISIBLE);
                            }else{
                                txt.setVisibility(View.VISIBLE);
                            }
                            blinkText();
                        }
                    });
                }}).start();
    }
}
