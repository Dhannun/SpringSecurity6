package click.touchit.security.secured;

import click.touchit.security.user.User;
import click.touchit.security.user.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@SecurityRequirement(name = "BearerAuth")
public class AdminController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<String> demo() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok(
                "Hello %s %s [ Role: %s ] From Secured Endpoint "
                        .formatted(user.getFirstname(), user.getLastname(), user.getRole().toString())
        );
    }
}
