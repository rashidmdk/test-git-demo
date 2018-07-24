package main.setupdata;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.util.CollectionUtils;
import main.vo.User;

public class MainTest implements SetupData{

  List<User> userList = setupUserList();

  public static void main(String args[]) {
    MainTest mainTest = new MainTest();
    List<User> users = mainTest.userList;
    Integer secondId = 2;
    //int user = userList.stream().filter(i -> secondId.equals(i.getId()).count());
    users = users.stream()
        .filter(user -> secondId.equals(user.getId()))
        .collect(Collectors.toList());

    Optional<User> filteredUser = users.stream()
        .filter(user -> secondId.equals(user.getId()))
        .findAny();
    if (!CollectionUtils.isEmpty(users)) {
      System.out.println(users.get(0).getName());
    }
    if (filteredUser.isPresent()) {
      System.out.println(filteredUser.get().getName());
    }

    System.out.println(users.stream().count());


    users = mainTest.userList;
    Comparator<User> comparator = Comparator.comparing(User::getId); //Comparator<User> comparator = Comparator.comparing(User::getId);
    User minUser = users.stream().min(comparator).get();
    User maxUser = users.stream().max(comparator).get();
    System.out.println("minObject = " + minUser.getId());
    System.out.println("maxObject = " + maxUser.getId());


  }




}
