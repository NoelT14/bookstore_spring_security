package NoelSpring.Bookstore.Service;

import NoelSpring.Bookstore.Entity.MyUser;
import NoelSpring.Bookstore.Repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private MyUserRepository myUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<MyUser> user = myUserRepository.findByEmail(email);

        if(user.isPresent()){
           var userObj =  user.get();
           return User.builder().username(userObj.getEmail()).password(userObj.getPassword())
                   .roles(userObj.getRole()).build();
        }
        else{
            throw new UsernameNotFoundException(email);
        }
    }
}
