package by.boiko.crm.model;


public class Email {

    private int emailNumber;
    private String emailSubject;
    private String emailFrom;
    private String subject;
    private String date;


    public Email(int emailNumber, String emailSubject, String emailFrom, String subject) {
        this.emailNumber = emailNumber;
        this.emailSubject = emailSubject;
        this.emailFrom = emailFrom;
        this.subject = subject;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getEmailNumber() {
        return emailNumber;
    }

    public void setEmailNumber(int emailNumber) {
        this.emailNumber = emailNumber;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    @Override
    public String toString() {
        return "Email{" +
                "emailNumber=" + emailNumber +
                ", emailSubject='" + emailSubject + '\'' +
                ", emailFrom='" + emailFrom + '\'' +
                '}';
    }
}
