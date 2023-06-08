package vn.com.trungtien.management.domains.audit;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import vn.com.trungtien.management.services.UserService;
import vn.com.trungtien.management.services.dto.ProfileDTO;

import java.util.Optional;


public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return Optional.ofNullable(username);
    }
}