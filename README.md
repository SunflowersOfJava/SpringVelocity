# SpringVelocity

Here is a very simple example.

#@Controller
@Controller
public class SpringVelocityController  {
  
  @RequestMapping("/velocity")
  public ModelAndView velocityView(HttpServletRequest req, HttpServletResponse resp) throws Exception {
      System.out.println("------------ enter SpringVelocityController");

      List<String> list = new ArrayList<String>();
      list.add("a");
      list.add("b");
      list.add("c");
      return new ModelAndView("velocity", "list", list);// velocity:即SpringVelocity/src/main/webapp/WEB-INF/templates/velocity.vm
  }

}
# The vm Templates

The vm templates are in /SpringVelocity/src/main/webapp/WEB-INF/templates dir.Here only contains a 'velocity.vm' file.

##content in velocity.vm

<ul>
#foreach( $entry in $list )
    <li>$entry</li>
#end
</ul>

