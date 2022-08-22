package devweb.security;

import java.util.Arrays;
import java.util.Collection;
 
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import devweb.domain.Usuario;
 
@SuppressWarnings("serial")
public class UsuarioDetails implements UserDetails {
 
    private Usuario user;
     
    public UsuarioDetails(Usuario user) {
        this.user = user;
    }
 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
        return Arrays.asList(authority);
    }
 
    @Override
    public String getPassword() {
        System.out.println("======Pegando senha " + user.getSenha());
        return user.getSenha();
    }
 
    @Override
    public String getUsername() {
        System.out.println("======Pegando email " + user.getEmail());
        return user.getEmail();
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