package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.ApacheHttpHelper;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dto.Tweet;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gdata.util.common.base.PercentEscaper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

public class TwitterRestDao implements CrdRepository<Tweet, String> {

  private HttpHelper httpHelper;


  /**
   * Parse JSON string to a object
   *
   * @param json JSON str
   * @param clazz object class
   * @param <T> Type
   * @return Object
   */
  public static <T> T toObjectFromJson(String json, Class clazz) throws IOException {

    ObjectMapper mapper = new ObjectMapper();

    return (T) mapper.readValue(json, clazz);

  }

  @Override
  public Tweet save(Tweet entity) {

    ApacheHttpHelper apacheHttpHelper = new ApacheHttpHelper();

    String uriString = "https://api.twitter.com/1.1/statuses/update.json?status=";

    String status = entity.getText();

    PercentEscaper percentEscaper = new PercentEscaper("", false);

    URI uri = URI.create(uriString + percentEscaper.escape(status));

    //Coordinates coordinates = entity.getCoordinates();

    String responseString = "";
    Tweet tweet = new Tweet();

    try {
      HttpResponse response = apacheHttpHelper.httpPost(uri);
      InputStream content = response.getEntity().getContent();

      BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
      String s = "";
      while ((s = buffer.readLine()) != null) {
        responseString += s;
      }

      tweet = toObjectFromJson(responseString, Tweet.class);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return tweet;
  }


  @Override
  public Tweet findById(String s) {

    ApacheHttpHelper apacheHttpHelper = new ApacheHttpHelper();

    URI uri = URI.create("https://api.twitter.com/1.1/statuses/show.json?id=" + s);

    Tweet tweet = new Tweet();

    try {
      String json = EntityUtils.toString(apacheHttpHelper.httpGet(uri).getEntity());
      tweet = toObjectFromJson(json, Tweet.class);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return tweet;

  }

  @Override
  public Tweet deleteById(String s) {

    ApacheHttpHelper apacheHttpHelper = new ApacheHttpHelper();

    String uriString = "https://api.twitter.com/1.1/statuses/destroy/";

    PercentEscaper percentEscaper = new PercentEscaper("", false);

    URI uri = URI.create(uriString + percentEscaper.escape(s) + ".json");

    //Coordinates coordinates = entity.getCoordinates();

    String responseString = "";
    Tweet tweet = new Tweet();

    try {
      HttpResponse response = apacheHttpHelper.httpPost(uri);
      InputStream content = response.getEntity().getContent();

      BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
      String text;
      while ((text = buffer.readLine()) != null) {
        responseString += text;
      }

      tweet = toObjectFromJson(responseString, Tweet.class);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return tweet;
  }
}
