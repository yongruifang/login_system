package org.example.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.entity.Actor;

import java.util.List;

@Tag(name="演员", description = "控制类测试Actor实体类")
public interface ActorApi {
    @Operation(summary = "查询所有的演员", description = "先不处理参数", tags={"group: from akilas"})
    List<Actor> index ();
}
