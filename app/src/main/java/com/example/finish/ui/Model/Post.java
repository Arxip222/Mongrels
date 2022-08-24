package com.example.finish.ui.Model;

public class Post {
    public String postid;
    public String postimage;
    public String description;
    public String publisher;
    public static String PointId;

    public Post(String postid, String postimage, String description, String publisher, String PointId) {
        this.postid = postid;
        this.postimage = postimage;
        this.description = description;
        this.publisher = publisher;
        this.PointId = PointId;
    }

    public Post() {
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getPostimage() {
        return postimage;
    }

    public void setPostimage(String postimage) {
        this.postimage = postimage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPointId() {
        return PointId;
    }

    public void setPointId(String pointId) {
        this.PointId = pointId;
    }
}