package vn.com.trungtien.management.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import vn.com.trungtien.management.domains.entities.Student;
import vn.com.trungtien.management.domains.entities.User;
import vn.com.trungtien.management.domains.entities.Vehicle;
import vn.com.trungtien.management.form.BuildingCreateForm;
import vn.com.trungtien.management.form.StudentCreateForm;
import vn.com.trungtien.management.form.UserCreateForm;
import vn.com.trungtien.management.form.VehicleCreateForm;

@Configuration
public class DIContainer {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.typeMap(StudentCreateForm.class, Student.class)
                .addMappings(mapper -> mapper.<Long>map(StudentCreateForm::getDepartmentId,(des,id) ->des.getDepartment().setId(id)));
        modelMapper.typeMap(UserCreateForm.class, User.class)
                .addMappings(mapper -> mapper.<Long>map(UserCreateForm::getBuildingId,(des,id) ->des.getBuilding().setId(id)));
        modelMapper.typeMap(VehicleCreateForm.class, Vehicle.class)
                .addMappings(mapper -> mapper.<Long>map(VehicleCreateForm::getUserId,(des,id) ->des.getUser().setId(id)));
        return modelMapper;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}