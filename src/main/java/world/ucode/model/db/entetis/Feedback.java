package world.ucode.model.db.entetis;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="comment")
    private String comment;

    @Column(name="mark")
    private int mark;

    @Column(name="lotid")
    private int lotId;

    @Column(name="userid")
    private int userId;
}
