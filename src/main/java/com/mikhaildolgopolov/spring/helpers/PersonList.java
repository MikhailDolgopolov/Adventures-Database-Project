package com.mikhaildolgopolov.spring.helpers;

import com.mikhaildolgopolov.spring.database.entities.Person;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PersonList {
    @Setter(AccessLevel.PUBLIC) @Getter(AccessLevel.PUBLIC)
    private List<Person> personList;
    @Setter(AccessLevel.PUBLIC) @Getter(AccessLevel.PUBLIC)
    private List<Integer> intList;
    public PersonList(List<Person> list){
        if(list == null) list=new ArrayList<>();
        personList = list;
        intList = list.stream().map(Person::getPerson_id).collect(Collectors.toList());
    }
}
