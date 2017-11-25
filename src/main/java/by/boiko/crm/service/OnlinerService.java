package by.boiko.crm.service;

import by.boiko.crm.model.Onliner;
import by.boiko.crm.model.Table;

import java.util.ArrayList;
import java.util.List;

public interface OnlinerService {

    List<Onliner> getReviews();

    ArrayList<Table> getDescription();

    List<String> getImages();

    List<String> getNames(String name);
}
