package world.ucode.model.db.entetis;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Entity

@Table(name="users")
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Users implements Serializable{

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

    @Column(name="balance")
    private int balance;

    @Column(name="userphoto")
    private String userphoto;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="bidderId")
    @Fetch(FetchMode.SELECT)
    private List<Bid> userbids;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="sellerId")
    @Fetch(FetchMode.SELECT)
    private List<Lot> userlots;
//
    public Users(String token, String login, String password, String role) {
        this.username = login;
        this.login = login;
        this.token = token;
        this.password = password;
//        this.role = role;
    }

    public int getId() {
        return this.id;
    }

    public boolean userValidPassword(String login, String password) {
        return this.login.equals(login) && this.password.equals(password);
    }


    public String getToken() {
        return this.token;
    }



    public Users() {

    }

    public String getusertname() {

        return username;
    }


    public List<Bid> getUserbids() {
        return userbids;
    }

    public List<Lot> getUserLots() {
        return userlots;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLogin() {
        return login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //    public Users(String name, String token) {
//                this.username = name;
//        this.token = token;
//    }


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