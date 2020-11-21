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
    private double price;

    @Column(name = "maxPrice") // add to table
    private double maxPrice;

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

    @Column(name = "bidNumber")
    private int bidNumber;

    @Column(name = "photoNumber")
    private int photoNumber;

    @Column(name="category")
    private String category;

    @Column(name = "rate")
    private float rate;

    @Column(name = "feedbacknumb")
    private int feedbackNumber;

    @Column(name = "bidid")
    private int bidId;

//    public Lot() {}



//    @OneToOne
//    @JoinColumn(name="bidid", insertable = false, updatable = false)
//    @Fetch(FetchMode.JOIN)
//    private Bid bid;
//    public Bid getBid() {
//        return bid;
//    }


    public int getBidId() {
        return bidId;
    }
    public void setBidId(int bidId) {
        this.bidId = bidId;
    }
    public void setPhotoNumber(int photoNumber) {
        this.photoNumber = photoNumber;
    }
    public int getPhotoNumber() {
        return photoNumber;
    }
    public void setBidNumber(int bidNumber) {
        this.bidNumber = bidNumber;
    }
    public int getStatus() {
        return status;
    }
    public int getBidNumber() {
        return bidNumber;
    }
    public long getDuration() {
        return duration;
    }
    public double getMaxPrice() {
        return maxPrice;
    }
    public double getPrice() {
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
    public void setPrice(double price) {
        this.price = price;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public void setRate(float rate) {
        this.rate = rate;
    }
    public float getRate() {
        return rate;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setDuration(long duration) {
        this.duration = duration;
    }
    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
    public int getLotId() {
        return lotId;
    }
    public int getFeedbackNumber() {
        return feedbackNumber;
    }
    public void setFeedbackNumber(int feedbackNumber) {
        this.feedbackNumber = feedbackNumber;
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
