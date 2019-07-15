package ca.jrvs.apps.twitter.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(
        {
                "truncated",
                "source",
                "in_reply_to_status_id",
                "in_reply_to_status_id_str",
                "in_reply_to_user_id",
                "in_reply_to_user_id_str",
                "in_reply_to_screen_name",
                "user",
                "geo",
                "place",
                "contributors",
                "is_quote_status",
                "lang"
        }
)
public class Tweet {


    private String created_at;
    private long id;
    private String id_str;
    private String text;
    private Entities entities;
    private Coordinates coordinates;
    private Long retweet_count;
    private Long favorite_count;
    private Boolean favorited;
    private Boolean retweeted;

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getId_str() {
        return id_str;
    }

    public void setId_str(String id_str) {
        this.id_str = id_str;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Entities getEntities() {
        return entities;
    }

    public void setEntities(Entities entities) {
        this.entities = entities;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Long getRetweet_count() {
        return retweet_count;
    }

    public void setRetweet_count(Long retweet_count) {
        this.retweet_count = retweet_count;
    }

    public Long getFavorite_count() {
        return favorite_count;
    }

    public void setFavorite_count(Long favorite_count) {
        this.favorite_count = favorite_count;
    }

    public Boolean getFavorited() {
        return favorited;
    }

    public void setFavorited(Boolean favorited) {
        this.favorited = favorited;
    }

    public Boolean getRetweeted() {
        return retweeted;
    }

    public void setRetweeted(Boolean retweeted) {
        this.retweeted = retweeted;
    }
}
