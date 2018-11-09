package com.pracownia;

import com.pracownia.jobs.PrintRemainingTimeJob;
import com.pracownia.jobs.SaveToFileJob;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import java.util.*;
import static org.quartz.CronScheduleBuilder.*;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class PersonStorageSystem {

    public HashMap<String, Person> PersonStorage;

    public PersonStorageSystem(){
        PersonStorage = new HashMap<>();
    }
    Scheduler jobScheduler;

    public void Run(){
        System.out.println("Welcome to person storage system!Write 'exit' to end program.");

        scheduleJobs();
        Scanner input = new Scanner(System.in);

        String city;
        String name;
        String surname;
        String PESEL;

        while(true) {
            System.out.print("Enter city: ");
            city = input.nextLine().toUpperCase();
            if (city.equals("exit"))
                break;

            System.out.print("Enter person name: ");
            name = input.nextLine();

            System.out.print("Enter person surname: ");
            surname = input.nextLine();

            System.out.print("Enter person PESEL number: ");
            PESEL = input.nextLine();

            while(!PeselValidator.IsValidPESEL(PESEL)){
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
        }

    }

    void scheduleJobs(){
        try{
            jobScheduler = StdSchedulerFactory.getDefaultScheduler();
            jobScheduler.start();

            JobDetail saveToFileJob = newJob(SaveToFileJob.class)
                    .withIdentity("saveJob", "group1")
                    .build();

            saveToFileJob.getJobDataMap().put("PersonStorage", (Object)PersonStorage);

            JobDetail printRemainingTimeJob = newJob(PrintRemainingTimeJob.class)
                    .withIdentity("printJob", "group1")
                    .build();


            Trigger saveToFileJobTrigger = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow()
                    .withSchedule(cronSchedule("0/30 * * * * ?"))
                    .build();

            Trigger printRemainingTimeJobTrigger = newTrigger()
                    .withIdentity("trigger2", "group1")
                    .startNow()
                    .withSchedule(cronSchedule("0/60 * * * * ?"))
                    .build();

            // Tell quartz to schedule the saveToFileJob using our trigger
            jobScheduler.scheduleJob(saveToFileJob, saveToFileJobTrigger);
            jobScheduler.scheduleJob(printRemainingTimeJob, printRemainingTimeJobTrigger);

        }catch(SchedulerException se){
            se.printStackTrace();
        }
    }



}
