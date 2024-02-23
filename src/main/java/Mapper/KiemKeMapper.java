package Mapper;

import Dto.KiemKeDto;
import Dto.KiemKeInfoDto;
import Model.KiemKe;

import java.util.List;

public class KiemKeMapper {
    public static List<KiemKeDto> mapToDtoList(List<KiemKe> list) {
        return list.stream().map(KiemKeMapper::mapToDto).toList();
    }

    public static KiemKeDto mapToDto(KiemKe item) {
        List<KiemKeInfoDto> list = item.getInfoList().stream()
                .map(KiemKeInfoMapper::mapToDto).toList();

        return KiemKeDto.builder()
                .name(item.getName())
                .list(list)
                .build();
    }
}
