package com.boonya.spring.mvc.velocity.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SpringVelocityController  {
  
  @RequestMapping("/velocity")
  public ModelAndView velocityView(HttpServletRequest req, HttpServletResponse resp) throws Exception {
      System.out.println("------------ enter SpringVelocityController");

      List<String> menus = new ArrayList<String>();
      menus.add("HOME");
      menus.add("NEWS");
      menus.add("CONTACT");
      menus.add("ABOUT");
      return new ModelAndView("velocity", "menus", menus);// velocity:即SpringVelocity/src/main/webapp/WEB-INF/templates/velocity.vm
  }

}
