package paxxa.com.ees.repository.seller;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paxxa.com.ees.entity.seller.Seller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
public class SellerRepositoryApp {

    @PersistenceContext
    private EntityManager entityManager;

    public List<String> getActiveAvailableSellers(){
        Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Seller.class);
        criteria.add(Restrictions.eq("enabled", Boolean.TRUE));
        criteria.setProjection(Property.forName("sellerCode"));
        return criteria.list();
    }
}
