package SanjaValley.Persuance.Service;

import SanjaValley.Persuance.Entity.UserModel;
import SanjaValley.Persuance.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String administrador) throws UsernameNotFoundException {
        // UserModel user = userRepository.findByAdministrador(administrador)
        UserModel user = userRepository.findByUsername(administrador)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + administrador));

        return user;
    }
}