package org.example.models;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class Person {

    private int person_id;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 100, message = "Имя должно быть от 2 до 100 символов длиной")
    private String full_name;

    @Min(value = 1900, message = "Год рождения должен быть больше, чем 1900")
    private int year_of_born;


    public Person() {

    }

    public Person(String full_name, int year_of_born) {
        this.full_name = full_name;
        this.year_of_born = year_of_born;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public int getYear_of_born() {
        return year_of_born;
    }

    public void setYear_of_born(int year_of_born) {
        this.year_of_born = year_of_born;
    }
}