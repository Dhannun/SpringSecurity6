package click.touchit.security.user;

import click.touchit.security.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(()
                -> new ResourceNotFoundException("User With Email [ %s ]".formatted(email)));
    }
}
