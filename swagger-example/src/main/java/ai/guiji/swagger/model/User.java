package ai.guiji.swagger.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * author: zhouliliang
 * Date: 2019/11/7 15:33
 * Description:
 */
@Data
@AllArgsConstructor
@ApiModel(value = "测试实体类", description = "测试实体类,可以有很长的一段描述信息")
public class User implements Serializable {
    private static final long serialVersionUID = -7446660454232128458L;

    private Integer id;

    @ApiModelProperty(value = "手机号", required = true)
    private String mobile;

    @ApiModelProperty(value = "姓名", required = true)
    private String name;
}
