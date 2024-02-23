package Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class KiemKe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<KiemKeInfo> infoList = new ArrayList<>();
    private String name;

    public KiemKe(String name) {
        this.name = name;
    }

    public void addInfo(KiemKeInfo info) {
        infoList.add(info);
        info.setKiemKe(this);
    }
}
