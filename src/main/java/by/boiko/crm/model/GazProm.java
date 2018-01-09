package by.boiko.crm.model;

/**
 * Created by Erizo on 10.01.2018.
 */
public class GazProm {

    private String address;
    private String latitud;
    private String longitud;

    public GazProm() {
    }

    public GazProm(String address, String latitud, String longitud) {
        this.address = address;
        this.latitud = latitud;
        this.longitud = longitud;
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
