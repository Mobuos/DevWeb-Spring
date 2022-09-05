package devweb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import devweb.dao.IUsuarioDAO;
import devweb.domain.Usuario;

public class UsuariosDetailsService implements UserDetailsService{

    @Autowired
    private IUsuarioDAO dao;

    @Override
    public UserDetails loadUserByUsername(String s)
            throws UsernameNotFoundException {
        Usuario user = dao.findByEmail(s);
         
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
         
        return new UsuarioDetails(user);
    }
}
