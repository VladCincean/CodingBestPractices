package service;

import domain.Actor;
import domain.MovieActor;
import repository.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by vlad on 24.03.2017.
 */
public class ActorService {
    private Repository<Long, Actor> repository;
    private Repository<Long, MovieActor> linkRepository;

    public ActorService(Repository<Long, Actor> repository, Repository<Long, MovieActor> linkRepository) {
        this.repository = repository;
        this.linkRepository = linkRepository;
    }

    /**
     * Provides all actors from the repository.
     *
     * @return the set of all actors.
     */
    public Set<Actor> getAllActors() {
        Iterable<Actor> actors = repository.findAll();
        return StreamSupport.stream(actors.spliterator(), false)
                .collect(Collectors.toSet());
    }

    /**
     * Find the {@code Actor} with the given {@code id}.
     *
     * @param id
     *            must be not null.
     * @return an {@code Optional} encapsulating the {@code Actor} with the given id.
     *          null if there is no one.
     */
    public Actor findOne(Long id) {
        Optional<Actor> optional = repository.findOne(id);
        return optional.orElse(null);
    }

    /**
     * Provides all actor(s) with the given name;
     *
     * @param firstName
     *              must be not null.
     * @param lastName
     *              must be not null.
     * @return a set of actor(s) having the given name.
     */
    public Set<Actor> filterActors(String firstName, String lastName) {
        Iterable<Actor> actors = repository.findAll();
        return StreamSupport.stream(actors.spliterator(), false)
                .filter(a -> a.getFirstName().contains(firstName) &&
                            a.getLastName().contains(lastName))
                .collect(Collectors.toSet());
    }

    /**
     * Deletes an actor.
     *
     * @param id
     *       must be valid and not null.
     * @throws IllegalArgumentException
     *       if the id is not valid
     */
    public void deleteActor(Long id) throws IllegalArgumentException {
        Iterable<MovieActor> links = linkRepository.findAll();
        StreamSupport.stream(links.spliterator(), false)
                .filter(l -> l.getActorId().equals(id))
                .map(MovieActor::getId)
                .forEach(linkRepository::delete);

        repository.delete(id);
    }
}
