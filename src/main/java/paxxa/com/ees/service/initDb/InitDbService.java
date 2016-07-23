package paxxa.com.ees.service.initDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paxxa.com.ees.entity.role.Role;
import paxxa.com.ees.entity.user.User;
import paxxa.com.ees.repository.role.RoleRepository;
import paxxa.com.ees.repository.user.UserRepository;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Transactional
@Service
public class InitDbService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;


    @PostConstruct
    public void init(){
        Role roleUser = new Role();
        roleUser.setName("ROLE_USER");
        roleRepository.save(roleUser);

        Role roleSuperAdmin = new Role();
        roleSuperAdmin.setName("ROLE_SUPER_ADMIN");
        roleRepository.save(roleSuperAdmin);

        User userSuperAdmin = new User();
        userSuperAdmin.setName("SuperAdmin");
        userSuperAdmin.setPassword("a");
        userSuperAdmin.setRoles(Arrays.asList(roleSuperAdmin, roleUser));
        userRepository.save(userSuperAdmin);



    }
}
