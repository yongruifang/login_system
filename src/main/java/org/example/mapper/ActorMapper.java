package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.entity.Actor;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ActorMapper extends BaseMapper<Actor> {

    /**
     * 获取当前时间
     */
    LocalDateTime now();
    /**
     * 根据时间区间 过滤
     */
    List<Actor> filterByInterval(Instant from, Instant to);
}
