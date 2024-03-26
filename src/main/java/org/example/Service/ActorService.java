package org.example.Service;

import org.example.Entity.Actor;
import org.example.Mapper.ActorMapper;
import org.example.Service.ServiceImpl.ActorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActorService implements ActorServiceImpl {
    // storing the data temporary
    List<Actor> actors = new ArrayList<>();
    @Autowired
    ActorMapper actorMapper;
    @Override
    public List<Actor> getAllActors() {
        actors.clear();
        for(Actor actor: actorMapper.selectList(null)){
            actors.add(actor);
            System.out.println(actor.toString());
        }
        System.out.println("+++++++++++++++++++");
        System.out.println(actors);
        return actors;
    }
}
