package count.bank.Security;

import count.bank.Entity.Customers;
import count.bank.Exception.EmailNotFoundException;
import count.bank.Repository.RepoCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsService implements UserDetailsService {

    @Autowired
    private RepoCustomer repoCustomer;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Customers customers = repoCustomer.findByEmail(email)
                .orElseThrow(()-> new EmailNotFoundException("El usuario con el email: "+ email+" no fue econtrado"));
        return new CustomersDetails(customers);
    }
}
