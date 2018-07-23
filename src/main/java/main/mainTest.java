package main;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.util.CollectionUtils;
import setupdata.SetupData;
import vo.User;

public class mainTest {

  public static void main(String args[]) {

    List<User> userList = SetupData.setupUsers();
    Integer secondId = 2;
    //int user = userList.stream().filter(i -> secondId.equals(i.getId()).count());
    userList = userList.stream()
        .filter(user -> secondId.equals(user.getId()))
        .collect(Collectors.toList());

    Optional<User> filteredUser = userList.stream()
        .filter(user -> secondId.equals(user.getId()))
        .findAny();
    if (!CollectionUtils.isEmpty(userList)) {
      System.out.println(userList.get(0).getName());
    }
    if (filteredUser.isPresent()) {
      System.out.println(filteredUser.get().getName());
    }

    System.out.println(userList.stream().count());


    userList = SetupData.setupUsers();
    Comparator<User> comparator = Comparator.comparing(User::getId); //Comparator<User> comparator = Comparator.comparing(User::getId);
    User minUser = userList.stream().min(comparator).get();
    User maxUser = userList.stream().max(comparator).get();
    System.out.println("minObject = " + minUser.getId());
    System.out.println("maxObject = " + maxUser.getId());


  }




}
