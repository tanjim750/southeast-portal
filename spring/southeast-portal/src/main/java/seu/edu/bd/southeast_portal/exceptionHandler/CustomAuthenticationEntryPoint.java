package seu.edu.bd.southeast_portal.exceptionHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException {

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        Map<String, Object> data = new HashMap<>();

        Throwable cause = authException.getCause();
        if (cause instanceof SignatureException) {
            data.put("status","failed");
            data.put("error","Invalid Authentication Token");
            data.put("message","Unauthorized");
        } else {
            data.put("status","failed");
            data.put("error",authException.getMessage());
            data.put("message","Unauthorized");
        }

        response.getOutputStream().write(objectMapper.writeValueAsBytes(data));
    }


}
