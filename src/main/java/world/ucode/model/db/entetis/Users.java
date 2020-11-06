package world.ucode.model.db.entetis;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class Users {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="username")
    private String username;

    @Column(name="login")
    private String login;

    @Column(name="password")
    private String password;

    @Column(name="token")
    private String token;

    @Column(name="userRole")
    private int userRole;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Bid> userbids;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval =true)
    private List<Lot> userlots;

    public Users(String name) {

    }




//    public Users() {
//    }
//
//    public Users(String name) {
//        super();
//        this.name = name;
//
//    }
//
//    public Users(int id, String name) {
//        super();
//        this.id = id;
//        this.name = name;
//
//    }
//
//    public int getId() {
//        return id;
//    }
//    public void setId(int id) {
//        this.id = id;
//    }
//    public String getName() {
//        return name;
//    }
//    public void setName(String name) {
//        this.name = name;
//    }

}