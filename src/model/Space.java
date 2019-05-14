package model;

import java.time.LocalDate;
import java.time.Period;

public interface Space {

    LocalDate getSinceDate();

    void setSinceDate(LocalDate sinceDate);

    Period getPeriod();

    Vehicle getVehicle();

    Person getPerson();

    void setVehicle(Vehicle vehicle);

    void setPerson(Person person);

    boolean isEmpty();

    String toString();

}
