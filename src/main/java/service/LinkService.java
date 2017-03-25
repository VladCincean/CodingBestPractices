package service;

import domain.Actor;
import domain.Movie;
import domain.MovieActor;
import repository.Repository;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by vlad on 24.03.2017.
 */
public class LinkService {
    private Repository<Long, MovieActor> linkRepository;

    public LinkService(Repository<Long, MovieActor> linkRepository) {
        this.linkRepository = linkRepository;
    }

    /**
     * Provides all movies an actor acted in.
     *
     * @param actor
     *          must be not null.
     * @return the set of all movies an actor acted in.
     */
    public Set<Long> getActorMovies(Actor actor) {
        Iterable<MovieActor> links = linkRepository.findAll();
        return StreamSupport.stream(links.spliterator(), false)
                .filter(l -> l.getActorId().equals(actor.getId()))
                .map(MovieActor::getMovieId)
                .collect(Collectors.toSet());
    }

    /**
     * Provides all actors that acted in a movie.
     *
     * @param movie
     *          must be not null.
     * @return the set of all actors that acted in.
     */
    public Set<Long> getMovieActors(Movie movie) {
        Iterable<MovieActor> links = linkRepository.findAll();
        return StreamSupport.stream(links.spliterator(), false)
                .filter(l -> l.getMovieId().equals(movie.getId()))
                .map(MovieActor::getActorId)
                .collect(Collectors.toSet());

    }
}
