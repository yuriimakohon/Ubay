package world.ucode.model.db.entetis;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.util.List;

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

    @Column(name = "lotid", nullable = false)
    private int lotid;

    @Column(name = "bidderId")
    private int bidderId;

    @Column(name = "price")
    private double price;

    @Column(name = "statusOfBid")
    private int statusOfBid;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="lotid", insertable = false, updatable = false)
    @Fetch(FetchMode.JOIN)
    private Lot lot;

//    @ManyToOne (optional=false, cascade=CascadeType.ALL)
//    @JoinColumn (name="bidderId", insertable = false, updatable = false)
//    private Users user;

    public Bid(int lotId, int bidderId, double price, int status) {
        this.bidderId = bidderId;
        this.lotid = lotId;
        this.price = price;
        this.statusOfBid = status;
    }

    public Bid(int lotId, int bidderId, double price) {
        this.bidderId = bidderId;
        this.lotid = lotId;
        this.price = price;
    }


    public int getBidderId() {
        return bidderId;
    }

    public void setBidderId(int bidderId) {
        this.bidderId = bidderId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }

    public void setLotId(int lotId) {
        this.lotid = lotId;
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
        return lotid;
    }

    public int getStatusOfBid() {
        return statusOfBid;
    }

    public Lot getLot() {
        return lot;
    }
}
