package player.jmdevelopers.com.mediaplayer;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private SeekBar seekBarvolume;
    private AudioManager   audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.music);

    }
    private void iniciarseekbar(){
        seekBarvolume=findViewById(R.id.volumeID);
        // configurar audio meneger
    audioManager= (AudioManager) getSystemService(Context.AUDIO_SERVICE);
    // valores de volume
        int volumemaximo=audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int volumeatual=audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        // VOLUMES DA SEEKBAR
        seekBarvolume.setMax(volumemaximo);
        // progresso atual
        seekBarvolume.setProgress(volumeatual);
        seekBarvolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,AudioManager.FLAG_SHOW_UI);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    public void executarsom(View view) {
        if (mediaPlayer != null) {
            mediaPlayer.start();

        }
    }

    public void pausarsom(View view) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();

        }
    }
        public void paraasom(View view){
            if(mediaPlayer.isPlaying()){
                mediaPlayer.stop();
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.music);


            }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer!=null && mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer=null;


        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();

        }
    }
}
