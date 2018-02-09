package by.boiko.crm.service;

import by.boiko.crm.model.Parser;

import java.io.IOException;
import java.util.List;

public interface YandexMailService {

    List<Parser> check() throws IOException;
}
