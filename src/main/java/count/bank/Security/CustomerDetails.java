package count.bank.Security;

import count.bank.Entity.Customers;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class CustomerDetails implements UserDetails {

    private final Customers CUSTOMERS;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities =new ArrayList<>();
        CUSTOMERS.getRoles().forEach(roles -> {
            authorities.add(
                    new SimpleGrantedAuthority("ROLE_" + roles.getEnumRole().name()));
            roles.getPermissions().forEach(permission -> {
                authorities.add(new SimpleGrantedAuthority(permission.getName()));
            });
        });
        return authorities;
    }

    @Override
    public String getPassword() {
        return CUSTOMERS.getPassword();
    }

    @Override
    public String getUsername() {
        return CUSTOMERS.getEmail();
    }
}
