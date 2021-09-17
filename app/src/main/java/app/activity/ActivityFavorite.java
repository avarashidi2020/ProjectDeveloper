package app.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import app.adapter.AdapterFavorite;
import app.basefile.BaseFile;
import app.baseurl.BaseUrl;
import app.datamodel.Datamodel;
import app.datamodel.Model_Post;
import app.datamodel.Posts_List_Favorite;
import app.proje_retrofit.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityFavorite extends AppCompatActivity {

  RecyclerView recyclerview;
  ProgressBar progressBar;
  TextView txt_error;
  BaseUrl baseUrl;
  List<Model_Post> list = new ArrayList<>();
  AdapterFavorite adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_favorite);

    recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
    progressBar = (ProgressBar) findViewById(R.id.progressBar);
    txt_error = (TextView) findViewById(R.id.txt_error);


    Retrofit retrofit = new Retrofit.Builder()
      .baseUrl(baseUrl.geturl())
      .addConverterFactory(GsonConverterFactory.create())
      .build();
    BaseFile baseFile = retrofit.create(BaseFile.class);
    Call<Posts_List_Favorite.list_favorite> calltoken = baseFile.Postsfavorit(MainActivity.iandroid_id);
    calltoken.enqueue(new Callback<Posts_List_Favorite.list_favorite>() {
      @Override
      public void onResponse(Call<Posts_List_Favorite.list_favorite> call, Response<Posts_List_Favorite.list_favorite> response) {
        progressBar.setVisibility(View.GONE);
        if (response.isSuccessful()) {
          if (response.body().getPosts().size()>0){
            txt_error.setVisibility(View.GONE);
          for (int i = 0; i < response.body().getPosts().size(); i++) {
            Model_Post model_post = new Model_Post();
            model_post.setId(response.body().getPosts().get(i).getId());
            model_post.setTitle(response.body().getPosts().get(i).getTitle());
            model_post.setImage(response.body().getPosts().get(i).getImage());
            model_post.setLinkvideo(response.body().getPosts().get(i).getLinkvideo());
            list.add(model_post);
          }
          recyclerview.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
          adapter = new AdapterFavorite(ActivityFavorite.this, list, new AdapterFavorite.Intercae_delete_Post() {
            @Override
            public void intercae_delete_Post(String idpost, int possition) {
              Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl.geturl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
              BaseFile baseFile = retrofit.create(BaseFile.class);
              Call<Datamodel> calladdfavorite = baseFile.deleteFavorite(idpost, MainActivity.iandroid_id);
              calladdfavorite.enqueue(new Callback<Datamodel>() {
                @Override
                public void onResponse(Call<Datamodel> call, Response<Datamodel> response) {
                  if (response.isSuccessful()) {
                    Datamodel datamodel = response.body();
                    if (datamodel.getStatus().equals("ok")) {
                      list.remove(possition);
                      adapter.notifyItemRemoved(possition);
                      adapter.notifyItemRangeChanged(possition, list.size());
                    } else {
                      Toast.makeText(ActivityFavorite.this, "پست از علاقه مندی حذف شد", Toast.LENGTH_LONG).show();
                    }
                  }
                }

                @Override
                public void onFailure(Call<Datamodel> call, Throwable t) {
                  Toast.makeText(ActivityFavorite.this, "خطا در دریافت اطلاعات از سرور ..", Toast.LENGTH_SHORT).show();
                  progressBar.setVisibility(View.GONE);
                }
              });
            }
          });
          recyclerview.setAdapter(adapter);
          }else {
            txt_error.setVisibility(View.VISIBLE);
          }
        }
      }

      @Override
      public void onFailure(Call<Posts_List_Favorite.list_favorite> call, Throwable t) {
        Toast.makeText(ActivityFavorite.this, "خطا در دریافت اطلاعات از سرور..", Toast.LENGTH_SHORT).show();
      }
    });
  }
}