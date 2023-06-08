package vn.com.trungtien.management.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.com.trungtien.management.domains.entities.User;
import vn.com.trungtien.management.form.UserCreateForm;
import vn.com.trungtien.management.form.UserFilterForm;
import vn.com.trungtien.management.form.UserUpdateForm;
import vn.com.trungtien.management.repositories.IUserRepository;
import vn.com.trungtien.management.services.dto.ProfileDTO;
import vn.com.trungtien.management.services.dto.UserDTO;
import vn.com.trungtien.management.services.mapper.UserMapper;
import vn.com.trungtien.management.specification.UserSpecification;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final IUserRepository repository;

    private final UserMapper mapper;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;
    public Page<UserDTO> findAll(Pageable pageable, UserFilterForm form){
        Specification<User> spec = UserSpecification.buildWhere(form);
        Page<User> page = repository.findAll(spec, pageable);
        List<User> users = page.getContent();
        List<UserDTO> dtos = mapper.toDTOS(users);
        return new PageImpl<>(dtos, pageable, page.getTotalElements());
    }
    public UserDTO findById(Long id){
        return mapper.toDTO(repository.findById(id).orElse(null));
    }
    public void create(UserCreateForm form){
        String encodedPassword = passwordEncoder.encode(form.getPassword());
        User user = modelMapper.map(form, User.class);
        user.setPassword(encodedPassword);
        repository.save(user);
    }
    public void update(UserUpdateForm form){
        String encodedPassword = passwordEncoder.encode(form.getPassword());
        User user = modelMapper.map(form, User.class);
        user.setPassword(encodedPassword);
        repository.save(user);
    }
    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public ProfileDTO findByUsername(String username){
        return modelMapper.map(repository.findByUsername(username), ProfileDTO.class);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList((user.getRole().toString()))
        );
    }
}
