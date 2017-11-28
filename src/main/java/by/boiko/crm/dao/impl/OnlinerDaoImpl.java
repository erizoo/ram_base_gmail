package by.boiko.crm.dao.impl;

import by.boiko.crm.dao.OnlinerDao;
import by.boiko.crm.model.pojo.SkuModel;
import by.boiko.crm.model.pojo.UnattachedGoods;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public class OnlinerDaoImpl implements OnlinerDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(SkuModel skuModel) {
        sessionFactory.getCurrentSession().save(skuModel);
    }

    @Override
    public List<UnattachedGoods> loadAllGoods() {
        return sessionFactory.getCurrentSession().createQuery("from UnattachedGoods").list();
    }

    @Override
    public void delete(String sku) {
        UnattachedGoods unattachedGoods = (UnattachedGoods) sessionFactory.getCurrentSession().createQuery("select u from UnattachedGoods u where sku = :sku")
                .setParameter("sku", sku).uniqueResult();
            sessionFactory.getCurrentSession().delete(unattachedGoods);

    }

    @Override
    public UnattachedGoods findBySky(String sku) {
        return (UnattachedGoods) sessionFactory.getCurrentSession().createQuery("select u from UnattachedGoods u where sku = :sku").setParameter("sku", sku).uniqueResult();
    }

}
