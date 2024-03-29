package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.entity.Actor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ActorMapper extends BaseMapper<Actor> {

    /**
     * 获取当前时间
     */
    LocalDateTime now();
}
