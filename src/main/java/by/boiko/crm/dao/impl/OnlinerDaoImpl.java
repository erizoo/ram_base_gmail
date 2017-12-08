package by.boiko.crm.dao.impl;

import by.boiko.crm.dao.OnlinerDao;
import by.boiko.crm.model.pojo.PendingGoods;
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
    public List<UnattachedGoods> loadAllGoods(int page) {
        if (page == 1){
            return sessionFactory.getCurrentSession().createQuery("from UnattachedGoods").setFirstResult(1).setMaxResults(10).list();
        }else {
            return sessionFactory.getCurrentSession().createQuery("from UnattachedGoods").setFirstResult(page * 10 - 10 + 1).setMaxResults(10).list();
        }

    }

    @Override
    public List<SkuModel> loadGoods() {
        return sessionFactory.getCurrentSession().createQuery("from SkuModel").list();
    }

    @Override
    public void saveGoods(String sku, String name) {
        UnattachedGoods unattachedGoods = new UnattachedGoods();
        unattachedGoods.setSku(sku);
        unattachedGoods.setName(name);
        sessionFactory.getCurrentSession().save(unattachedGoods);
    }

    @Override
    public int getAllCount() {
        return ((Long) sessionFactory.getCurrentSession().createQuery("select count(*) from UnattachedGoods").uniqueResult()).intValue();
    }

    @Override
    public void delete(int id) {
        UnattachedGoods unattachedGoods = (UnattachedGoods) sessionFactory.getCurrentSession().createQuery("select u from UnattachedGoods u where id = :id")
                .setParameter("id", id).uniqueResult();
            sessionFactory.getCurrentSession().delete(unattachedGoods);

    }

    @Override
    public UnattachedGoods findBySky(String sku) {
        return (UnattachedGoods) sessionFactory.getCurrentSession().createQuery("select u from UnattachedGoods u where sku = :sku").setParameter("sku", sku).uniqueResult();
    }

    @Override
    public void moveGoods(int id) {
        UnattachedGoods unattachedGoods = (UnattachedGoods) sessionFactory.getCurrentSession().createQuery("select u from UnattachedGoods u where id = :id")
                .setParameter("id", id).uniqueResult();
        PendingGoods pendingGoods = new PendingGoods();
        pendingGoods.setName(unattachedGoods.getName());
        pendingGoods.setSku(unattachedGoods.getSku());
        sessionFactory.getCurrentSession().delete(unattachedGoods);
        sessionFactory.getCurrentSession().save(pendingGoods);
    }

}
