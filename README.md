
### 环境
jdk21
mysql 8.0.12

### 配置
创建shirodemo数据库, 在application.properties中配置数据库

手动插入数据
```sql
-- 插入用户信息
INSERT INTO `userinfo` (`id`, `name`, `password`, `salt`, `username`) 
VALUES (1, '管理员', '951cd60dec2104024949d2e0b2', 'some_salt_value', 'admin');

-- 插入权限信息
INSERT INTO `sys_permission` (`id`, `description`, `name`, `url`) 
VALUES 
(1, '查询用户', 'userInfo:view', '/userList'),
(2, '增加用户', 'userInfo:add', '/userAdd'),
(3, '删除用户', 'userInfo:delete', '/userDelete');

-- 插入角色信息
INSERT INTO `sys_role` (`id`, `description`, `name`) 
VALUES (1, '管理员', 'admin');

-- 关联角色和权限
INSERT INTO `sys_role_permission` (`permission_id`, `role_id`) 
VALUES 
(1, 1),
(2, 1),
(3, 1);

-- 关联用户和角色
INSERT INTO `sys_user_role` (`role_id`, `uid`) 
VALUES (1, 1);

```

### 说明
启动会自动生成表