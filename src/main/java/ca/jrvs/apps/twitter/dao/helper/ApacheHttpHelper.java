package ca.jrvs.apps.twitter.dao.helper;

import java.net.URI;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class ApacheHttpHelper implements HttpHelper {

  private static String CONSUMER_KEY = System.getenv("CONSUMER_KEY");
  private static String CONSUMER_SECRET = System.getenv("CONSUMER_SECRET");
  private static String ACCESS_TOKEN = System.getenv("ACCESS_TOKEN");
  private static String TOKEN_SECRET = System.getenv("TOKEN_SECRET");
  private URI uri;
  private StringEntity stringEntity;

  @Override
  public HttpResponse httpPost(URI uri) throws Exception {

    // setup oauth
    OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
    consumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);

    // create an HTTP GET request
    HttpPost request = new HttpPost(uri);

    // sign the request (add headers)
    consumer.sign(request);

    // send/execute the request
    HttpClient httpClient = new DefaultHttpClient();

    return httpClient.execute(request);

  }

  @Override
  public HttpResponse httpPost(URI uri, StringEntity stringEntity) {

    return null;
  }

  @Override
  public HttpResponse httpGet(URI uri) throws Exception {

    // setup oauth
    OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
    consumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);

    // create an HTTP GET request
    HttpGet request = new HttpGet(uri);

    // sign the request (add headers)
    consumer.sign(request);

    // send/execute the request
    HttpClient httpClient = new DefaultHttpClient();
    return httpClient.execute(request);

  }
}
