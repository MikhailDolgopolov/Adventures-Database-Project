package com.mikhaildolgopolov.spring.helpers;

import com.mikhaildolgopolov.spring.database.entities.Trip;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class YearSplitTrips {
    public List<YearEntry> list;
    public int Count(){return list.size();}
    public YearSplitTrips(@NotNull List<Trip> orderedList){
        list=new ArrayList<>();
        for(Trip trip : orderedList){
            addTrip(trip);
        }
        list = list.stream().sorted().toList();
    }

    void addTrip(@NotNull Trip trip){
        int year = trip.getYear();
        YearEntry current = list.stream().filter(e->e.checkYear(year))
                .findAny().orElse(null);
        if(current==null) {
            current=new YearEntry(year);
            current.add(trip);
            list.add(current);
        }
        current.add(trip);
    }
    public int tripsStored(){
       return list.stream().map(e->e.getYearList().size())
               .reduce(0, Integer::sum);
    }


}
