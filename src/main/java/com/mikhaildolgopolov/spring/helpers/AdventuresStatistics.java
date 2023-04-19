package com.mikhaildolgopolov.spring.helpers;

import com.mikhaildolgopolov.spring.database.entities.City;
import com.mikhaildolgopolov.spring.database.entities.Country;
import com.mikhaildolgopolov.spring.database.entities.Person;

import java.util.List;

public class AdventuresStatistics {
    public int numberOfTrips;
    public List<Person> people;
    public List<Country> countries;
    public List<City> cities;
}
