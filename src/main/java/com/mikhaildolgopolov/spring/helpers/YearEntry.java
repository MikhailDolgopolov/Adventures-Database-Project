package com.mikhaildolgopolov.spring.helpers;

import com.mikhaildolgopolov.spring.database.entities.Trip;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class YearEntry implements Comparable<YearEntry>{
    @Getter (AccessLevel.PUBLIC) @Setter
    private int year;
    @Getter(AccessLevel.PUBLIC) @Setter
    private List<Trip> yearList;

    public YearEntry(int year){
        this.year=year;
        yearList=new ArrayList<>();
    }
    public void add(Trip trip){
        if(trip.getYear()==year){
            if(!yearList.contains(trip))
                yearList.add(trip);
        }
        Collections.sort(yearList);
    }
    public boolean checkYear(int y){return y==year;}

    @Override
    public int compareTo(@NotNull YearEntry o) {
        return -Integer.compare(year, o.year);
    }
}
