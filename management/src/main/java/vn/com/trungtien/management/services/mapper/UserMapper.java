package vn.com.trungtien.management.services.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.com.trungtien.management.domains.entities.User;
import vn.com.trungtien.management.services.dto.UserDTO;

import java.util.List;

@Component
public class UserMapper implements EntityMapper<User, UserDTO> {

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDTO toDTO(User entity) {
        return modelMapper.map(entity, UserDTO.class);
    }

    @Override
    public User toEntity(UserDTO dto) {
        return modelMapper.map(dto,User.class);
    }

    @Override
    public List<UserDTO> toDTOS(List<User> entities) {
        return modelMapper.map(entities, new TypeToken<List<UserDTO>>(){}.getType());
    }
    @Override
    public List<User> toEntities(List<UserDTO> dtos) {
        return modelMapper.map(dtos, new TypeToken<List<User>>(){}.getType());
    }
}