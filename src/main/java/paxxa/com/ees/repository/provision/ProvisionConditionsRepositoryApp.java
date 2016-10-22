package paxxa.com.ees.repository.provision;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paxxa.com.ees.entity.provision.ProvisionConditions;
import paxxa.com.ees.entity.provision.ProvisionVariant;
import paxxa.com.ees.repository.user.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ProvisionConditionsRepositoryApp {

    @Autowired
    private UserRepository userRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public List<ProvisionVariant> getProvisionVariants(final Integer userId,
                                                       final String PRODUCT_CODE,
                                                       final String SELLER_CODE) {

        Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(ProvisionConditions.class);
        criteria.add(Subqueries.propertyIn("id", getProvisionIdForUser(userId)));
        return criteria.list();
    }

    private DetachedCriteria getProvisionIdForUser(final Integer userId){
        Session session = entityManager.unwrap(Session.class);

        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ProvisionConditions.class, "provisionConditions");
        detachedCriteria.createAlias("provisionConditions.user", "user");
        detachedCriteria.add(Restrictions.eq("user.id", userId));
        detachedCriteria.setProjection(Property.forName("user.id"));
        return detachedCriteria;
    }

}
