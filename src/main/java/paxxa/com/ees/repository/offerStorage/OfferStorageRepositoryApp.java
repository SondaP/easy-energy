package paxxa.com.ees.repository.offerStorage;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paxxa.com.ees.entity.offerStorage.OfferStorage;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
public class OfferStorageRepositoryApp {

    @PersistenceContext
    private EntityManager entityManager;


    public Integer getLastOfferNumberForProductCode(String productCode, Integer userId) {
        Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(OfferStorage.class);
        criteria.createAlias("user", "user");
        criteria.add(Restrictions.eq("user.id", userId));
        criteria.add(Restrictions.eq("productCode", productCode));

        criteria.addOrder(Order.desc("creationDate"));
        criteria.setMaxResults(1);
        criteria.setProjection(Projections.property("offerNumber"));

        List<Integer> list = criteria.list();
        if (list.isEmpty()) {
            return 0;
        }
        return list.get(0);
    }
}
