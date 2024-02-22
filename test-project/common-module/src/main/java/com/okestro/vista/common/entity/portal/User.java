package com.okestro.vista.common.entity.portal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@DynamicInsert
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "USER")
@Entity
@ToString
@Builder
public class User extends BaseTime implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "PWD", nullable = false)
    private String pwd;

    @Column(name = "PHONE_NO")
    private String phoneNo;

    @Column(name = "STATUS", nullable = false)
    private String status;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "SECOND_AUTH_YN", nullable = false)
    private boolean secondAuthYn;

    @Column(name = "DEL_YN", nullable = false)
    private boolean delYn;

    @Column(name = "CREATED_BY", nullable = false)
    private Long createdBy;

    @Column(name = "UPDATED_BY")
    private Long updatedBy;

    @Column(name = "ADMIN_YN")
    private boolean adminYn;

    @BatchSize(size = 100)
    @Builder.Default
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserGrpUser> userGroupUsers = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<ChannelUser> channelUserInfos = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<UserNotiReceiveRefusal> userNotiReceiveRefusalEntitys = new ArrayList<>();

    @BatchSize(size = 100)
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<RoleUser> roleUsers = new ArrayList<>();

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    public void updateDelYn() {
        this.delYn = true;
    }

    public void updatePassword(String newPassword) {
        this.pwd = newPassword;
    }

    public void updatePhoneNo(String phoneNo) {
      this.phoneNo = phoneNo;
    }

    public void updatePwd(String pwd) {
		this.pwd = pwd;
	}

    @Transient
    private Collection<SimpleGrantedAuthority> authorities;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(Collection<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return this.pwd;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}





















