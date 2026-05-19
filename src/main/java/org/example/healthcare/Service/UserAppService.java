package org.example.healthcare.Service;

import lombok.RequiredArgsConstructor;
import org.example.healthcare.DTOs.UserAppDTO;
import org.example.healthcare.Mappers.UserAppMapper;
import org.example.healthcare.Model.UserApp;
import org.example.healthcare.Repository.UserAppRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserAppService {

    private final UserAppRepository userAppRepository;
    private final UserAppMapper userAppMapper;


    public UserAppDTO AjouterUser (UserAppDTO userAppDTO) {
        UserApp userApp =
                userAppMapper.toEntity(userAppDTO);
        return userAppMapper.toDTO(userAppRepository.save(userApp));
    }

    public UserAppDTO editUser (Long id , UserAppDTO userAppDTO){
        if(userAppRepository.findById(id).isPresent()){
            UserApp userApp = userAppMapper.toEntity(userAppDTO);
            userApp.setId(id);
            return userAppMapper.toDTO(userAppRepository.save(userApp));
        }else {
            return null;
        }
    }

    public void SupprimerUser( Long id){
        userAppRepository.deleteById(id);
    }

    public Page<UserAppDTO> listerUsers(Pageable pageable){
        Page<UserApp> userList = userAppRepository.findAll(pageable);
        return userList.map(userAppMapper::toDTO);
    }

    public UserAppDTO consulterUser (Long id){
        return userAppMapper.toDTO(userAppRepository.findById(id).get());
    }


}
