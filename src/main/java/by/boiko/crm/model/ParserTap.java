package by.boiko.crm.model;

/**
 * Created by Erizo on 09.01.2018.
 */
public class ParserTap {

    private String city;
    private String address;
    private String latitud;
    private String longitud;

    public ParserTap() {
    }

    public ParserTap(String address, String latitud, String longitud) {
        this.address = address;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public ParserTap(String city, String address, String latitud, String longitud) {
        this.city = city;
        this.address = address;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
}
