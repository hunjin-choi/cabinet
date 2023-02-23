package ftclub.cabinet.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ServiceException extends RuntimeException {
    final ExceptionStatus status;
}
