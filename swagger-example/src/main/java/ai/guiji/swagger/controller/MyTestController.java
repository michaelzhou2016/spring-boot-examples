package ai.guiji.swagger.controller;

import ai.guiji.swagger.model.User;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * author: zhouliliang
 * Date: 2019/11/7 15:35
 * Description: 测试类
 */
@Slf4j
@RestController
@RequestMapping("/test")
@Api(value = "测试", tags = "测试")
@ApiSort(1)
public class MyTestController {

    @ApiResponses({
            @ApiResponse(code = 200, message = "新增成功"),
            @ApiResponse(code = 404, message = "参数有误")})
    @ApiOperation(value = "新增", notes = "新增数据", response = Boolean.class)
    @PostMapping("/user")
    public Boolean add(@RequestHeader String code, @RequestBody User user) {
        log.info("code:{}, user:{}", code, user.toString());
        return Boolean.TRUE;
    }

    @ApiOperation(value = "列表", notes = "查看列表数据", responseContainer = "List", response = User.class)
    @GetMapping("/users")
    public List<User> list(@RequestHeader String code) {
        log.info("code:{}", code);
        List<User> list = Arrays.asList(new User(1, "1234556", "charlie"), new User(2, "789123456", "liliang"));

        return list;
    }
}
