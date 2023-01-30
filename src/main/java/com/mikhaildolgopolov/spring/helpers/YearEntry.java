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
    private HashSet<Trip> yearList;

    public List<Trip> orderedTrips(){
        return yearList.stream().sorted().toList();
    };
    public YearEntry(int year){
        this.year=year;
        yearList=new HashSet<>();
    }
    public void add(Trip trip){
        if(trip.getYear()==year) yearList.add(trip);
    }
    public boolean checkYear(int y){return y==year;}
    public static boolean containsYear(List<YearEntry> list, int year){
        boolean exists = list.stream().map(e -> e.checkYear(year))
                .reduce(Boolean.FALSE, Boolean::logicalOr);
        return exists;
    }


    @Override
    public int compareTo(@NotNull YearEntry o) {
        return -Integer.compare(year, o.year);
    }
}
