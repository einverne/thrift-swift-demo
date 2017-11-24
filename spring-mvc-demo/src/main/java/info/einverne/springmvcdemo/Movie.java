package info.einverne.springmvcdemo;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by mi on 17-11-24.
 */
public class Movie {
    @Expose
    private String imdbId;
    private String director;
    @Expose
    private List<ActorGson> actors;

    public Movie() {
    }

    public Movie(String imdbId, String director, List<ActorGson> actors) {
        this.imdbId = imdbId;
        this.director = director;
        this.actors = actors;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public List<ActorGson> getActors() {
        return actors;
    }

    public void setActors(List<ActorGson> actors) {
        this.actors = actors;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "imdbId='" + imdbId + '\'' +
                ", director='" + director + '\'' +
                ", actors=" + actors +
                '}';
    }
}
