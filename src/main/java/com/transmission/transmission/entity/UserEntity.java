package com.transmission.transmission.entity;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 用户表(user)
 * 
 * @author bianj
 * @version 1.0.0 2020-04-03
 */
@TableName("user")
public class UserEntity implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 867264917398995689L;

    /** 用户id */
    private Integer id;

    /** 账号 */
    private String account;

    /** 密码 */
    private String password;
    /** 角色id（0.患者，1.医生，2.护士） */
    
    private Integer roleId;

    /**
     * 获取用户id
     * 
     * @return 用户id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * 设置用户id
     * 
     * @param id
     *          用户id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取账号
     * 
     * @return 账号
     */
    public String getAccount() {
        return this.account;
    }

    /**
     * 设置账号
     * 
     * @param account
     *          账号
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取密码
     * 
     * @return 密码
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * 设置密码
     * 
     * @param password
     *          密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取角色id（0.患者，1.医生，2.护士）
     * 
     * @return 角色id（0.患者
     */
    public Integer getRoleId() {
        return this.roleId;
    }

    /**
     * 设置角色id（0.患者，1.医生，2.护士）
     * 
     * @param roleId
     *          角色id（0.患者
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}