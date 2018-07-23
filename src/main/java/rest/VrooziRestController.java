package rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vo.User;

@RestController
@RequestMapping ("ws")
public class VrooziRestController {

    @RequestMapping(value = "/getfriendlist", method = RequestMethod.GET)
    public String getFriendList () {
        User innovateFriends = new User();
        innovateFriends.setId(1);
        innovateFriends.setName("Asad Ullah");
        return "asad";
    }

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}
