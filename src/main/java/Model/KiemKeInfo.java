package Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KiemKeInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "kiemke_id")
    private KiemKe kiemKe;

    @OneToOne
    @JoinColumn(name = "input_id")
    private InputInfo input;
    private int quantity;
    private int trueQuantity;
}
