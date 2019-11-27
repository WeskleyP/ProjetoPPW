package com.crm.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "TAB_USUARIO")
@SequenceGenerator(name = "USUARIO_SEQUENCE", sequenceName = "TAB_USUARIO_SEQUENCE",allocationSize = 1)
public class Usuario implements UserDetails{
	private Long idUsuario;
	private String username;
	private String emailUsuario;
	private String password;
	private String contraSenha;
	private Date lastlogin;
	private boolean ativo;
	
	private List<Role> role;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "TAB_USUARIO_ROLE", joinColumns = @JoinColumn(name = "USUARIO_ID"),
	inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	public List<Role> getRole() {
		return role;
	}

	public void setRole(List<Role> role) {
		this.role = role;
	}

	public Usuario() {
	}

	public Usuario(Long idUsuario, String username, String emailUsuario, String password, String contraSenha) {
		this.idUsuario = idUsuario;
		this.username = username;
		this.emailUsuario = emailUsuario;
		this.password = password;
		this.contraSenha = contraSenha;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_SEQUENCE")
	@Column(name = "USUARIO_ID")
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	@Override
	@Size(min = 3, max = 50, message = "No minimo 3 caracteres e no maximo 50 caracteres!")
	@NotBlank(message = "O nome do usuario deve ser informado!")
	@NotNull(message = "O nome do usuario deve ser informado!")
	@Column(name = "USUARIO_NOME", length = 50,nullable = false)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	@Email(message = "Email Invalido")
	@Size(min = 5, max = 100, message = "No minimo 5 caracteres e no maximo 100 caracteres!")
	@NotBlank(message = "O e-mail do usuario deve ser informado!")
	@NotNull(message = "O e-mail do usuario deve ser informado!")
	@Column(name = "USUARIO_EMAIL", length = 100,nullable = false, unique = true)
	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}
	@Override
	@Size(min = 3, max = 200, message = "No minimo 3 caracteres e no maximo 20 caracteres!")
	@NotBlank(message = "A password deve ser informada!")
	@NotNull(message = "A password deve ser informada!")
	@Column(name = "USUARIO_SENHA", length = 200,nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Transient
	public String getContraSenha() {
		return contraSenha;
	}

	public void setContraSenha(String contraSenha) {
		this.contraSenha = contraSenha;
	}
	@Column(name = "USUARIO_ULTIMO_LOGIN", nullable = true, columnDefinition = "DATE")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	public Date getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
	}
	@Column(name = "USUARIO_ATIVO")
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (ativo ? 1231 : 1237);
		result = prime * result + ((contraSenha == null) ? 0 : contraSenha.hashCode());
		result = prime * result + ((emailUsuario == null) ? 0 : emailUsuario.hashCode());
		result = prime * result + ((idUsuario == null) ? 0 : idUsuario.hashCode());
		result = prime * result + ((lastlogin == null) ? 0 : lastlogin.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (ativo != other.ativo)
			return false;
		if (contraSenha == null) {
			if (other.contraSenha != null)
				return false;
		} else if (!contraSenha.equals(other.contraSenha))
			return false;
		if (emailUsuario == null) {
			if (other.emailUsuario != null)
				return false;
		} else if (!emailUsuario.equals(other.emailUsuario))
			return false;
		if (idUsuario == null) {
			if (other.idUsuario != null)
				return false;
		} else if (!idUsuario.equals(other.idUsuario))
			return false;
		if (lastlogin == null) {
			if (other.lastlogin != null)
				return false;
		} else if (!lastlogin.equals(other.lastlogin))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	@Override
	@Transient
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> autoridade = new ArrayList<GrantedAuthority>();
		
		for(Role role : getRole()) {
			autoridade.add(new SimpleGrantedAuthority("ROLE_"+ role.getNomeRole().toUpperCase()));
		}
		return autoridade;
	}

	@Override
	@Transient
	public boolean isAccountNonExpired() {
		return ativo;
	}

	@Override
	@Transient
	public boolean isAccountNonLocked() {
		return ativo;
	}

	@Override
	@Transient
	public boolean isCredentialsNonExpired() {
		return ativo;
	}

	@Override
	@Transient
	public boolean isEnabled() {
		return ativo;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", username=" + username + ", emailUsuario=" + emailUsuario
				+ ", password=" + password + ", contraSenha=" + contraSenha + ", lastlogin=" + lastlogin + ", ativo="
				+ ativo + ", role=" + role + "]";
	}
	

}
