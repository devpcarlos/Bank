package count.bank.Security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import count.bank.Entity.Customers;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class CustomersDetails implements UserDetails {
    private Long id;
    private String email;
    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;


    // Constructor que acepta directamente la entidad Customers
    public CustomersDetails(Customers customers) { // Cambié CustomerDetails por CustomersDetails
        this.id = customers.getId();
        this.email = customers.getEmail();
        this.password = customers.getPassword();

        // Convertimos roles y permisos en GrantedAuthority
        this.authorities = customers.getRoles().stream()
                .flatMap(role -> {
                    // Convertimos roles y permisos en autoridades
                    List<GrantedAuthority> rolePermissions = role.getPermissions().stream()
                            .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                            .collect(Collectors.toList());
                    rolePermissions.add(new SimpleGrantedAuthority("ROLE_" + role.getEnumRole().name()));
                    return rolePermissions.stream();
                })
                .collect(Collectors.toList());
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return  email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CustomersDetails customer = (CustomersDetails) o;
        return id.equals(customer.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
