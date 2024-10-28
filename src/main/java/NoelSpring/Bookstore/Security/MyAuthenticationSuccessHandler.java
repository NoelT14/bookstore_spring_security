package NoelSpring.Bookstore.Security;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


import java.io.IOException;

public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {



    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        boolean isAdmin = authentication.getAuthorities().stream().anyMatch(grantedAuthority ->
            grantedAuthority.getAuthority().equals("ROLE_ADMIN")
        );

        if (isAdmin) {
            response.sendRedirect("/bookstore");
        } else {
            boolean isUser = authentication.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_USER"));

            if (isUser) {
                response.sendRedirect("/bookstore_user");
            }
            else{
                throw new AccessDeniedException("Access is denied for this user");
            }
        }//main else

    }//method
}
