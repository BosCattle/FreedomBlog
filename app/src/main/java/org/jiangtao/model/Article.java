package org.jiangtao.model;

/**
 * Class:Article <br>
 * Description:文章bean数据 <br>
 * Creator: MrJiang <br>
 * Date: 2016/3/10 18:25 <br>
 */
public class Article {
  private String title;
  private String content;
  private String author;
  private String time;

  public Article(String title, String time, String author, String content) {
    this.title = title;
    this.time = time;
    this.author = author;
    this.content = content;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }
}
