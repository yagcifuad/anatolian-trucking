package model;

//import com.jniwrapper.Str;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String startCity;
    private String startLocation;
    private String endLocation;
    private String endCity;
    private LocalDate dateCreated;
    private LocalDate dateUpdated;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private String status;

    @ManyToOne(cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER,
            optional = false)
    @JoinColumn(name = "manager_id")
    private Manager rManager;
    //private String rManager;
    @OneToMany(mappedBy = "destination",
            fetch = FetchType.EAGER,
            cascade = CascadeType.REMOVE,
            targetEntity = Checkpoint.class,
            orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set checkpoints=new HashSet<>();

//    @ManyToOne(cascade = CascadeType.MERGE,
//            fetch = FetchType.EAGER,
//            optional = false)
//    @JoinColumn(name = "driver_id")
//    private Driver driver;

    @OneToOne
    private Driver driver;
    @OneToOne
    private Cargo cargo;

    @OneToOne
    private Truck truck;

    @PreRemove
    private void onRemove() {
        if (truck != null && truck.getCurrentDestination() != null && truck.getCurrentDestination().equals(this)) {
            truck.setCurrentDestination(null);
        }
    }



    public Destination(String startCity, String start, String end, String endCity, Manager rManager, Driver driver, Cargo cargo, Truck truck, LocalDate departureDate, LocalDate arrivalDate, String status) {
        this.startCity = startCity;
        this.startLocation = start;
        this.endLocation = end;
        this.endCity = endCity;
        this.rManager = rManager;
        this.driver=driver;
        this.cargo=cargo;
        this.truck=truck;
        this.departureDate=departureDate;
        this.arrivalDate=arrivalDate;
        this.status=status;

    }

    @Override
    public String toString() {
        return startCity+"-"+ endCity;
    }


}
