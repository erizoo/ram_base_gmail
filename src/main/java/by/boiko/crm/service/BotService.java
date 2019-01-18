package by.boiko.crm.service;

import by.boiko.crm.model.pojo.BotOrders;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BotService {

    List<BotOrders> getAll(String type);

    void deleteItem(Long id);

    void save(BotOrders botOrders);
}
