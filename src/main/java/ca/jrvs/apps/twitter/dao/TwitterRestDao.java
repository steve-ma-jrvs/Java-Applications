package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dto.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TwitterRestDao implements CrdDao<Tweet, String> {

  //URI constants
  private static final String API_BASE_URI = "https://api.twitter.com";
  private static final String POST_PATH = "/1.1/statuses/update.json";
  private static final String SHOW_PATH = "/1.1/statuses/show.json";
  private static final String DELETE_PATH = "/1.1/statuses/destroy";
  //URI symbols
  private static final String QUERY_SYM = "?";
  private static final String AMPERSAND = "&";
  private static final String EQUAL = "=";
  //Response code
  private static final int HTTP_OK = 200;

  private HttpHelper httpHelper;

  @Autowired
  public TwitterRestDao(HttpHelper httpHelper) {
    this.httpHelper = httpHelper;
  }


  @Override
  public Tweet create(Tweet entity) {
    //Get tweet text and geo info
    String text = entity.getText();
    Double longitude = entity.getCoordinates().getCoordinates().get(0);
    Double latitude = entity.getCoordinates().getCoordinates().get(1);

    //Construct URI
    URI uri;
    try {
      StringBuilder sb = new StringBuilder();
      sb.append(API_BASE_URI)
          .append(POST_PATH)
          .append(QUERY_SYM);
      appendQueryParam(sb, "status", URLEncoder.encode(text, StandardCharsets.UTF_8.name()), true);
      appendQueryParam(sb, "long", longitude.toString(), false);
      appendQueryParam(sb, "lat", latitude.toString(), false);
      uri = new URI(sb.toString());
    } catch (UnsupportedEncodingException | URISyntaxException e) {
      throw new IllegalArgumentException("Unable to construct URI", e);
    }

    //Execute Http Request
    HttpResponse response = httpHelper.httpPost(uri);

    //Validate response and deser response to Tweet Object
    return parseResponseBody(response);
  }


  @Override
  public Tweet findById(String id) {
    //Construct URI
    URI uri;
    try {
      StringBuilder sb = new StringBuilder();
      sb.append(API_BASE_URI)
          .append(SHOW_PATH)
          .append(QUERY_SYM);
      appendQueryParam(sb, "id", id, true);
      uri = new URI(sb.toString());
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException("Unable to construct URI", e);
    }

    //Execute Http Request
    HttpResponse response = httpHelper.httpGet(uri);

    //Validate response and convert response to Tweet Object
    return parseResponseBody(response);
  }


  @Override
  public Tweet deleteById(String id) {
    //Construct URI
    URI uri;
    try {
      StringBuilder sb = new StringBuilder();
      sb.append(API_BASE_URI)
          .append(DELETE_PATH)
          .append("/")
          .append(id)
          .append(".json");
      uri = new URI(sb.toString());
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException("Unable to construct URI", e);
    }

    //Execute HTTP Request
    HttpResponse response = httpHelper.httpPost(uri);

    //Validate response and convert response to Tweet object
    return parseResponseBody(response);
  }


  private void appendQueryParam(StringBuilder sb, String key, String value, boolean firstParam) {
    if (!firstParam) {
      sb.append(AMPERSAND);
    }
    sb.append(key)
        .append(EQUAL)
        .append(value);
  }


  /**
   * Check response status code Convert Response Entity to Tweet
   */
  protected Tweet parseResponseBody(HttpResponse response) {
    Tweet tweet = null;

    //Check response status
    int status = response.getStatusLine().getStatusCode();
    if (status != HTTP_OK) {
      try {
        System.out.println(EntityUtils.toString(response.getEntity()));
      } catch (IOException e) {
        System.out.println("Response has no entity");
      }
      throw new RuntimeException("Unexpected HTTP status:" + status);
    }

    if (response.getEntity() == null) {
      throw new RuntimeException("Empty response body");
    }

    //Convert Response Entity to str
    String jsonStr;
    try {
      jsonStr = EntityUtils.toString(response.getEntity());
    } catch (IOException e) {
      throw new RuntimeException("Failed to convert entity to String", e);
    }

    //Deser JSON string to Tweet object
    try {
      tweet = (Tweet) JsonUtil.toObjectFromJson(jsonStr, Tweet.class);
    } catch (IOException e) {
      throw new RuntimeException("Unable to convert JSON str to Object", e);
    }

    return tweet;
  }
}
