package godel.by.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;

public class Director implements Serializable, Identifiable {

    public static final String TABLE = "director";
    public static final String ID = "id";
    public static final String FIRST_NAME = "first_name";
    public static final String SECOND_NAME = "second_name";
    public static final String BIRTH_DATE = "birth_date";

    private Long id;
    private String firstName;
    private String secondName;
    private LocalDate birthDate;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Director director = (Director) o;
        return Objects.equals(id, director.id) &&
                Objects.equals(firstName, director.firstName) &&
                Objects.equals(secondName, director.secondName) &&
                Objects.equals(birthDate, director.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, secondName, birthDate);
    }

    @Override
    public String toString() {
        return "Director{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
