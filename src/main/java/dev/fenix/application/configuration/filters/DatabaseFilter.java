package dev.fenix.application.configuration.filters;

import dev.fenix.application.configuration.database.DBContextHolder;
import dev.fenix.application.configuration.database.DBEnum;
import dev.fenix.application.security.model.User;
import dev.fenix.application.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
public class DatabaseFilter implements Filter {

    UserRepository userRepository;


    @Autowired
    public DatabaseFilter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        System.out.println("Requested received and handled before controller : " + getCurrentUser().getLogInEnterprise().getEnterpriseDatabase());
        DBEnum selectedDb = getCurrentUser().getLogInEnterprise().getEnterpriseDatabase();
         DBContextHolder.setCurrentDb(selectedDb);
        chain.doFilter(req, res);
       // System.out.println("Code executed after controller");
    }

    private User getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        System.out.println("userDetails.getUsername() " +  userDetails.getUsername());
        User user = userRepository.findOneByUserName(username);
        return user;
    }
}
