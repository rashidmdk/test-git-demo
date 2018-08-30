package main.rest;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateHashModel;
import java.io.IOException;
import java.util.Arrays;
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
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
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

  @Autowired
  private Configuration freemarkerConfig;

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
    ctx.setVariable("condition", 2);
    ctx.setVariable("listItems", Arrays.asList(new String[]{"one", "two", "three"}));
    return this.htmlTemplateEngine.process("mailTemplate", ctx);
  }

  @RequestMapping(value = "/freeMarkerEmail", method = RequestMethod.GET)
  @ResponseBody
  public String freeMarkerEmail() throws IOException, TemplateException {
    Template t = freemarkerConfig.getTemplate("email-template.ftl");
    Map<String, Object> dataModel = new HashMap<>();
    dataModel.put("mesasge", "this is my custom message");
    dataModel.put("condition", 3);
    dataModel.put("prno", "12345");
    dataModel.put("listItems", Arrays.asList(new String[]{"one", "two", "three"}) );
    ResourceBundle bundle = ResourceBundle.getBundle("emailmessages", new Locale("ja"));
    dataModel.put("bundle",bundle);
    return FreeMarkerTemplateUtils.processTemplateIntoString(t, dataModel);
  }




}
