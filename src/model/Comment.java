package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Comment  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String commentText;

    private LocalDate dateCreated;
    private LocalDate dateModified;


    @ManyToOne(cascade = CascadeType.MERGE,//CascadeType.ALL
            fetch = FetchType.EAGER,
            optional = false)
    private User user;

    @ManyToOne(cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER,
            optional = false)
     @JoinColumn(name = "forum_id")
    private Forum forum;

    @ManyToOne(cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER,
            optional = true)
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;

    @OneToMany(mappedBy = "parentComment",
            fetch = FetchType.EAGER,
            cascade = CascadeType.REMOVE,
            targetEntity = Comment.class,
            orphanRemoval = true)
    private Set<Comment> replies = new HashSet<>();


    public Comment(String commentText, User user,Forum forum,Comment parentComment) {
        this.commentText = commentText;
        this.user = user;
        this.dateCreated=LocalDate.now();
        this.forum=forum;
        this.parentComment=parentComment;
    }
    

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return commentText + (parentComment != null ? "         re:" + parentComment.getCommentText() : "");
    }

}
