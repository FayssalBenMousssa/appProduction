package dev.fenix.application;

import dev.fenix.application.app.service.ApplicationData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)
public class IndexController {

  private static final Logger log = LoggerFactory.getLogger(Application.class);
  @Autowired private ApplicationContext appContext;

  @RequestMapping(value = {"/", "home"})
  public ModelAndView edit(
      @RequestParam(value = "id", required = false) Long id, Map<String, Object> model) {
    ApplicationData data = new ApplicationData();
    var mav = new ModelAndView();

    mav.addObject("data", data);

    mav.setViewName("index/index");

    return mav;
  }
}
