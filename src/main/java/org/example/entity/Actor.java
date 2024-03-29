package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@TableName("actor")
public class Actor {
    @TableId(value="actor_id", type = IdType.AUTO)
    private int id;
    @TableField("first_name")
    @NonNull
    private String first_name;
    @TableField("last_name")
    @NonNull
    private String last_name;

    @UpdateTimestamp
    @TableField("last_update")
    private Instant last_update;
}
