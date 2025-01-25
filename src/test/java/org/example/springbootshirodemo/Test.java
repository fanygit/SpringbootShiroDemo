package org.example.springbootshirodemo;


import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Test {
    public static void main(String[] args) {
        String password = "123456";  // 用户输入的密码
        String salt = "some_salt_value";     // 已知的盐值（通常是随机生成的）

        // 加密密码
        String encryptedPassword = encryptPassword(password, salt);
        System.out.println("加密后的密码: " + encryptedPassword);
    }

    // 使用盐值加密密码
    public static String encryptPassword(String password, String salt) {
        // 加密算法，哈希算法和盐值
        String algorithmName = "md5";  // 使用 SHA-256 算法
        int hashIterations = 2;            // 迭代 2 次加密（可根据需要增加）

        // 使用 SimpleHash 进行加密
        SimpleHash hash = new SimpleHash(
                algorithmName,                   // 算法名
                password,                         // 用户密码
                ByteSource.Util.bytes(salt),      // 盐值
                hashIterations                    // 迭代次数
        );

        return hash.toHex();  // 返回加密后的密码
    }

}
