package main.setupdata;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.util.CollectionUtils;
import main.vo.User;

public class MainTest implements SetupData{

  List<User> userList = setupUserList();



  public static void main(String args[]) {


    MainTest mainTest = new MainTest();
    List<User> users = mainTest.userList;

    /*users.stream().map(User::getId).findFirst().ifPresent(System.out::println);
    if(true) return;*/
    Integer secondId = 1;
    //int user = userList.stream().filter(i -> secondId.equals(i.getId()).count());
    users = users.stream()
        .filter(user -> secondId.equals(user.getId()))
        .collect(Collectors.toList());

    Optional<User> filteredUser = users.stream()
        .filter(user -> secondId.equals(user.getId()))
        .findFirst();
    if (!CollectionUtils.isEmpty(users)) {
      System.out.println(users.get(0).getName());
    }
    if (filteredUser.isPresent()) {
      System.out.println(filteredUser.get().getName());
    }

    if (filteredUser.isPresent()) {
      System.out.println(filteredUser.get().getId());
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
