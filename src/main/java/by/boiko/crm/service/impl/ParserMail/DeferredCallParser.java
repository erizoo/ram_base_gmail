package by.boiko.crm.service.impl.ParserMail;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Erizo on 11.02.2018.
 */
public class DeferredCallParser {

    public DeferredCall parser(String[] lines){
        DeferredCall deferredCall = new DeferredCall();
        List<String> linesList = Arrays.asList(lines);
        String firstChange = linesList.get(2).substring(0, linesList.get(2).length() - 8);
        String secondShange = firstChange.replaceAll("\\D+", "");
        StringBuffer result = new StringBuffer();
        result.append(secondShange.substring(0, 2)).append(" ").append(secondShange.substring(2, secondShange.length()));
        deferredCall.setPhoneNumber(String.valueOf(result));
        return deferredCall;
    }
}
