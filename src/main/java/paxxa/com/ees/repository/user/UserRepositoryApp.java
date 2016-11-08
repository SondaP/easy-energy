package paxxa.com.ees.repository.user;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paxxa.com.ees.entity.client.Client;
import paxxa.com.ees.entity.role.Role;
import paxxa.com.ees.entity.user.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class UserRepositoryApp {

    @Autowired
    private UserRepository userRepository;
    @PersistenceContext
    private EntityManager entityManager;


    public List<User> findAllUsersForUser(String userName) {
        Session session = entityManager.unwrap(Session.class);

        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
        detachedCriteria.createAlias("userList", "userList");
        detachedCriteria.add(Restrictions.eq("name", userName));
        detachedCriteria.setProjection(Property.forName("userList.id"));

        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Subqueries.propertyIn("id", detachedCriteria));

        List<User> list = criteria.list();
        return list;
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
        for (Client client : clients) {
            client.setCurrentAdvisor(null);
        }
    }

    public boolean hasUserExpectedRole(String userName, String expectedRole) {
        User user = userRepository.findByName(userName);
        Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Role.class);
        criteria.createAlias("users", "users");
        criteria.add(Restrictions.eq("users.id", user.getId()));
        criteria.add(Restrictions.eq("name", expectedRole));
        List<Role> role = criteria.list();
        if (role.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public String getUserNameById(final Integer userId) {
        Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("id", userId));
        criteria.setProjection(Property.forName("name"));
        return (String) criteria.uniqueResult();
    }


}
