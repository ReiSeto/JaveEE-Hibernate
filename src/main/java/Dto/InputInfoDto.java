package Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter
public class InputInfoDto {
    private int id;
    private int input_id;
    private Date date;

    private String displayName;
    private String supplier;

    private int quantity;
    private float inputPrice;
    private String status;
}
