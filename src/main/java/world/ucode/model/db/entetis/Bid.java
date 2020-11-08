package world.ucode.model.db.entetis;
import lombok.*;

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

    @Column(name = "lotid")
    private int lotid;

    @Column(name = "bidderId")
    private int bidderId;

    @Column(name = "price")
    private int price;

    @Column(name = "statusOfBid")
    private int statusOfBid;

    public int getBidderId() {
        return bidderId;
    }

}
