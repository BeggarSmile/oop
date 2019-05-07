package model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public abstract class AbstractSpace implements Space, Cloneable {
    private Person person;
    private Vehicle vehicle;
    private LocalDate sinceDate;

    protected AbstractSpace() {
        this(Person.UNKNOWN_PERSON, Vehicle.NO_VEHICLE, LocalDate.now());
    }

    protected AbstractSpace(Person person, LocalDate sinceDate) {
        this(person, Vehicle.NO_VEHICLE, sinceDate);
    }

    protected AbstractSpace(Person person, Vehicle vehicle, LocalDate sinceDate) {
        // Исключения isNull
        //todo а можно и методы set вызвать, чтоб не дублировать проверку и инициализацию полей...
        Objects.requireNonNull(person, "person - null");
        Objects.requireNonNull(vehicle, "vehicle - null");
        Objects.requireNonNull(sinceDate, "sinceDate - null");

        // Исключения IllegalDate
        if (sinceDate.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("IllegalDate");

        // Конструктор
        this.person = person;
        this.vehicle = vehicle;
        this.sinceDate = sinceDate;
    }

    //todo это херня, а не формат.
    public String toString() {
        return String.format(person.toString() + " " + vehicle.toString() + " " + sinceDate);
    } //todo формат не формат =))

    public int hashCode() {
        return (person.hashCode() * vehicle.hashCode() * sinceDate.hashCode());
    }

    public boolean equals(Object object) {
        return (object instanceof AbstractSpace &&
                ((AbstractSpace) object).person.equals(person) &&
                ((AbstractSpace) object).vehicle.equals(vehicle) &&
                ((AbstractSpace) object).sinceDate.equals(sinceDate));
    }

    protected Object clone() throws CloneNotSupportedException{
        AbstractSpace clone = (AbstractSpace)super.clone();
        clone.vehicle = (Vehicle)vehicle.clone();
        clone.person = (Person)person.clone();

        return clone;
    }

    public LocalDate getSinceDate() {
        return sinceDate;
    }

    public void setSinceDate(LocalDate sinceDate) {
        Objects.requireNonNull(sinceDate, "sinceDate - null");
        this.sinceDate = sinceDate;
    }

    public Period getPeriod() {
        return Period.between(sinceDate, LocalDate.now());
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        Objects.requireNonNull(person, "person - null");
        this.person = person;
    }

    public void setVehicle(Vehicle vehicle) {
        Objects.requireNonNull(vehicle, "vehicle - null");
        this.vehicle = vehicle;
    }

    public boolean isEmpty() {
        return (this.vehicle == Vehicle.NO_VEHICLE || this.vehicle.getType() == VehicleTypes.NONE);
    }
}
