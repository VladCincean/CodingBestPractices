package repository;

import domain.BaseEntity;
import domain.validator.Validator;
import domain.validator.ValidatorException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * Created by vlad on 24.03.2017.
 *
 * Abstract class for generic CRUD operations on a repository for a specific type for CSV persistence.
 */
public abstract class FileRepository<ID, T extends BaseEntity<ID>> extends InMemoryRepository<ID, T> {
    protected String fileName;

    public FileRepository(Validator<T> validator, String fileName) {
        super(validator);
        this.fileName = fileName;
    }

    /**
     * Loads the data from the CSV file into memory.
     */
    protected abstract void loadData();

    /**
     * Appends the given entity to the CSV file.
     *
     * @param entity
     *             must not be null.
     */
    protected abstract void saveToFile(T entity);

    private void refreshFile() {
        Path path = Paths.get(fileName);
        try {
            Files.write(path, "".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.findAll().forEach(this::saveToFile);
    }

    /**
     * Saves the given entity in memory.
     *
     * @param entity
     *            must not be null.
     * @return an {@code Optional} - null if the entity was saved otherwise (e.g. id already exists) returns the entity.
     * @throws IllegalArgumentException
     *             if the given entity is null.
     * @throws ValidatorException
     *             if the entity is not valid.
     */
    Optional<T> saveInMemory(T entity) throws ValidatorException {
        return super.save(entity);
    }

    /**
     * Saves the given entity in file.
     *
     * @param entity
     *            must not be null.
     * @return an {@code Optional} - null if the entity was saved otherwise (e.g. id already exists) returns the entity.
     * @throws IllegalArgumentException
     *             if the given entity is null.
     * @throws ValidatorException
     *             if the entity is not valid.
     */
    @Override
    public Optional<T> save(T entity) throws ValidatorException {
        Optional<T> optional = super.save(entity);

        optional.orElseGet(() -> {
            this.saveToFile(entity);
            return null;
        });
        return optional.isPresent() ? optional : Optional.empty();
    }

    /**
     * Removes the entity with the given id from the CSV file.
     *
     * @param id
     *            must not be null.
     * @return an {@code Optional} - null if there is no entity with the given id, otherwise the removed entity.
     * @throws IllegalArgumentException
     *             if the given id is null.
     */
    @Override
    public Optional<T> delete(ID id) {
        Optional<T> optional = super.delete(id);
        optional.ifPresent(o -> this.refreshFile());
        return optional.isPresent() ? optional : Optional.empty();
    }

    /**
     * Updates the given entity in the CSV file.
     *
     * @param entity
     *            must not be null.
     * @return an {@code Optional} - null if the entity was updated otherwise (e.g. id does not exist) returns the
     *         entity.
     * @throws IllegalArgumentException
     *             if the given entity is null.
     * @throws ValidatorException
     *             if the entity is not valid.
     */
    @Override
    public Optional<T> update(T entity) throws ValidatorException {
        Optional<T> optional = super.update(entity);
        optional.ifPresent(o -> this.refreshFile());
        return optional.isPresent() ? optional : Optional.empty();
    }
}
