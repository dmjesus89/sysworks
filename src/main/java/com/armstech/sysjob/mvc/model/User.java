package com.armstech.sysjob.mvc.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.armstech.sysjob.mvc.model.security.Authority;
import com.armstech.sysjob.mvc.model.security.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Entity(name = "user")
@Data
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	private Long id;

	@NotNull
	@Size(min = 1, max = 20)
	private String username;

	@NotNull
	private String password;

	@NotNull
	@Size(min = 1, max = 20)
	private String mail;

	@NotNull
	private String cpf;

	
	@Column(name = "dt_cadastro")
	private LocalDate dtCadastro;

//	@NotNull
//	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private List<Phone> phones = new ArrayList<>();

	
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;

	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<UserRole> userRoles = new HashSet<>();

	@JsonIgnore
	@Transient
	public boolean isInativo() {
		return !this.enabled;
	}

	private boolean enabled = true;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<>();
		userRoles.forEach(ur -> authorities.add(new Authority(ur.getRole().getName())));
		return authorities;
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
		return enabled;
	}

}
