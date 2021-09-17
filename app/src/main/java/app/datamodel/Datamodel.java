package app.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datamodel {



 @SerializedName("status")
 @Expose
 String status;
 String error;

 @SerializedName("id")
 @Expose
 String id;

  @SerializedName("title")
  @Expose
  String title;

  @SerializedName("image")
  @Expose
  String image;

  @SerializedName("linkvideo")
  @Expose
  String linkvideo;


  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getImage() {
    return image;
  }

  public String getLinkvideo() {
    return linkvideo;
  }
 public String getStatus() {
  return status;
 }

 public String getError() {
  return error;
 }

 public void setStatus(String status) {
  this.status = status;
 }

 public void setError(String error) {
  this.error = error;
 }

 public void setId(String id) {
  this.id = id;
 }

 public void setTitle(String title) {
  this.title = title;
 }

 public void setImage(String image) {
  this.image = image;
 }

 public void setLinkvideo(String linkvideo) {
  this.linkvideo = linkvideo;
 }
}
