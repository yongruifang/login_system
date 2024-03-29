package org.example.service;

import org.example.entity.Actor;
import java.util.List;

public interface ActorService{
    public List<Actor> getAllActors();

    public List<Actor> filterByTimezone(String from, String to);
}
