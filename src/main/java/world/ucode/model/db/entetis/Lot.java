package world.ucode.model.db.entetis;

import lombok.*;

import javax.persistence.*;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lot")
public class Lot {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int lotId;

    @Column(name = "sellerId")
    private int sellerId;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private long price;

    @Column(name = "maxPrice") // add to table
    private long maxPrice;

    @Column(name = "photo")
    private String photo;

    @Column(name = "startTime") // add to table
    private long startTime;

    @Column(name = "duration")
    private long duration;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private int status;

    @Column(name = "highestBid")
    private int highestBid;

    @Column(name = "bidderid")
    private int bidderId;

    @Column(name = "bidnumber")
    private int bidnumber;

    @Column(name="category")
    private String category;

//    public Lot() {}

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setHighestBid(int highestBid) {
        this.highestBid = highestBid;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBidderId(int bidderId) {
        this.bidderId = bidderId;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public void setMaxPrice(long maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public int getLotId() {
        return lotId;
    }


    public int getSellerId() {
        return sellerId;
    }
}
