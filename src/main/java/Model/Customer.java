package Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "customer")
    private List<OutputInfo> outputInfoList;

    private String displayName;
    private String address;
    private String phone;
    private String email;
    private String moreInfo;
    private LocalDateTime dateTime;

}
