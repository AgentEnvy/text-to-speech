package com.example.dennisgrajoii.handwriting;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.ibm.watson.developer_cloud.android.library.audio.StreamPlayer;
import com.ibm.watson.developer_cloud.http.ServiceCall;
import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;

import java.io.InputStream;
import java.util.List;




// <string name="text_speech_username">ff604eca-069b-41f7-a0a0-83b2b1eff974</string>
// <string name="text_speech_password">3G8pSFZWCOqx</string>
// <string name="text_speech_url">https://stream.watsonplatform.net/text-to-speech/api</string>

public class MainActivity extends AppCompatActivity {

    private TextToSpeech initTextToSpeechService(){
        TextToSpeech service = new TextToSpeech();
        String username = "ff604eca-069b-41f7-a0a0-83b2b1eff974";
        String password = "3G8pSFZWCOqx";
        service.setUsernameAndPassword(username, password);
        return service;
    }

    public void speakText() {
        TextToSpeech service = this.initTextToSpeechService();
        Voice voice = Voice.EN_MICHAEL;
        InputStream stream = service.synthesize("It is Wednesday, my dudes", voice).execute();

        StreamPlayer streamPlayer = new StreamPlayer();
        streamPlayer.playStream(stream);
        //List<Voice> voices = service.getVoices().execute();
        //Log.i("Hey", voices.toString());


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                MainActivity.this.speakText();

            }
        });
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
