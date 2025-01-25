package me.fany.shiro.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SysRole")
public class SysRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 主键生成策略
    private Long id;

    @Column(nullable = false, unique = true, length = 50) // 非空，唯一，最大长度50
    private String name;

    @Column(length = 200) // 最大长度200
    private String description;

    @JsonIgnoreProperties(value = {"roles"}) // 防止递归序列化
    @ManyToMany(fetch = FetchType.LAZY) // 使用延迟加载
    @JoinTable(
            name = "SysRolePermission",
            joinColumns = @JoinColumn(name = "roleId"),
            inverseJoinColumns = @JoinColumn(name = "permissionId")
    )
    private List<SysPermission> permissions;

    @JsonIgnoreProperties(value = {"roles"}) // 防止递归序列化
    @ManyToMany(fetch = FetchType.LAZY) // 使用延迟加载
    @JoinTable(
            name = "SysUserRole",
            joinColumns = @JoinColumn(name = "roleId"),
            inverseJoinColumns = @JoinColumn(name = "userId")
    )
    private List<Userinfo> userinfos;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SysPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SysPermission> permissions) {
        this.permissions = permissions;
    }

    public List<Userinfo> getUserinfos() {
        return userinfos;
    }

    public void setUserinfos(List<Userinfo> userinfos) {
        this.userinfos = userinfos;
    }
}
