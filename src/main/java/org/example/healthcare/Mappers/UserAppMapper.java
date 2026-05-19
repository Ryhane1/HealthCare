package org.example.healthcare.Mappers;

import org.example.healthcare.DTOs.UserAppDTO;
import org.example.healthcare.Model.UserApp;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserAppMapper {
    UserAppDTO toDTO(UserApp userApp);
    UserApp toEntity(UserAppDTO userAppDTO);
//    Page<UserAppDTO> toDTOPage(Page<UserApp> userApps);
//    Page<UserAppDTO> toDTOList(Page<UserApp> userApps);

}
