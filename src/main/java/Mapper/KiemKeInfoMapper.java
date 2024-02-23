package Mapper;

import Dto.KiemKeInfoDto;
import Model.KiemKeInfo;

public class KiemKeInfoMapper {


    public static KiemKeInfoDto mapToDto(KiemKeInfo item) {
        return KiemKeInfoDto.builder()
                .input_id(item.getInput().getId())
                .displayName(item.getInput().getObject().getDisplayName())
                .inputPrice(item.getInput().getInputPrice())

                .quantity(item.getQuantity())
                .trueQuantity(item.getTrueQuantity())
                .build();
    }
}
