package by.boiko.crm.dao;

import by.boiko.crm.model.pojo.BotOrders;

import java.util.List;

public interface BotDao {

    List<BotOrders> loadAll();

    void deleteItem(Long id);

    void save(BotOrders botOrders);
}
