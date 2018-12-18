package by.boiko.crm.service.impl.ParserMail;

public class Migom {

    private String name;
    private String phoneNumber;
    private String url;

    public Migom() {
    }

    public Migom(String name, String phoneNumber, String url) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
