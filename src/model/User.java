package model;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String phoneNum;
    private byte[] user_image;

    private String userUID;


//    @OneToMany(mappedBy = "user",
//            cascade = {CascadeType.REMOVE},
//            orphanRemoval = true)
//    @LazyCollection(LazyCollectionOption.FALSE)
//    private List<Forum>forumList;

//    @OneToMany(mappedBy = "user",
//            cascade = {CascadeType.PERSIST,
//                    CascadeType.MERGE},
//            orphanRemoval = true)
//    @LazyCollection(LazyCollectionOption.FALSE)
//    private List<Comment>commentList;

    public User(String login, String password, String name, String surname, LocalDate birthDate, String phoneNum, byte[] user_image) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.phoneNum = phoneNum;
        this.user_image = user_image;
    }

//    public User(String login, String password, String name, String surname, LocalDate birthDate, String phoneNum, String userUID) {
//        this.login = login;
//        this.password = password;
//        this.name = name;
//        this.surname = surname;
//        this.birthDate = birthDate;
//        this.phoneNum = phoneNum;
//        this.userUID = userUID;
//    }


}
