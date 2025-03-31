package com.example.twitter.user;

public class UrlMapping {
  public static final String API = "/twitter";

  public static final String AUTH = API + "/auth";
  public static final String CLIENT = API + "/clients";
  public static final String POSTARE = API + "/posts";
  public static final String ID_STRING = "/{id}";
  public static final String SIGN_IN = "/sign-in";
  public static final String SIGN_UP = "/sign-up";
  public static final String UPDATE = "/update" + ID_STRING;
  public static final String DELETE = "/delete" + ID_STRING;
  public static final String FOLLOW = "/followAction";
  public static final String SHOW_FOLLOWERS = "/followers" + ID_STRING;
  public static final String SHOW_FOLLOWING = "/following" + ID_STRING;
  public static final String EDIT = "/edit" + ID_STRING;
  public static final String CREATE_POST = "/create";
  public static final String SHOW_ALL = "/show";
  public static final String SHOW_POST = "/without-parent";
  public static final String CLIENT_DATA = "/client_data" + ID_STRING;
  public static final String SHOW_REPLYS = "/replys" + ID_STRING;
  public static final String LIKE = "/like" + ID_STRING;
  public static final String REPLY = "/replys" + ID_STRING;
  public static final String VIEW_PRIVACY = "/view" + ID_STRING;

}