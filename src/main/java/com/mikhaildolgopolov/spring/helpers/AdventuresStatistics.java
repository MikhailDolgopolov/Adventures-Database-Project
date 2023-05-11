package com.mikhaildolgopolov.spring.helpers;

import com.mikhaildolgopolov.spring.database.entities.City;
import com.mikhaildolgopolov.spring.database.entities.Country;
import com.mikhaildolgopolov.spring.database.entities.Person;
import com.mikhaildolgopolov.spring.database.entities.Souvenir;

import java.util.List;

public class AdventuresStatistics {
    public int numberOfTrips;
    public int numberOfSouvenirs;
    public List<Person> people;
    public List<Country> countries;
    public List<City> cities;
    public List<Souvenir> souvenirs;
}
