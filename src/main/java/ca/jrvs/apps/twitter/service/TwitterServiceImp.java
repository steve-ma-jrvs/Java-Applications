package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dto.Coordinates;
import ca.jrvs.apps.twitter.dto.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import ca.jrvs.apps.twitter.util.StringUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Method;
import static ca.jrvs.apps.twitter.util.TweetUtil.*;

@Service
public class TwitterServiceImp implements TwitterService {

    private CrdDao dao;

    @Autowired
    public TwitterServiceImp(CrdDao dao) {
        this.dao = dao;
    }

    @Override
    public Tweet postTweet(String text, Double latitude, Double longitude) {
        //Validate tweet
        validatePostTweet(text, latitude, longitude);

        //Build tweet
        Tweet tweet = new Tweet();
        tweet.setText(text);
        Coordinates coordinates = new Coordinates();
        coordinates.setCoordinates(Arrays.asList(longitude, latitude));
        tweet.setCoordinates(coordinates);

        //Post tweet
        try {
            Tweet responseTweet = (Tweet) dao.create(tweet);
            printTweet(responseTweet);
        } catch (Exception e) {
            throw new RuntimeException("Fail to post tweet");
        }

        return tweet;
    }

    @Override
    public Tweet showTweet(String id, String[] fields) {
        Tweet tweet;
        Tweet newTweet;

        //validate id
        if(!validID(id)){
            throw new IllegalArgumentException("ID must be number");
        }

        //Get tweet and Print
        tweet = (Tweet) dao.findById(id);
        newTweet = selectFields(tweet, fields);
        printTweet(newTweet);


        return tweet;
    }

    @Override
    public List<Tweet> deleteTweets(String[] ids) {
        List<Tweet> tweets = new ArrayList<>();
        for (String id : ids) {
            if(!validID(id)){
                System.out.println(id);
                throw new IllegalArgumentException("ID must be valid");
            } else {
                Tweet tweet = (Tweet) dao.deleteById(id);
                printTweet(tweet);
                tweets.add(tweet);
            }
        }
        return tweets;
    }

    private boolean validID (String id){
        //validate id
        StringBuilder sb = new StringBuilder();
        sb.append("\\d{")
            .append(id.length())
            .append("}");
        Pattern pattern = Pattern.compile(sb.toString());

        return pattern.matcher(id).matches();
    }

    /**
     * select fields in tweet object with java reflection
     *
     * @param tweet tweet object
     * @param fields fields to be selected
     *
     * @return Tweet with id & only selected fields
     */

    private Tweet selectFields(Tweet tweet, String[] fields) {
        if (fields == null || fields.length == 0) {
            return tweet;
        }

        Tweet newTweet = new Tweet();
        // HashMap saving getter : setter
        HashMap<String, String> methodNames = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (String field : fields) {
            sb.append("get")
                .append(field.substring(0, 1).toUpperCase())
                .append(field.substring(1).toLowerCase());
            sb.append("set")
                .append(field.substring(0, 1).toUpperCase())
                .append(field.substring(1).toLowerCase());
            methodNames.put(sb.toString(), sb2.toString());
        }


//        Class<?> c = Class.forName("class name");
//        Method method = c.getDeclaredMethod("method name", parameterTypes);
//        method.invoke(objectToInvokeOn, params);
//
//        //get method that takes a String as argument
//        Method method = MyObject.class.getMethod("doSomething", String.class);
//
//        Object returnValue = method.invoke(null, "parameter-value1");

        for (Map.Entry<String, String> entry : methodNames.entrySet()) {
            try {
                // get with no argument
                Method getMethod = tweet.getClass().getMethod(entry.getKey());
                Object returnValue = getMethod.invoke(null);
                // set with arguments
                Method setMethod = newTweet.getClass().getMethod(entry.getValue(), Object.class);
                setMethod.invoke(Object.class, returnValue);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("No field named as " + entry.getKey());
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException("failed to set setters" + entry.getValue(), e);
            }

        }

        return newTweet;
    }


//
//
//
//    /**
//     * Select fields in tweet object by setting other fields to null
//     *
//     * @param tweet tweet object
//     * @param fields fields to be selected
//     */
//    protected Tweet selectFields(Tweet tweet, String[] fields) throws IOException {
//        if (fields == null || fields.length == 0) {
//            return tweet;
//        }
//        //rTweet = deep copy of tweet
//        Tweet rTweet = JsonUtil.toObjectFromJson(JsonUtil.toPrettyJson(tweet), Tweet.class);
//
//        //helper lambda function to remove leading and trailing spaces
//        Function<String[], String[]> trimStrArray = (items) -> Arrays.stream(items).map(String::trim)
//            .toArray(String[]::new);
//        //Make fieldSet for fast lookup and removal
//        Set<String> fieldSet = new HashSet<>(Arrays.asList(trimStrArray.apply(fields)));
//
//        /**
//         * for(method: Tweet.getMethods)
//         *   if method.name.prefix == "set"
//         *     if fieldSet contains JsonProperty.value
//         *       remove field from fieldSet
//         *     else
//         *       invoke rTweet setter with null argument
//         */
//        Predicate<Method> isSetter = (method) -> method.getName().startsWith("set");
//        Arrays.stream(Tweet.class.getMethods())
//            .filter(isSetter)
//            .forEach(setter ->
//            {
//                JsonProperty jsonProperty = setter.getDeclaredAnnotation(JsonProperty.class);
//                if (jsonProperty == null || StringUtil.isEmpty(jsonProperty.value())) {
//                    throw new RuntimeException(
//                        "@JsonProperty is not defined for method" + setter.getName());
//                }
//                String value = jsonProperty.value();
//                if (fieldSet.contains(value)) {
//                    fieldSet.remove(value);
//                } else {
//                    try {
//                        setter.invoke(rTweet, new Object[]{null});
//                    } catch (IllegalAccessException | InvocationTargetException e) {
//                        throw new RuntimeException("unable to set setter:" + setter.getName(), e);
//                    }
//                }
//            });
//
//        /**
//         * if fieldSet not empty
//         *   throw invalid fields exception
//         */
//        if (!fieldSet.isEmpty()) {
//            String invalidFields = String.join(",", fieldSet);
//            throw new RuntimeException("Found invalid select field(s):" + invalidFields);
//        }
//        return rTweet;
//    }

}
