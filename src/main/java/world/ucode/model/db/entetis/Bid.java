package world.ucode.model.db.entetis;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
    private int price;

    @Column(name = "statusOfBid")
    private int statusOfBid;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="lotid", insertable = false, updatable = false)
    @Fetch(FetchMode.JOIN)
    private Lot lot;

//    @ManyToOne (optional=false, cascade=CascadeType.ALL)
//    @JoinColumn (name="bidderId", insertable = false, updatable = false)
//    private Users user;


    public int getBidderId() {
        return bidderId;
    }

    public Lot getLot(){
        return lot;
    }

//    public Users getUser(){
//        return user;
//    }
}
