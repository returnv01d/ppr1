package com.pracownia;

public class Person {
    String name;
    String surname;
    String PESEL;
    String city;

    public String getCity() { return city; }

    public String getName() {
        return name;
    }

    public String getSurname() { return surname; }

    public String getPESEL() {
        return PESEL;
    }

    public String toString(){
        return getPESEL() + " " + getName() + " " +  getSurname();
    }

    public void ChangeInformation(String newName, String newSurname, String newCity){
        name = newName;
        surname = newSurname;
        city = newCity;
    }

    public Person(String name, String surname, String PESEL, String city){
        this.name = name;
        this.surname = surname;
        this.PESEL = PESEL;
        this.city = city;
    }
}
