package Mapper;

import Dto.InputInfoDto;
import Model.InputInfo;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class InputInfoMapper {
    public static List<InputInfoDto> mapToDtoList(List<InputInfo> infoList) {
        return infoList.stream()
                .map(InputInfoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public static InputInfoDto mapToDto(InputInfo item) {
        LocalDateTime dateTime = item.getInput().getDateTime();
        Instant instant = dateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);

        return InputInfoDto.builder()
                .id(item.getId())
                .input_id(item.getInput().getId())
                .date(date)

                .displayName(item.getObject().getDisplayName())
                .supplier(item.getObject().getSupplier().getDisplayName())

                .status(item.getStatus())
                .inputPrice(item.getInputPrice())
                .quantity(item.getQuantity())
                .build();
    }
}
