package nutf4n.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author cc
 * @date 2023年01月13日 15:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

//    @TableId(type = IdType.ID_WORKER)//生成19位值比如long类型
    @TableId(type = IdType.ID_WORKER_STR)//字符串类型 生成19位
    private Long id;
    private String name;
    private Integer age;
    private String email;

    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;

    @TableLogic
    private Integer deleted;


    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}