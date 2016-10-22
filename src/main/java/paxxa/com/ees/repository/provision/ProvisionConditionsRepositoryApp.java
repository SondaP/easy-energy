package paxxa.com.ees.repository.provision;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paxxa.com.ees.entity.provision.ProvisionConditions;
import paxxa.com.ees.entity.provision.ProvisionVariant;
import paxxa.com.ees.repository.user.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
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
        Criteria criteria = session.createCriteria(ProvisionVariant.class);
        criteria.add(Subqueries.propertyIn("id", getProvisionIdForUser(userId, PRODUCT_CODE, SELLER_CODE)));
        List<ProvisionVariant> results = criteria.list();
        return results;
    }

    private DetachedCriteria getProvisionIdForUser(final Integer userId,
                                                   final String PRODUCT_CODE,
                                                   final String SELLER_CODE){
        Session session = entityManager.unwrap(Session.class);

        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ProvisionConditions.class, "provisionConditions");
        detachedCriteria.createAlias("provisionConditions.user", "user");
        detachedCriteria.createAlias("provisionConditions.provisionVariantList", "provisionVariantList");
        detachedCriteria.add(Restrictions.eq("productCode", PRODUCT_CODE));
        detachedCriteria.add(Restrictions.eq("sellerCode", SELLER_CODE));
        detachedCriteria.add(Restrictions.eq("user.id", userId));
        detachedCriteria.setProjection(Property.forName("provisionVariantList.id"));
        return detachedCriteria;
    }

}
