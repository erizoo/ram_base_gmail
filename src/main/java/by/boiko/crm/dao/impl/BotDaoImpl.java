package by.boiko.crm.dao.impl;

import by.boiko.crm.dao.BotDao;
import by.boiko.crm.model.pojo.BotOrders;
import by.boiko.crm.model.pojo.UnattachedGoods;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class BotDaoImpl implements BotDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<BotOrders> loadAll(String type) {
        Query query = null;
        if (type.equals("BOT")){
            query = sessionFactory.getCurrentSession().createQuery("FROM BotOrders D WHERE D.type = 'TENDER BOT'");
        } else {
            query = sessionFactory.getCurrentSession().createQuery("FROM BotOrders D WHERE D.type = 'YANDEX TAXI'");
        }
        assert false;
        return query.list();
    }

    @Override
    public void deleteItem(Long id) {
        BotOrders botOrders = (BotOrders) sessionFactory.getCurrentSession().createQuery("select u from BotOrders u where id = :id")
                .setParameter("id", id).uniqueResult();
        sessionFactory.getCurrentSession().delete(botOrders);
    }

    @Override
    public void save(BotOrders botOrders) {
        sessionFactory.getCurrentSession().save(botOrders);
    }

}
