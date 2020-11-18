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
    private double highestBid;

    @Column(name = "bidderid")
    private int bidderId;

    @Column(name = "bidNumber")
    private int bidNumber;

    @Column(name = "photoNumber")
    private int photoNumber;

    @Column(name="category")
    private String category;

    @Column(name = "rate")
    private int rate;
//    public Lot() {}


    public void setPhotoNumber(int photoNumber) {
        this.photoNumber = photoNumber;
    }

    public int getPhotoNumber() {
        return photoNumber;
    }

    public void setBidNumber(int bidNumber) {
        this.bidNumber = bidNumber;
    }

    public int getBidderId() {
        return bidderId;
    }

    public int getStatus() {
        return status;
    }

    public int getBidNumber() {
        return bidNumber;
    }

    public double getHighestBid() {
        return highestBid;
    }

    public long getDuration() {
        return duration;
    }

    public long getMaxPrice() {
        return maxPrice;
    }

    public long getPrice() {
        return price;
    }

    public long getStartTime() {
        return startTime;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getPhoto() {
        return photo;
    }

    public String getTitle() {
        return title;
    }

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

    public void setHighestBid(double highestBid) {
        this.highestBid = highestBid;
    }

    public void setLotId(int lotId) {
        this.lotId = lotId;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getSellerId() {
        return sellerId;
    }
}
