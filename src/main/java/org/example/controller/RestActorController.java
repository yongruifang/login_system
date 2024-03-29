package org.example.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.val;
import org.example.dto.request.Timezone;
import org.example.dto.response.ActorListResponse;
import org.example.dto.response.AddActorResponse;
import org.example.entity.Actor;
import org.example.mapper.ActorMapper;
import org.example.service.ActorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/actor")
@AllArgsConstructor
@Tag(name="演员数据", description = "RestFul风格")
public class RestActorController {
    private ActorService actorService;
    private ActorMapper actorMapper;
    @GetMapping("/")
    public ResponseEntity<ActorListResponse> fetchList (
    ){
        System.out.println("获取演员列表");
        List<Actor> actors = new ArrayList<>();
        for(Actor actor: actorMapper.selectList(null) ){
            actors.add(actor);
        }
        val actorListResponse = new ActorListResponse();
        actorListResponse.setMessage("success");
        actorListResponse.setActorList(actors);
        return ResponseEntity.ok(actorListResponse);
    }
    @PostMapping("/")
    public ResponseEntity<AddActorResponse> addOne (
        @RequestBody Actor actor
    ){
        int id = actorMapper.insert(new Actor( actor.getFirst_name(), actor.getLast_name()));
        val addActorResponse = new AddActorResponse();
        addActorResponse.setMessage("success");
        addActorResponse.setActorId(id);
        return ResponseEntity.ok(addActorResponse);
    }

    @PostMapping("/time")
    public ResponseEntity<String> fetchByTimezone (
            @RequestBody Timezone timezone
    ){

        return ResponseEntity.ok("@TODO: fetchByTimezone");
    }
    @PutMapping("/")
    public ResponseEntity<AddActorResponse> updateOne(
            @RequestBody Actor actor
    ) {
        System.out.println("$$$$$$$$$update actor$$$$$$$$$$$$$");
        System.out.println(actor);
        System.out.println("$$$$$$$$$update actor$$$$$$$$$$$$$");
        int id = actorMapper.updateById(actor);
        val addActorResponse = new AddActorResponse();
        addActorResponse.setMessage("Update success");
        addActorResponse.setActorId(id);
        return ResponseEntity.ok(addActorResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddActorResponse> deleteOne (
            @PathVariable("id") int id
    ){
        actorMapper.deleteById(id);
        val addActorResponse = new AddActorResponse();
        addActorResponse.setMessage("Delete success");
        addActorResponse.setActorId(id);
        return ResponseEntity.ok(addActorResponse);
    }
}
