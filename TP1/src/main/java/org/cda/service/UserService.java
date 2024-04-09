package org.cda.service;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private List<String[]> userList;

    private final String[] colum = {"Nom","Email","Genre"};

    private static UserService instance = null;

    private UserService() {

        userList = new ArrayList<>();

        save("test","testmail","test");
    }

    public void save(String name, String email, String gender) {
        String[] newUser = {name,email,gender};
        userList.add(newUser);
    }

    public String[][] getUsers(){
        return userList.toArray(new String[0][]);
    }


    public static UserService getUserService(){
        if ( instance == null ){
            instance = new UserService();
        }
        return instance;
    }

}
