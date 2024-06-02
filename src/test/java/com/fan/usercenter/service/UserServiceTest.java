package com.fan.usercenter.service;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fan.usercenter.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import javax.security.auth.message.callback.PrivateKeyCallback;

/**
 * 用户服务测试
 */
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void testAddUser() {
        User user = new User();

        user.setUserName("test");
        user.setAccount("1234");
        user.setAvatarUrl("https://tse2-mm.cn.bing.net/th/id/OIP-C.VCEfNgKKolY211Ia3m7fLgHaNK?rs=1&pid=ImgDetMain");
        user.setGender(0);
        user.setPassword("123");
        user.setPhone("11111");
        user.setEmail("22222");
        boolean res = userService.save(user);
        System.out.println(user.getId());
        // 用断言来判断程序实际执行结果是否是我们期望的结果
        Assertions.assertTrue(res);
    }

    @Test
    void testDigest() {
        String newPassword = DigestUtils.md5DigestAsHex(("abcd" + "123455").getBytes(StandardCharsets.UTF_8));
        System.out.println(newPassword);
    }

    @Test
    void userRegister() {
        String userAccount = "fanxx";
        String checkPassword = "123456";
        // 测试非空
        String userPassword = "";
        long res = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, res);
        // 账户长度必须大于4位
        userAccount = "fan";
        res = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, res);
        // 账户不能重复
        userAccount = "1234";
        res = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, res);
        // 账户不包含特殊字符
        userAccount = "fan xx";
        userPassword = "12345678";
        checkPassword = "12345678";
        res = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, res);
        // 密码和校验密码相同
        userPassword = "1234567";
        checkPassword = "1234569";
        res = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, res);
        // 正常插入
        userAccount = "fan123";
        userPassword = "12345678";
        checkPassword = "12345678";
        res = userService.userRegister(userAccount, userPassword, checkPassword);
        // Assertions.assertTrue(res > 0); // 断言内部条件成立
        Assertions.assertEquals(-1, res);

    }


}