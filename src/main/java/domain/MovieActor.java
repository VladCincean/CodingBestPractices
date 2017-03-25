package domain;

import java.util.Objects;

/**
 * Created by vlad on 23.03.2017.
 *
 * Class for joining movies with actors.
 */
public class MovieActor extends BaseEntity<Long> {
    private Long movieId;
    private Long actorId;

    public MovieActor(Long id, Long movieId, Long actorId) {
        super.setId(id);
        this.movieId = movieId;
        this.actorId = actorId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getActorId() {
        return actorId;
    }

    public void setActorId(Long actorId) {
        this.actorId = actorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieActor that = (MovieActor) o;
        return Objects.equals(movieId, that.movieId) &&
                Objects.equals(actorId, that.actorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, actorId);
    }

    @Override
    public String toString() {
        return "MovieActor{" +
                "movieId=" + movieId +
                ", actorId=" + actorId +
                '}' + super.toString();
    }
}
