package org.example.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@TableName("actor")
public class Actor {
    @TableId(value="actor_id", type = IdType.AUTO)
    private int id;
    @TableField("first_name")
    private String first_name;
    @TableField("last_name")
    private String last_name;
}
