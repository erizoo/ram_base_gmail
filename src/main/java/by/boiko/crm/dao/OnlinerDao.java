package by.boiko.crm.dao;

import by.boiko.crm.model.pojo.SkuModel;
import by.boiko.crm.model.pojo.UnattachedGoods;

import java.util.List;

/**
 * Created by Erizo on 26.11.2017.
 */

public interface OnlinerDao {

    void save(SkuModel skuModel);

    List loadAllGoods();

    void delete(String sku);

    UnattachedGoods findBySky(String sku);
}
