package domain;

/**
 * Created by vlad on 23.03.2017.
 *
 * A generic class for representing a 'real-world' entity that can be identified by an object of type {@code ID}.
 */
public abstract class BaseEntity<ID> {
    private ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                '}';
    }
}
