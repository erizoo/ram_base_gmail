package by.boiko.crm.service.impl;


import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.bitfinex.v1.Bitfinex;
import org.knowm.xchange.bitfinex.v1.BitfinexExchange;
import org.knowm.xchange.bitstamp.BitstampExchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trade;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.service.marketdata.MarketDataService;

import java.io.IOException;
import java.util.List;

public class Main {
//    public static void main(String[] argv) throws IOException {
////
////        String fileName = "D:\\бойко.txt";
////        BufferedImage image = null;
////        Stream<String> stream = Files.lines(Paths.get(fileName));
////        List<String> result = stream.collect(Collectors.toList());
////        int count = 0;
////        for (String items : result) {
////            Files.createDirectories(Paths.get("D:\\foto\\" + items));
////            count++;
////            if (count == 1500){
////                break;
////            }
////        }
////
////    }

    public static void main(String[] args) throws IOException {
        Exchange bitfinex = ExchangeFactory.INSTANCE.createExchange(BitfinexExchange.class.getName());
        MarketDataService marketDataService = bitfinex.getMarketDataService();
        Ticker ticker = marketDataService.getTicker(CurrencyPair.BTC_USD);
        Trades trades = marketDataService.getTrades(CurrencyPair.BTC_USD);
        List<Trade> tradesList = trades.getTrades();
        System.out.println(tradesList);
    }
}
