package by.boiko.crm.dao.impl;

import by.boiko.crm.dao.MarketDao;
import by.boiko.crm.model.Market;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Erizo on 08.02.2018.
 */
@Repository
@Transactional
public class MarketDaoImpl implements MarketDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Market market) {
        sessionFactory.getCurrentSession().save(market);
    }
}
