package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Driver extends User implements Serializable {

    private LocalDate medCertificateDate;
    private String medCertificateNumber;
    private String driverLicense;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> myComments;

//    @OneToMany(mappedBy = "driver",
//            fetch = FetchType.EAGER,
//            cascade = CascadeType.REMOVE,
//            targetEntity = Destination.class,
//            orphanRemoval = true)
//    private Set destinations=new HashSet<>();

    @OneToOne
    private Driver driver;


    public Driver(String login, String password, String name, String surname, LocalDate birthDate, String phoneNum, byte[] user_image, LocalDate medCertificateDate, String medCertificateNumber, String driverLicense) {
        super(login, password, name, surname, birthDate, phoneNum, user_image);
        this.medCertificateDate = medCertificateDate;
        this.medCertificateNumber = medCertificateNumber;
        this.driverLicense = driverLicense;
    }

    //    public Driver(String login, String password, String name, String surname, LocalDate birthDate, String phoneNum,  LocalDate medCertificateDate, String medCertificateNumber, String driverLicense) {
//        super(login, password, name, surname, birthDate, phoneNum,);
//        this.medCertificateDate = medCertificateDate;
//        this.medCertificateNumber = medCertificateNumber;
//        this.driverLicense = driverLicense;
//    }

    @Override
    public String toString() {
        return "("+id+")  "+ getName() ;
    }

}
