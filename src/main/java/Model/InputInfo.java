package Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class InputInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "input_id")
    private Input input;
    @ManyToOne
    @JoinColumn(name = "Object_id")
    private Object object;

    private int quantity;
    private float inputPrice;
    private String status;
}
