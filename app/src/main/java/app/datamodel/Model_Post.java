package app.datamodel;

public class Model_Post {

  String id;
  String title;
  String image;
  String linkvideo;
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
