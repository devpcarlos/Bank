package count.bank.Security.Jwt;

import count.bank.Security.CustomerDetailsService;
import count.bank.Security.CustomersDetails;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;

@Component
public class JwtAuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CustomerDetailsService customerDetailsService;

    // Método para extraer el JWT desde el encabezado Authorization
    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7); // Retorna el JWT eliminando el prefijo "Bearer "
        }
        return null; // Si no hay JWT o no empieza con "Bearer ", retorna null
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = parseJwt(request); // Llama al método para obtener el JWT

            if (jwt != null && jwtUtils.validateJwtToken(jwt)) { // Verifica si el JWT es válido
                String username = jwtUtils.getUserNameFromJwtToken(jwt);// Extrae el nombre de usuario del token

                CustomersDetails customerDetails = (CustomersDetails) customerDetailsService.loadUserByUsername(username); // Carga los detalles del usuario
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(customerDetails, null, customerDetails.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); // Configura los detalles de autenticación
                SecurityContextHolder.getContext().setAuthentication(authentication); // Establece la autenticación en el contexto de seguridad
            }
        } catch (ExpiredJwtException e) {
            // Maneja el caso de un JWT expirado (puedes loguear o enviar una respuesta diferente)
            logger.error("JWT expired: {}");
        } catch (Exception e) {
            // Maneja otras excepciones si es necesario
            logger.error("Cannot set user authentication: {}");
        }

        filterChain.doFilter(request, response); // Continua con la cadena de filtros

    }
}
