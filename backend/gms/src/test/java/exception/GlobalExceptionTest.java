package exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import com.gms.demo.exception.GlobalExcHandler;
import com.gms.demo.payloads.ApiResponse;

public class GlobalExceptionTest {
	private final GlobalExcHandler globalException = new GlobalExcHandler();

	@Test
	public void testHttpRequestMethodNotSupportedExceptionHandler() {
		HttpRequestMethodNotSupportedException ex = new HttpRequestMethodNotSupportedException("Invalid method");

		ResponseEntity<ApiResponse> responseEntity = globalException.httpRequestMethodNotSupportedExceptionHandler(ex);

		assertNotNull(responseEntity);
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

	}

}
