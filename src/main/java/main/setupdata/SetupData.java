package main.setupdata;

import java.util.ArrayList;
import java.util.List;
import main.vo.User;

public interface SetupData {


  default List<User> setupUserList() {
    List<User> users = new ArrayList<>();

    User user = new User();
    user.setName("n1");
    user.setId(1);
    users.add(user);

    user = new User();
    user.setName("n2");
    user.setId(1);
    users.add(user);

    user = new User();
    user.setName("n2");
    user.setId(2);
    users.add(user);

    user = new User();
    user.setName("n3");
    user.setId(3);
    users.add(user);
    return users;
  }
}
