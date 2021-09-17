package app.basefile;

import app.datamodel.Datamodel;
import app.datamodel.Posts_List;
import app.datamodel.Posts_List_Favorite;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface BaseFile {

  @POST("get.php")
  @FormUrlEncoded
  Call<Posts_List.posts> PostSend(@Field("token") String token);

  @POST("addfavorite.php")
  @FormUrlEncoded
  Call<Datamodel> addFavorite(@Field("idpost") String idpost,@Field("token") String token);

  @POST("get_post_favorite.php")
  @FormUrlEncoded
  Call<Posts_List_Favorite.list_favorite> Postsfavorit(@Field("token") String token);


  @POST("delete_favorite.php")
  @FormUrlEncoded
  Call<Datamodel> deleteFavorite(@Field("idpost") String idpost,@Field("token") String token);


  @POST("edit.php")
  @FormUrlEncoded
  Call<Datamodel> editTitle(@Field("idpost") String idpost,@Field("title") String title);


}
