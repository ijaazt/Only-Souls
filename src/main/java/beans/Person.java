package beans;

import java.util.Objects;

public class Person {
    private final int id;
    private String firstName;
    private String lastName;
    private String eyeColor;
    private String hairColor;
    private double height;
    private double weight;

    public Person(String firstName, String lastName, String eyeColor, String hairColor, double height, double weight, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.height = height;
        this.weight = weight;
        this.id = id;
    }

    public int getId() {
        return id;
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

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(eyeColor, person.eyeColor) &&
                Objects.equals(hairColor, person.hairColor) &&
                Objects.equals(height, person.height) &&
                Objects.equals(weight, person.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, eyeColor, hairColor, height, weight);
    }
}
