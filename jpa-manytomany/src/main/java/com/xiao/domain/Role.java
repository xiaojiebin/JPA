package com.xiao.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sys_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "role_name")
    private String roleName;

    @ManyToMany(targetEntity = Role.class)
    @JoinTable(name = "sys_user_role",
    joinColumns = {@JoinColumn(name = "sys_role_id",referencedColumnName = "role_id")},
    inverseJoinColumns = {@JoinColumn(name = "sys_user_id",referencedColumnName = "user_id")})
    private Set<Role> roles=new HashSet<Role>();
    @Override
    public String toString() {
        return "User{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                '}';
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
