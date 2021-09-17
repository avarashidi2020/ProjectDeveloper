package app.activity;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;


public class G extends Application {


  public static Context context;
  public static Activity currentActivity;

  public static Handler handler;
  public static SharedPreferences preferences;


  @Override
  public void onCreate() {
    super.onCreate();

    context = getApplicationContext();
    handler = new Handler();
    preferences = PreferenceManager.getDefaultSharedPreferences(this);



  }
}
