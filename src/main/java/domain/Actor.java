package domain;

import java.util.Objects;

/**
 * Created by vlad on 23.03.2017.
 *
 * Class for representing an actor.
 */
public class Actor extends BaseEntity<Long> {
    private String firstName;
    private String lastName;
    private String birthDate;
    private String nationality;
    private String city;
    private String height;
    private String weight;
    private String email;
    private String phoneNumber;

    public Actor(
            Long id,
            String firstName,
            String lastName,
            String birthDate,
            String nationality,
            String city,
            String height,
            String weight,
            String email,
            String phoneNumber) {
        super.setId(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.city = city;
        this.height = height;
        this.weight = weight;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return Objects.equals(firstName, actor.firstName) &&
                Objects.equals(lastName, actor.lastName) &&
                Objects.equals(birthDate, actor.birthDate) &&
                Objects.equals(nationality, actor.nationality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, birthDate, nationality);
    }

    @Override
    public String toString() {
        return "Actor {\n" +
                "\tid = " + getId() + "\n" +
                "\tfirst name = " + firstName + "\n" +
                "\tlast name = " + lastName + "\n" +
                "\tbirth-date = " + birthDate + "\n" +
                "\tnationality = " + nationality + "\n" +
                "\tcity = " + city + "\n" +
                "\theight = " + height + "\n" +
                "\tweight = " + weight + "\n" +
                "\temail = " + email + "\n" +
                "\tphone number = " + phoneNumber + "\n" +
                "}\n";
    }
}
