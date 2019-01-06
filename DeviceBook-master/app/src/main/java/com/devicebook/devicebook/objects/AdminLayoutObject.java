package com.devicebook.devicebook.objects;

import java.util.ArrayList;

/**
 * Created by Sam on 23/04/2018.
 */

public class AdminLayoutObject extends ArrayList<AdminLayoutObject> {
    private String AdminId;
    private String Username;
    private String Password;


    public AdminLayoutObject()
    {

    }

    public AdminLayoutObject(String adminId, String username, String password) {
        AdminId = adminId;
        Username = username;
        Password = password;
    }

    public String getAdminId() {
        return AdminId;
    }

    public void setAdminId(String adminId) {
        AdminId = adminId;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}


