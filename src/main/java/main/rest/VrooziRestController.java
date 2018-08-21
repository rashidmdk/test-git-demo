package main.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import main.setupdata.SetupData;
import main.util.UTF8Control;
import main.vo.User;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RestController
@RequestMapping(value = "/api")
public class VrooziRestController implements SetupData {
  @Autowired
  ReloadableResourceBundleMessageSource messageSource;

  @Autowired
  private static VelocityEngine velocityEngine;

  @Autowired
  @Qualifier("htmlTemplateEngine")
  private TemplateEngine htmlTemplateEngine;

  @RequestMapping(value = "/helloworld", method = RequestMethod.GET)
  public String helloWorld (@RequestHeader(value="Accept-Language") String locale) {

      /*Map<String, Object> model = new HashMap<String, Object>();
      model.put("title", "title");
      model.put("body", "body");
      String text = VelocityEngineUtils
          .mergeTemplateIntoString(velocityEngine, "email.vm", "UTF-8", model);*/


    /*ResourceBundle resourceBundle = ResourceBundle.getBundle("emailmessages",new Locale(locale));
    System.out.println(resourceBundle.getString("resource.msg"));*/
    ResourceBundle bundle = ResourceBundle.getBundle("emailmessages", new Locale(locale), new UTF8Control());
    return bundle.getString("resource.msg");
  }

  @RequestMapping(value = "/userlist", produces = "application/json", method = RequestMethod.POST)
  public List<User> getUserList(User user) {

      List<User> users = setupUserList();
      return users.stream()
          .filter(usr -> user.getId().equals(usr.getId()))
          .collect(Collectors.toList());
  }

  @RequestMapping(value = "/thymeleaf", method = RequestMethod.GET)
  @ResponseBody
  public String localizedEmail() {
    return build("this is message");
  }

  private String build(String message) {
    final Context ctx = new Context(new Locale("ja"));
    ctx.setVariable("mesasge", message);
    return this.htmlTemplateEngine.process("html/mailTemplate", ctx);
  }

}
