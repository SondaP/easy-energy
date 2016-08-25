package paxxa.com.ees.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paxxa.com.domainConstans.DomainConstans;
import paxxa.com.ees.entity.personalData.PersonalData;
import paxxa.com.ees.entity.role.Role;
import paxxa.com.ees.entity.user.User;
import paxxa.com.ees.repository.client.ClientRepository;
import paxxa.com.ees.repository.role.RoleRepository;
import paxxa.com.ees.repository.user.UserRepository;
import paxxa.com.ees.repository.user.UserRepositoryApp;

import java.util.Arrays;
import java.util.List;

@Transactional
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRepositoryApp userRepositoryApp;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ClientRepository clientRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findAllUsersForUser(String userName){
        return userRepositoryApp.findAllUsersForUser(userName);
    }

    public User findById(final int userId) {
        return userRepository.findOne(userId);
    }

    public void saveUserWithRoleAdmin(User user) {
        user.setEnabled(true);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));

        Role role = roleRepository.findByName(DomainConstans.ROLE.ROLE_ADMIN);
        user.setRoles(Arrays.asList(role));

        userRepository.save(user);
    }

    public void saveUserWithRoleTrader(User user, String adminName){
        User adminEntity = userRepository.findByName(adminName);
        user.setEnabled(true);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        Role role = roleRepository.findByName(DomainConstans.ROLE.ROLE_TRADER);
        user.setRoles(Arrays.asList(role));
        userRepository.saveAndFlush(user);

        adminEntity.getUserList().add(user);
        userRepository.save(adminEntity);
    }

    public void removeUser(int id) {
        userRepositoryApp.removeUser(id);
    }


    public PersonalData findPersonalDataByUserName(String userName){
        User user = userRepository.findByName(userName);
        return user.getPersonalData();
    }

    public User findUserByUserName(String userName){
        User user = userRepository.findByName(userName);
        return user;
    }

    public boolean hasUserExpectedRole(String userName, String expectedRole){
        return userRepositoryApp.hasUserExpectedRole(userName, expectedRole);
    }

    public Integer getUserIdByUserName(String userName){
        return userRepository.findByName(userName).getId();
    }



}
