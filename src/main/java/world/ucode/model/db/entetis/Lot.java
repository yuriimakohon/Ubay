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
    private int id;

    @Column(name = "sellerId")
    private int sellerId;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private int price;

    @Column(name = "photo")
    private String photo;

    @Column(name = "duration")
    private int duration;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private int status;

    @Column(name = "highestBid")
    private int highestBid;

    @Column(name = "bidderid")
    private int bidderid;
}
