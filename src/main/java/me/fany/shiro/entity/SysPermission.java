package me.fany.shiro.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SysPermission")
public class SysPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 主键生成策略
    private Long id;

    @Column(nullable = false, unique = true, length = 50) // 非空，唯一，长度50
    private String name;

    @Column(length = 200) // 最大长度200
    private String description;

    @Column(nullable = false, length = 200) // 非空，最大长度200
    private String url;

    @JsonIgnoreProperties(value = {"permissions"}) // 防止序列化递归
    @ManyToMany(fetch = FetchType.EAGER) // 使用 EAGER 或 LAZY 视情况而定
    @JoinTable(
            name = "SysRolePermission",
            joinColumns = @JoinColumn(name = "permissionId"),
            inverseJoinColumns = @JoinColumn(name = "roleId")
    )
    private List<SysRole> roles;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }
}
