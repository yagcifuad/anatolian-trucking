package model;

import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Checkpoint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private boolean longStop;
    private LocalDate dateArrived;

    @ManyToOne(cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER,
            optional = false)
    @JoinColumn(name = "destination_ID")
    private Destination destination;


    public Checkpoint(String title, boolean longStop, LocalDate dateArrived,Destination destination) {
        this.title = title;
        this.longStop = longStop;
        this.dateArrived = dateArrived;
        this.destination=destination;
    }

    public Checkpoint(String title, boolean longStop) {
        this.title = title;
        this.longStop = longStop;
    }

    @Override
    public String toString() {
        return this.getId()+ ") "+this.getTitle() +" ("+this.getDateArrived()+")";
    }
}
