package app.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import app.proje_retrofit.R;

public class ActivityMore extends AppCompatActivity {


  VideoView videoview;
  Intent intent;
  ProgressBar progressbar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_more);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    videoview = findViewById(R.id.videoview);
    progressbar = findViewById(R.id.progressbar);





    intent = getIntent();
      String link_video = intent.getStringExtra("linkvideo");

      MediaController mediaController = new MediaController(this);
      mediaController.setAnchorView(videoview);
      mediaController.setMediaPlayer(videoview);
      videoview.setMediaController(mediaController);
      videoview.setVideoPath(link_video);

    videoview.requestFocus();
    videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
      @Override
      public void onPrepared(MediaPlayer mp) {
        progressbar.setVisibility(View.GONE);
        videoview.start();
      }
    });

  }

  @Override
  public void onBackPressed() {
    videoview.clearFocus();
    videoview.pause();
    finish();
    super.onBackPressed();
  }
}