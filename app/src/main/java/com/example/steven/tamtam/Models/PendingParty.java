package com.example.steven.tamtam.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by steven on 6/19/16.
 */
public class PendingParty extends Party {
    private List<Person> gamers = new ArrayList<>();

   public int getGamersCount() {
       return gamers.size();
   }

    public void addGamer(Person person) {
        gamers.add(person);
    }
}
