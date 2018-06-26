package by.boiko.crm.dao;

import by.boiko.crm.model.Market;
import by.boiko.crm.model.pojo.PendingGoods;
import by.boiko.crm.model.pojo.SkuModel;
import by.boiko.crm.model.pojo.UnattachedGoods;
import by.boiko.crm.service.impl.NewParserModel;

import java.util.List;

/**
 * Created by Erizo on 26.11.2017.
 */

public interface OnlinerDao {

    void save(Market market);

    List loadAllGoods(int page);

    void delete(int id);

    UnattachedGoods findBySky(String sku);

    void moveGoods(String id, String url);

    List<SkuModel> loadGoods();

    void saveGoods(String sku, String name);

    int getAllCount();

    List<UnattachedGoods> loadAllUnattachedGoods();

    List<PendingGoods> getCheckGoods();
}
