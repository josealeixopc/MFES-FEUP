package com.mfes.model;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Client extends User {
  private Object gender;
  private Number weight;
  private Number height;
  private MyUtils.Date birthDate;
  private Milestone milestone;

  public void cg_init_Client_1(
      final String e,
      final String p,
      final String n,
      final Object s,
      final Number w,
      final Number h,
      final MyUtils.Date d) {

    gender = s;
    weight = w;
    height = h;
    birthDate = Utils.copy(d);
    milestone = new Milestone(0.0);
    cg_init_User_1(e, p, n);
  }

  public Client(
      final String e,
      final String p,
      final String n,
      final Object s,
      final Number w,
      final Number h,
      final MyUtils.Date d) {

    cg_init_Client_1(e, p, n, s, w, h, Utils.copy(d));
  }

  public Object getGender() {

    return gender;
  }

  public Number getWeight() {

    return weight;
  }

  public Number getHeight() {

    return height;
  }

  public void setWeight(final Number w) {

    weight = w;
  }

  public void setHeight(final Number h) {

    height = h;
  }

  public Number getAge() {

    return calculateAge(Utils.copy(birthDate), MyUtils.getCurrentDate());
  }

  public Milestone getMilestone() {

    return milestone;
  }

  public Client() {}

  public static Number calculateAge(final MyUtils.Date d, final MyUtils.Date c) {

    return c.year.longValue() - d.year.longValue();
  }

  public String toString() {

    return "Client{"
        + "gender := "
        + Utils.toString(gender)
        + ", weight := "
        + Utils.toString(weight)
        + ", height := "
        + Utils.toString(height)
        + ", birthDate := "
        + Utils.toString(birthDate)
        + ", milestone := "
        + Utils.toString(milestone)
        + "}";
  }
}
