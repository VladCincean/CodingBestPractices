package domain;

import java.util.Objects;

/**
 * Created by vlad on 23.03.2017.
 *
 * Class for representing a movie.
 */
public class Movie extends BaseEntity<Long> {
    private String title;
    private String year;
    private String website;

    public Movie(Long id, String title, String year, String website) {
        super.setId(id);
        this.title = title;
        this.year = year;
        this.website = website;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return year == movie.year &&
                Objects.equals(title, movie.title) &&
                Objects.equals(website, movie.website);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, year, website);
    }

    @Override
    public String toString() {
        return "Movie {\n" +
                "\tid = " + getId() + "\n" +
                "\ttitle = " + title + "\n" +
                "\tyear = " + year + "\n" +
                "\twebsite = " + website + "\n" +
                "}\n";
    }
}
