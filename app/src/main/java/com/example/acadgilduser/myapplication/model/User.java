package com.example.acadgilduser.myapplication.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by acadgilduser on 17/10/16.
 */
public class User extends Model {

    @Column(name = "ServerId")
    @SerializedName("id")
    @Expose
    private
    Integer ServerId;

    @Column(name = "name")
    @SerializedName("name")
    @Expose
    private
    String name;

    @Column(name = "email")

    @SerializedName("email")
    @Expose
    private
    String email;

    @Column(name = "created_at")
    @SerializedName("created_at")
    @Expose
    private
    String createdAt;

    @Column(name = "updated_at")
    @SerializedName("updated_at")
    @Expose
    private
    String updatedAt;

    public User() {
        super();
    }

    public Integer getServerId() {
        return ServerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setServerId(Integer serverId) {
        ServerId = serverId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static List<User> all() {
        return new Select().from(User.class).execute();
    }
    @Override
    public String toString() {
        return "User{" +
                "ServerId=" + ServerId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
