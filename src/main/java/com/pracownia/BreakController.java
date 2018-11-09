package com.pracownia;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public class BreakController {
    Break WMIBreaks[] = {
            new Break(LocalTime.parse("09:45"), 15),
            new Break(LocalTime.parse("11:30"), 15),
            new Break(LocalTime.parse("13:15"), 30),
            new Break(LocalTime.parse("15:15"), 15),
            new Break(LocalTime.parse("17:00"), 15),
    };

    Break emptyBreak = new Break(LocalTime.parse("00:00"),1);
    public BreakController(){}

    public boolean isBreakTime(LocalTime time){
        for(Break currentBreak : WMIBreaks){
            if(currentBreak.isDuringBreak(time))
                return true;
        }
        return false;
    }

    public Break getCurrentBreak(LocalTime time){
        for(Break currentBreak : WMIBreaks) {
            if (currentBreak.isDuringBreak(time)) {
                return currentBreak;
            }
        }
            return emptyBreak;
    }

    public Break getNextBreak(LocalTime time){
        LocalTime firstBreakTime = WMIBreaks[0].startTime;
        LocalTime lastBreakTime = WMIBreaks[WMIBreaks.length - 1].startTime;
        if(time.isBefore(firstBreakTime) || time.isAfter(lastBreakTime)){
            return WMIBreaks[0];
        }

        for(Break nextBreak : WMIBreaks){
            if(time.isBefore(nextBreak.startTime)){
                return nextBreak;
            }
        }

        return emptyBreak;
    }

    public LocalTime getTimeToNextBreak(LocalTime time){
        Break nextBreak = getNextBreak(time);
        return nextBreak.startTime.minusHours(time.getHour())
                .minusMinutes(time.getMinute());
    }
}
