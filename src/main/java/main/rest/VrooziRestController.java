package main.rest;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import main.setupdata.SetupData;
import main.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class VrooziRestController implements SetupData {
    @Autowired
    ReloadableResourceBundleMessageSource messageSource;

    @RequestMapping(value = "/helloworld", method = RequestMethod.GET)
    public String helloWorld (@RequestHeader(value="Accept-Language") String locale) {

        return messageSource.getMessage("resource.msg", null, new Locale(locale));
    }

    @RequestMapping(value = "/userlist", produces = "application/json", method = RequestMethod.POST)
    public List<User> getUserList(User user) {

        List<User> users = setupUserList();
        return users.stream()
            .filter(usr -> user.getId().equals(usr.getId()))
            .collect(Collectors.toList());
    }

}
