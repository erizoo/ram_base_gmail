package by.boiko.crm.service.impl.ParserMail;

/**
 * Created by Erizo on 11.02.2018.
 */
public class DeferredCall {

    private String phoneNumber;

    public DeferredCall() {
    }

    public DeferredCall(String phone_number) {
        this.phoneNumber = phone_number;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
