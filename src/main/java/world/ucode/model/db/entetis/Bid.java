package world.ucode.model.db.entetis;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bid")
public class Bid {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "lotId", nullable = false)
    private int lotId;

    @Column(name = "bidderId")
    private int bidderId;

    @Column(name = "price")
    private double price;

    @Column(name = "statusOfBid")
    private int statusOfBid;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="lotId", insertable=false, updatable=false)
    @Fetch(FetchMode.JOIN)
    private Lot lot;

//    @ManyToOne (cascade=CascadeType.ALL)
//    @JoinColumn (name="bidderId", insertable=false, updatable=false)
//    @Fetch(FetchMode.SELECT)
//    private Users user;

    public Bid(int lotId, int bidderId, double price, int status) {
        this.bidderId = bidderId;
        this.lotId = lotId;
        this.price = price;
        this.statusOfBid = status;
    }

    public Bid(int lotId, int bidderId, double price) {
        this.bidderId = bidderId;
        this.lotId = lotId;
        this.price = price;
    }

//    public Users getUser() {
//        return user;
//    }
    public int getBidderId() {
        return bidderId;
    }

    public void setBidderId(int bidderId) {
        this.bidderId = bidderId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLotId(int lotId) {
        this.lotId = lotId;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStatusOfBid(int statusOfBid) {
        this.statusOfBid = statusOfBid;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public int getLotId() {
        return lotId;
    }

    public int getStatusOfBid() {
        return statusOfBid;
    }

    public Lot getLot() {
        return lot;
    }
}
