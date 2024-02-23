package Dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KiemKeInfoDto {
    private int input_id;
    private String displayName;
    private float inputPrice;

    private int quantity;
    private int trueQuantity;
}
