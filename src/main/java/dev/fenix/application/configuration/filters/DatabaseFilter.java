package dev.fenix.application.configuration.filters;

import dev.fenix.application.api.security.util.JwtUtil;
import dev.fenix.application.configuration.database.DBContextHolder;
import dev.fenix.application.configuration.database.DBEnum;
import dev.fenix.application.security.model.User;
import dev.fenix.application.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

@Component
@Order(2)
public class DatabaseFilter implements Filter {

    UserRepository userRepository;
    @Autowired private JwtUtil jwtUtil;


    @Autowired
    public DatabaseFilter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletRequest httpRequest = request;
        Enumeration<String> headerNames = httpRequest.getHeaderNames();
        httpRequest.getHeader("authorization");
        String authorizationHeader = httpRequest.getHeader("authorization");



        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
           String  jwt = authorizationHeader.substring(7);
            jwtUtil = new JwtUtil();
           String username = jwtUtil.extractUsername(jwt);
            // System.out.println("username : " + username);
            User user = userRepository.findOneByUserName(username);

          //System.out.println("YOU ARE " + user.getPerson().getFullName() + " You select " + user.getLogInEnterprise().getSocialReason());
         // System.out.println("Requested received and handled before controller : " + user.getLogInEnterprise().getEnterpriseDatabase());

            //switch (user.getLogInEnterprise().getId())

            DBEnum selectedDb = DBEnum.MAIN;
            if (user.getLogInEnterprise().getId() == 1) {
                // code block
                  selectedDb = DBEnum.CANELIA;
            } else if (user.getLogInEnterprise().getId() == 2) {
                // code block


                selectedDb = DBEnum.FRAISCAPRICES;
            } else if (user.getLogInEnterprise().getId() == 3) {
                // code block
                selectedDb = DBEnum.OVOFRAIS;
            }



            DBContextHolder.setCurrentDb(selectedDb);
            chain.doFilter(req, res);
        }




    }


}
