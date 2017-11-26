package by.boiko.crm.service;

import by.boiko.crm.model.Onliner;
import by.boiko.crm.model.SkuModel;
import by.boiko.crm.model.Table;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface OnlinerService {

    List<Onliner> getReviews(String decodedUrl);

    ArrayList<Table> getDescription();

    List<String> getImages();

    List<String> getNames(String name);

    void save(SkuModel skuModel);
}
