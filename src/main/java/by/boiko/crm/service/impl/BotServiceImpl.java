package by.boiko.crm.service.impl;

import by.boiko.crm.dao.BotDao;
import by.boiko.crm.model.pojo.BotOrders;
import by.boiko.crm.service.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BotServiceImpl implements BotService {

    @Autowired
    private BotDao botDao;

    @Override
    public List<BotOrders> getAll(String type) {
        return botDao.loadAll(type);
    }

    @Override
    public void deleteItem(Long id) {
        botDao.deleteItem(id);
    }

    @Override
    public void save(BotOrders botOrders) {
        botDao.save(botOrders);
    }

}
