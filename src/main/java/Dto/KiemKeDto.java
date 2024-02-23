package Dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KiemKeDto {
    private String name;
    private List<KiemKeInfoDto> list;
}
