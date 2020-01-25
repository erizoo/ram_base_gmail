package by.boiko.crm.model.pojo;

import com.google.api.client.util.DateTime;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@javax.persistence.Table(name = "bot_orders")
public class BotOrders {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "NUMBER")
    private String number;
    @Column(name = "TYPE")
    private String type;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TIME")
    private Date date;


    public BotOrders() {
    }

    public BotOrders(String name, String number, String type) {
        this.name = name;
        this.number = number;
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
