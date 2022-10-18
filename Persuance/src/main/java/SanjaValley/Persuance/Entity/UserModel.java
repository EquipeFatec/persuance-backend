package SanjaValley.Persuance.Entity;

import java.util.Set;
import javax.persistence.*;

import java.util.Collection;
import java.util.HashSet;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(
	name = "usuario", 
	uniqueConstraints = { 
		@UniqueConstraint(columnNames = "username")
	}
)
public class UserModel implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@NotBlank
	@Size(max = 60)
	private String username;

	@NotBlank
	@Size(max = 120)
	private String password;

	// @ManyToMany(fetch = FetchType.LAZY)
	// @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	// private Set<RoleModel> roles = new HashSet<>();

	public UserModel() {
	}

	public UserModel(String username, String password) {
		this.username = username;
		this.password = password;
	}

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	// public Set<RoleModel> getRoles() {
	// 	return roles;
	// }

	// public void setRoles(Set<RoleModel> roles) {
	// 	this.roles = roles;
	// }

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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
}
