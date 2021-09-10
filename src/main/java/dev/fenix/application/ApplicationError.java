package dev.fenix.application;

/// https://javatechonline.com/how-to-handle-exceptions-errors-in-spring-boot/

import dev.fenix.application.app.model.Exception;
import dev.fenix.application.app.repository.AppExceptionRepository;
import dev.fenix.application.security.model.User;
import dev.fenix.application.security.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class ApplicationError implements ErrorController {

  @Autowired private ErrorAttributes errorAttributes;
  @Autowired private AppExceptionRepository exceptionRepository;
  @Autowired private UserRepository userRepository;

  private static final Logger log = LoggerFactory.getLogger(ErrorController.class);

  @RequestMapping("/error")
  public @ResponseBody Object handleError(HttpServletRequest request) {
    ServletWebRequest webRequest = new ServletWebRequest(request);
    log.trace("ApplicationError.handleError method accessed");
    @SuppressWarnings("deprecation")
    Map<String, Object> errors = errorAttributes.getErrorAttributes(webRequest, true);
    String username = null;
    try {
      Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
      username = loggedInUser.getName();
    } catch (java.lang.Exception e) {
      e.printStackTrace();
    }

    User user = userRepository.findOneByUserName(username);
    log.trace("UserName : " +  username   );

    Exception exception = new Exception(errors, user);
    exception = exceptionRepository.save(exception);
    String[] paths = exception.getPath().split("\\/", -1);
    if (!paths[1].equals("api")) {
      ModelAndView modelAndView = new ModelAndView();
      modelAndView.addObject("exception", exception);
      if (exception.getStatus() != null) {
        Integer statusCode = Integer.valueOf(exception.getStatus());
        log.trace("StatusCode : " + statusCode  );
        if (statusCode == HttpStatus.NOT_FOUND.value()) {
          modelAndView.addObject("status", HttpStatus.NOT_FOUND);
          modelAndView.setViewName("errors/not_found");
          return modelAndView;
        } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
          modelAndView.addObject("status", HttpStatus.INTERNAL_SERVER_ERROR);
          modelAndView.setViewName("errors/not_found");
          return modelAndView;
        }
      }
    }

    return errors;
  }

  @Override
  public String getErrorPath() {
    return null;
  }
}
