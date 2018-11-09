package com.pracownia.jobs;
import com.pracownia.Constants;
import com.pracownia.Person;
import org.quartz.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

@PersistJobDataAfterExecution
public class SaveToFileJob implements org.quartz.Job{
    HashMap<String, Person> personHashMap;

    public SaveToFileJob(){

    }

    public void execute(JobExecutionContext jobExecutionContext) {
        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();

        personHashMap = (HashMap<String, Person>)dataMap.get("PersonStorage");
        if(personHashMap.isEmpty())
            return;

        ArrayList<Person> persons = new ArrayList<>(personHashMap.values());
        Set<String> cities = persons.stream()
                .map(Person::getCity).collect(Collectors.toSet());

        //System.out.println("Executing save to file job!");
        saveToFile(cities, persons);
    }

    void saveToFile(Set<String> cities, ArrayList<Person> persons){
        FileWriter fw;
        BufferedWriter bw;
        PrintWriter out;

        try{
            fw = new FileWriter(Constants.filePath, false);
            bw = new BufferedWriter(fw);
            out = new PrintWriter(bw);

            for (String currentCity : cities){
                out.println(currentCity);

                ArrayList<Person> personFromOneCity = persons.stream()
                        .filter(person -> person.getCity().equals(currentCity))
                        .collect(Collectors.toCollection(ArrayList::new));

                for(Person person : personFromOneCity){
                    out.println(person.toString());
                }
            }
            out.close();
        } 
        catch (IOException e) {
            System.out.println("File error! Exiting...");
            e.printStackTrace();
            System.exit(-1);
        }

    }
}
