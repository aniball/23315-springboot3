 package ar.com.codoacodo.domain;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*representar la tabla USUARIOS*/
@Entity
@Table(name = "usuario")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {
	//@JsonIgnore //anotacion de presentacion
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="username",length = 50,unique = true)
	private String username;
	
	@Column(name="password",length = 50)
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "usuario_roles",
			joinColumns = @JoinColumn(name="usuario_id"),
			inverseJoinColumns = @JoinColumn(name="role_id")
	)
	private Set<Role> roles;
//	select * from user u
//	  inner join usuario_roles ur on ur.usuario_id = u.id
//	  inner join role r on r.id= ur.role_id
}
