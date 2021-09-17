package app.activity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import app.proje_retrofit.R;

public class ActivitySplash extends ActivityEnhanced {

  public TextView txt_titleNoInternet;
  public TextView txt_connectInternet;
  public ProgressBar progressBar;
  public NetworkInfo networkInfo;
  public SwipeRefreshLayout swip_refresh;
  public ImageView img_Replay;
  public LinearLayout linear;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);

    //// تیکه کد حذف کردن TitleBar بالای صفحه
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    casted();

    txt_titleNoInternet.setVisibility(View.GONE);


    //// متد چک کردن اینترنت کاربر هنگام ورود به برنامه
    networkOnlineStatus();

    //// متد انیمیشن دادن به تکست ویو لطفا صبور باشید در حال دریافت اطلاعات..
    txt_connectInternet.setText("لطفا صبور باشید در حال دریافت اطلاعات...");
    AnimationTextViewDaryaftEtelaat();

    swip_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        swip_refresh.setRefreshing(false);
        txt_titleNoInternet.setVisibility(View.GONE);
        img_Replay.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        //// متد انیمیشن دادن به تکست ویو لطفا صبور باشید در حال دریافت اطلاعات..
        txt_connectInternet.setVisibility(View.VISIBLE);
        AnimationTextViewDaryaftEtelaat();

        networkOnlineStatus1();
      }
    });

    //// تیکه کد هنگام کلیک کردن روی لایه و چک کردن اینترنت کاربر
    linear.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        swip_refresh.setRefreshing(false);
        txt_titleNoInternet.setVisibility(View.GONE);
        img_Replay.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        //// متد انیمیشن دادن به تکست ویو لطفا صبور باشید در حال دریافت اطلاعات..
        txt_connectInternet.setVisibility(View.VISIBLE);
        AnimationTextViewDaryaftEtelaat();

        networkOnlineStatus1();
      }
    });
  }
    //// متد چک کردن اینترنت کاربر هنگام ورود به برنامه
    public void networkOnlineStatus() {
      ConnectivityManager manager = (ConnectivityManager) G.context.getSystemService(Context.CONNECTIVITY_SERVICE);
      networkInfo = manager.getActiveNetworkInfo();

      if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
        G.handler.postDelayed(new Runnable() {
          @Override
          public void run() {
            Intent intent = new Intent(ActivitySplash.this, MainActivity.class);
            ActivityOptions options = ActivityOptions.makeCustomAnimation(G.currentActivity, R.anim.in, R.anim.out);
            G.currentActivity.startActivity(intent, options.toBundle());
            finish();
          }
        }, 4000);
      } else {
        G.handler.postDelayed(new Runnable() {
          @Override
          public void run() {
            progressBar.setVisibility(View.GONE);
            txt_connectInternet.setVisibility(View.GONE);
            img_Replay.setImageResource(R.drawable.replay);
            txt_titleNoInternet.setVisibility(View.VISIBLE);
            txt_titleNoInternet.setText("ارتباط با اینترنت برقرار نیست");
            AnimationTextViewEmkanat();
            AnimationImageViewEmkanat();
          }
        }, 4000);
      }

  }

  //// متد انیمیشن دادن به تکست ویو لطفا صبور باشید در حال دریافت اطلاعات
  private void AnimationTextViewDaryaftEtelaat() {
    YoYo.with(Techniques.FadeIn)
      .duration(2000)
      .playOn(findViewById(R.id.txt_connectInternet));
  }
  //// متد انیمیشن دادن به عکس ارتباط با اینترنت برقرار نیست
  private void AnimationImageViewEmkanat() {
    YoYo.with(Techniques.FadeIn)
      .duration(2000)
      .playOn(findViewById(R.id.img_Replay));
  }
  //// متد انیمیشن دادن به تکست ویو ارتباط با اینترنت برقرار نیست
  private void AnimationTextViewEmkanat() {
    YoYo.with(Techniques.FadeIn)
      .duration(2000)
      .playOn(findViewById(R.id.txt_titleNoInternet));
  }
  public void networkOnlineStatus1() {
    ConnectivityManager manager = (ConnectivityManager) G.context.getSystemService(Context.CONNECTIVITY_SERVICE);
    networkInfo = manager.getActiveNetworkInfo();

    if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
      G.handler.postDelayed(new Runnable() {
        @Override
        public void run() {
          Intent intent = new Intent(ActivitySplash.this, MainActivity.class);
          ActivityOptions options = ActivityOptions.makeCustomAnimation(G.currentActivity, R.anim.in, R.anim.out);
          G.currentActivity.startActivity(intent, options.toBundle());
          finish();

        }
      }, 4000);
    } else {
      G.handler.postDelayed(new Runnable() {
        @Override
        public void run() {
          progressBar.setVisibility(View.GONE);
          txt_connectInternet.setVisibility(View.GONE);
          img_Replay.setVisibility(View.VISIBLE);
          txt_titleNoInternet.setVisibility(View.VISIBLE);
          AnimationTextViewEmkanat();
          AnimationImageViewEmkanat();

        }
      }, 4000);
    }
  }

  private void casted(){

    //// کست کردن ویجت ها در اکتیویتی اسپلش
    swip_refresh = (SwipeRefreshLayout) findViewById(R.id.swip_refresh);
    txt_connectInternet = (TextView) findViewById(R.id.txt_connectInternet);
    img_Replay = (ImageView) findViewById(R.id.img_Replay);
    progressBar = (ProgressBar) findViewById(R.id.progressBar);
    txt_titleNoInternet = (TextView) findViewById(R.id.txt_titleNoInternet);
    linear = (LinearLayout) findViewById(R.id.linear);
  }

}
