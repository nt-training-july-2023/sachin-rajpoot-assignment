package exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.gms.demo.exception.GlobalException;
import com.gms.demo.exception.ResourceNotFoundException;
import com.gms.demo.payloads.ApiResponse;

public class GlobalExceptionTest {
	private final GlobalException globalException = new GlobalException();

    @Test
    public void testResourceNotFoundExceptionHandler() {
        String errorMessage = "Resource not found.";
        ResourceNotFoundException ex = new ResourceNotFoundException(errorMessage, errorMessage, 0);

        ResponseEntity<ApiResponse> responseEntity = globalException.resourceNotFoundHandler(ex);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(errorMessage, responseEntity.getBody().getMessage());
        assertFalse(responseEntity.getBody().isSuccess());
    }

    @Test
    public void testMethodArgumentNotValidExceptionHandler() {
        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, null);

        ResponseEntity<Map<String, String>> responseEntity = globalException.methodArgumentNotValidExceptionHandler(ex);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        
    }

    @Test
    public void testHttpRequestMethodNotSupportedExceptionHandler() {
        HttpRequestMethodNotSupportedException ex = new HttpRequestMethodNotSupportedException("Invalid method");

        ResponseEntity<ApiResponse> responseEntity = globalException.httpRequestMethodNotSupportedExceptionHandler(ex);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
      
    }

    @Test
    public void testDataIntegrityViolationExceptionHandler() {
        DataIntegrityViolationException ex = new DataIntegrityViolationException("Data integrity violation");

        ResponseEntity<ApiResponse> responseEntity = globalException.DataIntegrityViolationExceptionHandler(ex);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
}
