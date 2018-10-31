package com.pracownia;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class PersonStorageSystem {

    public HashMap<String, Person> PersonStorage;

    public PersonStorageSystem(){
        PersonStorage = new HashMap<>();
    }

    public void Run(){
        String city;
        String name;
        String surname;
        String PESEL;

        System.out.println("Welcome to person storage system!Write 'exit' to end program.");

        File outputFile = new File("person_list.txt");
        try {
            outputFile.createNewFile();
        } catch (IOException e) {
            System.out.print("Failed creating file!Exiting...");
            System.exit(-1);
        }

        Scanner input = new Scanner(System.in);
        while(true) {
            System.out.print("Enter city: ");
            city = input.nextLine();
            if (city.equals("exit"))
                break;

            System.out.print("Enter person name: ");
            name = input.nextLine();

            System.out.print("Enter person surname: ");
            surname = input.nextLine();

            System.out.print("Enter person PESEL number: ");
            PESEL = input.nextLine();

            while(!IsValidPESEL(PESEL)){
                System.out.print("Enered PESEL is not valid.Please enter correct one: ");
                PESEL = input.nextLine();
            }

            Person newPerson = new Person(name, surname, PESEL, city);

            if (PersonStorage.get(PESEL) == null){
                PersonStorage.put(PESEL, newPerson);
            }
            else{
                Person personWithTheSamePESEL = PersonStorage.get(PESEL);
                personWithTheSamePESEL.ChangeInformation(name, surname, city);
            }
            for (Map.Entry<String,Person> entry : PersonStorage.entrySet()) {
                String key = entry.getKey();
                Person person = entry.getValue();
                System.out.println(key + ": " + person.name + " " + person.surname + " " + person.city);
            }

        }

    }

    public static boolean IsValidPESEL(String PESEL){
        final int PESEL_NUMBER_LENGTH = 11;

        if(PESEL == null)
            return false;

        if(PESEL.length() != PESEL_NUMBER_LENGTH)
            return false;

        return true;
    }


}
