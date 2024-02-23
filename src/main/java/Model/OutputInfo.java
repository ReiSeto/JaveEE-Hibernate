package Model;

import jakarta.persistence.*;

@Entity
public class OutputInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "output_id")
    private Output output;

    @ManyToOne
    @JoinColumn(name = "Object_id")
    private Object object;

    private int quantity;
    private float outputPrice;
    private String status;
}
