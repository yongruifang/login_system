package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("actor")
public class Actor {
    @TableId(value="actor_id", type = IdType.AUTO)
    private int id;
    @TableField("first_name")
    private String firstName;
    @TableField("last_name")
    private String lastName;
    @UpdateTimestamp
    @TableField("last_update")
    private Instant lastUpdate;
}
