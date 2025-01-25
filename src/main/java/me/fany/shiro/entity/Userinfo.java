package me.fany.shiro.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Userinfo") // 指定表名
public class Userinfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 指定主键生成策略
    private Long id;

    @Column(unique = true, nullable = false) // 唯一约束并且不能为空
    private String username;

    private String name;



    @Column(nullable = false) // 密码不能为空
    private String password;

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    private String salt; // 加密的盐

    @JsonIgnoreProperties(value = {"userInfos"}) // 防止递归序列化
    @ManyToMany(fetch = FetchType.LAZY) // 使用 LAZY 加载
    @JoinTable(
            name = "SysUserRole",
            joinColumns = @JoinColumn(name = "uid"), // 中间表中的外键列
            inverseJoinColumns = @JoinColumn(name = "roleId") // 中间表指向 SysRole 的外键列
    )
    private List<SysRole> roles;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }
}
