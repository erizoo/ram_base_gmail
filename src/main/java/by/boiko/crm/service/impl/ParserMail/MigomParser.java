package by.boiko.crm.service.impl.ParserMail;

public class MigomParser {

    public Migom parser(String[] lines) {
        Migom migom = new Migom();
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].contains("tel:")) {
                String[] strings = lines[i].split(">");
                String result = strings[1].substring(0, strings[1].length() - 3);
                migom.setPhoneNumber(result);
            }
            if (lines[i].contains("Покупатель")) {
                String str = lines[i + 6].replaceAll("[^А-Яа-я]", "");
                migom.setName(str);
            }
        }

        return migom;
    }
}
