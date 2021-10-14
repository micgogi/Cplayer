package com.stackroute.favouriteservice.exception;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(ExceptionController.class)
public class ExceptionControllerTest {

    @InjectMocks
    ExceptionController exceptionController;

    @Test
    public void playerAlreadyExistsExceptionTest() {
        PlayerAlreadyExistsException e = new PlayerAlreadyExistsException("Bad Request");
        ResponseEntity<String> responseEntity = exceptionController.playerAlreadyExistException(e);
    }

    @Test
    public void playerNotFoundExceptionTest() {
        PlayerNotFoundException e = new PlayerNotFoundException("Not Found");
        ResponseEntity<String> responseEntity = exceptionController.playerNotFoundException(e);
    }

}
