package com.mfes.model;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class User {
  private String email;
  private String password;
  private String name;
  private Number mobile;

  public void cg_init_User_2(final String e, final String p, final String n, final Number m) {

    email = e;
    password = p;
    name = n;
    mobile = m;
  }

  public void cg_init_User_1(final String e, final String p, final String n) {

    email = e;
    password = p;
    name = n;
    mobile = null;
  }

  public User(final String e, final String p, final String n) {

    cg_init_User_1(e, p, n);
  }

  public User(final String e, final String p, final String n, final Number m) {

    cg_init_User_2(e, p, n, m);
  }

  public String getEmail() {

    return email;
  }

  public String getPassword() {

    return password;
  }

  public String getName() {

    return name;
  }

  public Number getMobileNumber() {

    return mobile;
  }

  public User() {}

  public String toString() {

    return "User{"
        + "email := "
        + Utils.toString(email)
        + ", password := "
        + Utils.toString(password)
        + ", name := "
        + Utils.toString(name)
        + ", mobile := "
        + Utils.toString(mobile)
        + "}";
  }
}
