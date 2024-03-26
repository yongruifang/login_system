package org.example;

import org.example.entity.Actor;
import org.example.mapper.ActorMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DemoTest {
    @Autowired
    private ActorMapper actorMapper;
    @Test
    public void queryActor(){
        System.out.println("==== selectAll method test ===");
        for(Actor actor: actorMapper.selectList(null)){
            System.out.println(actor.toString());
        }
    }
}
