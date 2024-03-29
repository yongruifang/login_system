package org.example.service.serviceImpl;

import org.example.entity.Actor;
import org.example.mapper.ActorMapper;
import org.example.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class ActorServiceImpl implements ActorService {
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

    @Override
    public List<Actor> filterByTimezone(String from, String to) {
        // from String to Date (Instant)
        String pattern = "uuuu/M/d";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern, Locale.CHINA);
        LocalDateTime fromDateTime = LocalDate.parse(from, dateTimeFormatter).atStartOfDay();
        ZoneId fromZoneId = ZoneId.of("America/Chicago");
        ZonedDateTime fromZonedDateTime = fromDateTime.atZone(fromZoneId);
        Instant fromInstant = fromZonedDateTime.toInstant();
        LocalDateTime toDateTime = LocalDate.parse(to, dateTimeFormatter).atStartOfDay();
        ZoneId toZoneId = ZoneId.of("America/Chicago");
        ZonedDateTime toZonedDateTime = toDateTime.atZone(toZoneId);
        Instant toInstant = toZonedDateTime.toInstant();
        System.out.println("**********************");
        System.out.println(fromInstant);
        System.out.println(toInstant);
        System.out.println("**********************");
        LocalDateTime datetime = actorMapper.now();
        System.out.println(datetime);
        List<Actor> actors =  actorMapper.filterByInterval(fromInstant, toInstant);
        System.out.println(actors.size());
        return actors;
    }
}
