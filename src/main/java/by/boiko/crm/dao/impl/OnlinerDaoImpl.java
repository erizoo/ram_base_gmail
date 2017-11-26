package by.boiko.crm.dao.impl;

import by.boiko.crm.dao.OnlinerDao;
import by.boiko.crm.model.SkuModel;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class OnlinerDaoImpl implements OnlinerDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(SkuModel skuModel) {
        sessionFactory.getCurrentSession().save(skuModel);
    }
}
