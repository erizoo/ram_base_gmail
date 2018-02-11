package by.boiko.crm.service;

import by.boiko.crm.model.Market;

import java.io.IOException;

/**
 * Created by Erizo on 08.02.2018.
 */
public interface MarketService {

    void startParser() throws IOException, InterruptedException;
}
