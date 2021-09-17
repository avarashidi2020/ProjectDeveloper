package app.datamodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Posts_List {

  public class posts{
    @SerializedName("posts")
    public List<postList> posts= null;

    public List<postList> getPosts() {
      return posts;
    }

    public void setPosts(List<postList> posts) {
      this.posts = posts;
    }
  }


  public class postList{
    @SerializedName("id")
    String id;
    @SerializedName("title")
    String title;
    @SerializedName("image")
    String image;
    @SerializedName("linkvideo")
    String linkvideo;
    @SerializedName("userfavorite")
    String userfavorite;

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

    public String getUserfavorite() {
      return userfavorite;
    }

    public void setUserfavorite(String userfavorite) {
      this.userfavorite = userfavorite;
    }
  }
}
