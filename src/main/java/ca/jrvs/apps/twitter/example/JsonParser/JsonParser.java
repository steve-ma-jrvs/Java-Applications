package ca.jrvs.apps.twitter.example.JsonParser;

import ca.jrvs.apps.twitter.example.JsonParser.dto.Company;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class JsonParser {

  /**
   * Convert a java object to JSON string
   *
   * @param object input object
   * @return JSON String
   */
  public static String toJson(Object object, boolean prettyJson, boolean includeNullValues)
      throws JsonProcessingException {

    ObjectMapper mapper = new ObjectMapper();

    return mapper.writeValueAsString(object);

  }


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


  public static void main(String[] args) throws IOException {

    ObjectMapper mapper = new ObjectMapper();

    String filePath = "/Users/stevenma/java_apps/src/main/java/ca/jrvs/apps/twitter/example/JsonParser/company.json";

    Company company = mapper.readValue(new File(filePath), Company.class);

    String r = toJson(company, true, true);

    Company c = toObjectFromJson(r, Company.class);

    System.out.println(c);

  }


}
