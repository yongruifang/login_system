package org.example.controller;

import org.example.controller.Api.ActorApi;
import org.example.entity.Actor;
import org.example.mapper.ActorMapper;
import org.example.service.ActorService;
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
