package ca.jrvs.apps.twitter.dao.helper;

import java.io.IOException;
import java.net.URI;

import ca.jrvs.apps.twitter.util.StringUtil;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ApacheHttpHelper implements HttpHelper {

  private OAuthConsumer consumer;
  private HttpClient httpClient;

  /**
   * Constructor, getting keys and tokens
   */
  public ApacheHttpHelper(){
    String CONSUMER_KEY = System.getenv("CONSUMER_KEY");
    String CONSUMER_SECRET = System.getenv("CONSUMER_SECRET");
    String ACCESS_TOKEN = System.getenv("ACCESS_TOKEN");
    String TOKEN_SECRET = System.getenv("TOKEN_SECRET");

    if (StringUtil.isEmpty(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, TOKEN_SECRET)) {
      throw new RuntimeException("Unable to detect key and tokens from System env");
    }

    consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
    consumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);
    httpClient = new DefaultHttpClient();
  }

  @Override
  public HttpResponse httpPost(URI uri) {
    // create an HTTP POST request
    HttpPost request = new HttpPost(uri);
    // sign the request
    try {
      consumer.sign(request);
      return httpClient.execute(request);
    } catch (OAuthException | IOException e) {
      throw new RuntimeException("Failed to execute", e);
    }

  }

  @Override
  public HttpResponse httpPost(URI uri, StringEntity stringEntity) {
    // create an HTTP POST request
    HttpPost request = new HttpPost(uri);
    // sign the request
    try {
      request.setEntity(stringEntity);
      consumer.sign(request);
      return httpClient.execute(request);
    } catch (OAuthException | IOException e) {
      throw new RuntimeException("Failed to execute", e);
    }
  }

  @Override
  public HttpResponse httpGet(URI uri) {
    // create an HTTP GET request
    HttpGet request = new HttpGet(uri);
    // sign the request
    try {
      consumer.sign(request);
      return httpClient.execute(request);
    } catch (OAuthException | IOException e) {
      throw new RuntimeException("Failed to execute", e);
    }
  }
}
