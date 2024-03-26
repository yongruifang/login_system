package org.example.Controller;

import org.example.Controller.Api.ActorApi;
import org.example.Entity.Actor;
import org.example.Mapper.ActorMapper;
import org.example.Service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ActorController implements ActorApi {
    private List<Actor> actors = new ArrayList<>();

    @Autowired
    private ActorService actorService;

    @Autowired
    private ActorMapper actorMapper;

    @GetMapping("/actor")
    public List<Actor> index() {
        actors.clear();
        for(Actor actor: actorMapper.selectList(null)){
            actors.add(actor);
        }
        return actors;
    }
}
