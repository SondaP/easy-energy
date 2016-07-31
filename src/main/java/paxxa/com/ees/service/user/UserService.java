package paxxa.com.ees.service.user;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paxxa.com.ees.entity.client.Client;
import paxxa.com.ees.entity.company.Company;
import paxxa.com.ees.entity.personalData.PersonalData;
import paxxa.com.ees.entity.role.Role;
import paxxa.com.ees.entity.user.User;
import paxxa.com.ees.repository.client.ClientRepository;
import paxxa.com.ees.repository.role.RoleRepository;
import paxxa.com.ees.repository.user.UserRepository;
import paxxa.com.utils.DomainConstans;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;

@Transactional
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ClientRepository clientRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(final int userId) {
        return userRepository.findOne(userId);
    }

    public void saveAdmin(User user) {
        user.setEnabled(true);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));

        Role role = roleRepository.findByName(DomainConstans.ROLE.ROLE_ADMIN);
        user.setRoles(Arrays.asList(role));

        userRepository.save(user);
    }

    public void removeUser(int id) {
        List<Client> allCompaniesByUserId = getAllCompaniesByUserId(id);
        unLockClient(allCompaniesByUserId);
        userRepository.delete(id);
    }

    private List<Client> getAllCompaniesByUserId(final int userId) {
        Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Client.class);
        criteria.createAlias("currentAdvisor", "currentAdvisor");
        criteria.add(Restrictions.eq("currentAdvisor.id", userId));
        List<Client> results = criteria.list();
        return results;
    }

    private void unLockClient(List<Client> clients) {
        for(Client client: clients){
            client.setCurrentAdvisor(null);
        }
    }


    public void updatePassword(User userEntity, String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userEntity.setPassword(encoder.encode(password));
        userRepository.saveAndFlush(userEntity);
    }

    public PersonalData findPersonalDataByUserName(String userName){
        User user = userRepository.findByName(userName);
        return user.getPersonalData();
    }

    public User findUserByUserName(String userName){
        User user = userRepository.findByName(userName);
        return user;
    }
}
