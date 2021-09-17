package app.datamodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Posts_List_Favorite {

  public class list_favorite{
    @SerializedName("list_favorite")
    public List<list_favoritess> posts= null;

    public List<list_favoritess> getPosts() {
      return posts;
    }

    public void setPosts(List<list_favoritess> posts) {
      this.posts = posts;
    }
  }


  public class list_favoritess{
    @SerializedName("id")
    String id;
    @SerializedName("title")
    String title;
    @SerializedName("image")
    String image;
    @SerializedName("linkvideo")
    String linkvideo;



    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public String getImage() {
      return image;
    }

    public void setImage(String image) {
      this.image = image;
    }

    public String getLinkvideo() {
      return linkvideo;
    }

    public void setLinkvideo(String linkvideo) {
      this.linkvideo = linkvideo;
    }

  }
}
