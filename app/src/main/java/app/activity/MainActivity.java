package app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import app.adapter.Adapter;
import app.basefile.BaseFile;
import app.baseurl.BaseUrl;
import app.datamodel.Datamodel;
import app.datamodel.Model_Post;
import app.datamodel.Posts_List;
import app.db_sqlite.db_sqlite;
import app.proje_retrofit.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

  RelativeLayout relative_like;
  RecyclerView recy;
  ProgressBar progressBar;
  Adapter adapter;
  BaseUrl baseUrl;
  List<Model_Post> list = new ArrayList<>();
  public static String iandroid_id;
  db_sqlite db_sqlite;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    iandroid_id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);

    relative_like = (RelativeLayout) findViewById(R.id.relative_like);
    recy = (RecyclerView) findViewById(R.id.recy);
    progressBar = (ProgressBar) findViewById(R.id.progressBar);


    db_sqlite = new db_sqlite(MainActivity.this);
    get_post();

    relative_like.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, ActivityFavorite.class);
        startActivity(intent);
      }
    });

  }

  private void get_post() {
    if (db_sqlite.Check_Database() == true) {
      recy.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
      List<Model_Post> data = db_sqlite.Get_Post();
      adapter = new Adapter(MainActivity.this, data, new Adapter.Intercae_Like_Post() {
        @Override
        public void intercae_Like_Post(String idpost, ImageView imageView) {
          Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl.geturl())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
          BaseFile baseFile = retrofit.create(BaseFile.class);
          Call<Datamodel> calladdfavorite = baseFile.addFavorite(idpost, iandroid_id);
          calladdfavorite.enqueue(new Callback<Datamodel>() {
            @Override
            public void onResponse(Call<Datamodel> call, Response<Datamodel> response) {
              if (response.isSuccessful()) {
                Datamodel datamodel = response.body();
                if (datamodel.getStatus().equals("ok")) {
                  imageView.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.icon_favorite, null));
                } else {
                  String error = datamodel.getError();
                  if (error.equals("شما قبلا این پست را به لیست علاقه مندی خود اضافه کرده اید")) {
                    Toast.makeText(MainActivity.this, "شما قبلا این پست را به لیست علاقه مندی خود اضافه کرده اید", Toast.LENGTH_LONG).show();
                  } else {
                    Toast.makeText(MainActivity.this, "این پست به علاقه مندی اضافه نشد مجددا تلاش کنید", Toast.LENGTH_LONG).show();
                  }
                }
              }
            }

            @Override
            public void onFailure(Call<Datamodel> call, Throwable t) {
              Toast.makeText(MainActivity.this, "خطا در دریافت اطلاعات از سرور ..", Toast.LENGTH_SHORT).show();
            }
          });
        }
      });
      recy.setAdapter(adapter);
    } else {
      progressBar.setVisibility(View.VISIBLE);
    Retrofit retrofit = new Retrofit.Builder()
      .baseUrl(baseUrl.geturl())
      .addConverterFactory(GsonConverterFactory.create())
      .build();
    BaseFile baseFile = retrofit.create(BaseFile.class);
    Call<Posts_List.posts> calltoken = baseFile.PostSend(iandroid_id);
    calltoken.enqueue(new Callback<Posts_List.posts>() {
      @Override
      public void onResponse(Call<Posts_List.posts> call, Response<Posts_List.posts> response) {
        progressBar.setVisibility(View.GONE);
        if (response.isSuccessful()) {
          list.clear();
          for (int i = 0; i < response.body().getPosts().size(); i++) {
            Model_Post model_post = new Model_Post();
            model_post.setId(response.body().getPosts().get(i).getId());
            model_post.setTitle(response.body().getPosts().get(i).getTitle());
            model_post.setImage(response.body().getPosts().get(i).getImage());
            model_post.setLinkvideo(response.body().getPosts().get(i).getLinkvideo());
            model_post.setUserfavorite(response.body().getPosts().get(i).getUserfavorite());
            list.add(model_post);
            db_sqlite.Get_information(list);
          }
          recy.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
          adapter = new Adapter(MainActivity.this, list, new Adapter.Intercae_Like_Post() {
            @Override
            public void intercae_Like_Post(String idpost, ImageView imageView) {
              Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl.geturl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
              BaseFile baseFile = retrofit.create(BaseFile.class);
              Call<Datamodel> calladdfavorite = baseFile.addFavorite(idpost, iandroid_id);
              calladdfavorite.enqueue(new Callback<Datamodel>() {
                @Override
                public void onResponse(Call<Datamodel> call, Response<Datamodel> response) {
                  if (response.isSuccessful()) {
                    Datamodel datamodel = response.body();
                    if (datamodel.getStatus().equals("ok")) {
                      imageView.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.icon_favorite, null));
                    } else {
                      String error = datamodel.getError();
                      if (error.equals("شما قبلا این پست را به لیست علاقه مندی خود اضافه کرده اید")) {
                        Toast.makeText(MainActivity.this, "شما قبلا این پست را به لیست علاقه مندی خود اضافه کرده اید", Toast.LENGTH_LONG).show();
                      } else {
                        Toast.makeText(MainActivity.this, "این پست به علاقه مندی اضافه نشد مجددا تلاش کنید", Toast.LENGTH_LONG).show();
                      }
                    }
                  }
                }

                @Override
                public void onFailure(Call<Datamodel> call, Throwable t) {
                  Toast.makeText(MainActivity.this, "خطا در دریافت اطلاعات از سرور ..", Toast.LENGTH_SHORT).show();
                }
              });
            }
          });
          recy.setAdapter(adapter);
        }
      }

      @Override
      public void onFailure(Call<Posts_List.posts> call, Throwable t) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(MainActivity.this, "خطا در دریافت اطلاعات", Toast.LENGTH_SHORT).show();
      }
    });
  }
  }


  @Override
  protected void onResume() {
    get_post();
    super.onResume();
  }
}