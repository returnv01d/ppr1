package com.pracownia.jobs;
import com.pracownia.Break;
import com.pracownia.BreakController;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalTime;

public class PrintRemainingTimeJob implements org.quartz.Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        BreakController breakController = new BreakController();

        LocalTime remainingTime;
        LocalTime currentTime = LocalTime.now();

        if(breakController.isBreakTime(currentTime)){
            Break currentBreak = breakController.getCurrentBreak(currentTime);
            remainingTime = currentBreak.remainingTime(currentTime);

            String remainingMinutes = Integer.toString(remainingTime.getMinute());
            String remainingHours = Integer.toString(remainingTime.getHour());

            System.out.println("Pozostało " + remainingHours + " godzin i "
                    + remainingMinutes + " minut do końca przerwy" );
        }
        else{
            remainingTime = breakController.getTimeToNextBreak(currentTime);

            String remainingMinutes = Integer.toString(remainingTime.getMinute());
            String remainingHours = Integer.toString(remainingTime.getHour());

            System.out.println("Pozostało " + remainingHours + " godzin i "
                    + remainingMinutes + " minut do końca zajęć" );

        }
    }
}
