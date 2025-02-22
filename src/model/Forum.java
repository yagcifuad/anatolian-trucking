package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Forum {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;

    @OneToMany(mappedBy = "forum",
            fetch = FetchType.EAGER,
            cascade = CascadeType.REMOVE,
            targetEntity = Comment.class,
            orphanRemoval = true)
    private Set comments=new HashSet<>();





    //private List<Comment> commentList;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

//    @JoinColumn(name = "user_id")

    //@NotFound(action = NotFoundAction.IGNORE)

    public Forum(String title, User user) {
        this.title = title;
        this.user = user;
        //List<Comment> commentLis
        // this.commentList = commentList;
    }


    public void setId(int id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }


}
