package app.activity;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityEnhanced extends AppCompatActivity {


  @Override
  protected void onResume() {
    super.onResume();
    G.currentActivity = this;
  }
}
