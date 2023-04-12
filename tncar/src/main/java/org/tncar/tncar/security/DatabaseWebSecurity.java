package org.tncar.tncar.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class DatabaseWebSecurity {
	@Bean
	public UserDetailsManager usersCustom(DataSource dataSource) {
	JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
	users.setUsersByUsernameQuery("select username,password,estatus from Usuarios u where username=?");
	users.setAuthoritiesByUsernameQuery("select u.username,p.perfil from UsuarioPerfil up " + 
	"inner join Usuarios u on u.id = up.idUsuario " + 
	"inner join Perfiles p on p.id = up.idPerfil " + 
	"where u.username=?");
	return users;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
				// Los recursos estáticos no requieren autenticación
				.requestMatchers("/bootstrap/**", "/images/**", "/tinymce/**", "/logos/**", "/error", "/contacto", "/productos/detalle/**", "/tiposserv/detalle/**", "/search").permitAll()
				//.requestMatchers("/bootstrap/**", "/images/**", "/tinymce/**").permitAll()
				// Las vistas públicas no requieren autenticación
				.requestMatchers("/", "/signup", "/guardar", "/acerca").permitAll()
				//Asignar permisos a URLS por roles 
				.requestMatchers("/usuarios/**").hasAnyAuthority("Administrador")
				.requestMatchers("/categorias/**").hasAnyAuthority("Administrador","Empleado")
				.requestMatchers("/productos/**").hasAnyAuthority("Administrador","Empleado")
				.requestMatchers("/tiposserv/**").hasAnyAuthority("Administrador","Empleado")
				.requestMatchers("/servicios/**").hasAnyAuthority("Administrador","Empleado")
				.requestMatchers("/automoviles/**").hasAnyAuthority("Administrador","Empleado")
				.requestMatchers("/perfiles/**").hasAnyAuthority("Administrador")
				.requestMatchers("/catalogoproductos").hasAnyAuthority("Usuario")
				.requestMatchers("/catalogoservicios").hasAnyAuthority("Usuario")
				.requestMatchers("/cart").hasAnyAuthority("Usuario")
				.requestMatchers("/contrataciones/**").hasAnyAuthority("Administrador","Empleado","Usuario")
				// Todas las demás URLs de la Aplicación requieren autenticación
				.anyRequest().authenticated()
				// El formulario de Login no requiere autenticacion
				.and().formLogin().loginPage("/login").permitAll();
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
