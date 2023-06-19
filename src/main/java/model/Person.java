package model;

/**
 * The Person class represents a person.
 * It stores information such as first name and surname.
 */
public abstract class Person {
    private String firstName;
    private String surname;

    /**
     * Constructs a Person object with the given first name and surname.
     *
     * @param firstName
     * @param surname
     */
    public Person(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
    }

    /**
     *
     * @return the first name of the person
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName sets the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return the surname of the person
     */
    public String getSurname() {
        return surname;
    }

    /**
     *
     * @param surname sets the first name
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }
}