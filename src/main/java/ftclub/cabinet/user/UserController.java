package ftclub.cabinet.user;

import ftclub.cabinet.exception.NotFoundUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    final UserRepository userRepository;

    @GetMapping("/{intraId}")
    public UserEntity get(@PathVariable String intraId) {
        return userRepository.findByIntraId(intraId).orElseThrow(NotFoundUserException::new);
    }

    @ExceptionHandler(NotFoundUserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void notFoundUser() { }

}
