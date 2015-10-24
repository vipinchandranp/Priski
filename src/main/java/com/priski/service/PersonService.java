package com.priski.service;


import java.util.List;

import com.priski.model.Person;

public interface PersonService {
    
    public void addPerson(Person person);
    public List<Person> listPeople();
    public void removePerson(String id);
}
