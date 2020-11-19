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

    public Feedback(String comment, int mark, int lotId, int userId) {
        this.comment = comment;
        this.mark = mark;
        this.lotId = lotId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public int getMark() {
        return mark;
    }

    public int getLotId() {
        return lotId;
    }

    public int getUserId() {
        return userId;
    }
}
