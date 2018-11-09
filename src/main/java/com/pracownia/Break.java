package com.pracownia;

import java.time.LocalTime;

public class Break {
    public LocalTime startTime;
    int durationInMinutes;

    public Break(LocalTime startTime, int durationInMinutes){
        this.startTime = startTime;
        this.durationInMinutes = durationInMinutes;
    }

    public LocalTime finishTime(){
        return startTime.plusMinutes(durationInMinutes);
    }

    public boolean isDuringBreak(LocalTime time){
        return time.isAfter(startTime) && time.isBefore(finishTime());
    }
    public LocalTime remainingTime(LocalTime time){
        return finishTime().minusHours(time.getHour()).minusMinutes(time.getMinute());
    }
}
