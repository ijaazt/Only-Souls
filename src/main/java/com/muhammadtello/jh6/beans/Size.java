package com.muhammadtello.jh6.beans;

import java.io.Serializable;
import java.util.Objects;

public class Size implements Serializable {
    private int inches;
    private int feet;
    private int totalInches;

    Size(int feet, int inches) {
        this.inches = inches;
        this.feet = feet;
        totalInches = calculateTotalInches(inches, feet);
    }
    private int calculateTotalInches(int inches, int feet) {
        return inches + feet * 12;
    }

    public Size(int totalInches) {
        this.totalInches = totalInches;
        inches = totalInches % 12;
        feet = totalInches / 12;
    }

    public int getInches() {
        return inches;
    }

    public int getFeet() {
        return feet;
    }

    @Override
    public String toString() {
        return "Size{" + "inches=" + inches +
                ", feet=" + feet +
                ", totalInches=" + totalInches +
                '}';
    }

    public int getTotalInches() {
        return totalInches;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Size size = (Size) o;
        return inches == size.inches &&
                feet == size.feet &&
                totalInches == size.totalInches;
    }

    @Override
    public int hashCode() {
        return Objects.hash(inches, feet, totalInches);
    }
}
