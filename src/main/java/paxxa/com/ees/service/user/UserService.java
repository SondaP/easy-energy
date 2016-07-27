package paxxa.com.ees.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paxxa.com.ees.entity.role.Role;
import paxxa.com.ees.entity.user.User;
import paxxa.com.ees.repository.role.RoleRepository;
import paxxa.com.ees.repository.user.UserRepository;
import paxxa.com.utils.DomainConstans;

import java.util.Arrays;
import java.util.List;
@Transactional
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(final int userId) {
        return userRepository.findOne(userId);
    }

    public void saveAdmin(User user){
        user.setEnabled(true);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));

        Role role = roleRepository.findByName(DomainConstans.ROLE.ROLE_ADMIN);
        user.setRoles(Arrays.asList(role));

        userRepository.save(user);
    }


}
