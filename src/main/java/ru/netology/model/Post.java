package ru.netology.model;

public class Post {
  private boolean isRemoved;
  private long id;
  private String content;

  public Post() {
  }

  public Post(long id, String content) {
    this.id = id;
    this.content = content;
    this.isRemoved = false;
  }

  public long getId() {
    return id;
  }

  public boolean isRemoved(){
    return isRemoved;
  }
  public void setRemoved(boolean isRemoved){
    this.isRemoved = isRemoved;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
