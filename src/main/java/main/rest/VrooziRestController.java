package main.rest;

import java.util.List;
import java.util.stream.Collectors;
import main.setupdata.SetupData;
import main.vo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VrooziRestController implements SetupData {

    @RequestMapping(value = "/helloworld", method = RequestMethod.GET)
    public String helloWorld () {

        return "hello world!";
    }

    @RequestMapping(value = "/userlist", produces = "application/json", method = RequestMethod.POST)
    public List<User> getUserList(User user) {

        List<User> users = setupUserList();
        return users.stream()
            .filter(usr -> user.getId().equals(usr.getId()))
            .collect(Collectors.toList());
    }

}
