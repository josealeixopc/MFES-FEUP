package com.mfes.model;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Exercise {
  private String name;
  private String description;
  private Object bodyPart;

  public void cg_init_Exercise_1(final String n, final String d, final Object b) {

    name = n;
    description = d;
    bodyPart = b;
  }

  public Exercise(final String n, final String d, final Object b) {

    cg_init_Exercise_1(n, d, b);
  }

  public String getName() {

    return name;
  }

  public String getDescription() {

    return description;
  }

  public Object getBodyPart() {

    return bodyPart;
  }

  public Exercise() {}

  public String toString() {

    return "Exercise{"
        + "name := "
        + Utils.toString(name)
        + ", description := "
        + Utils.toString(description)
        + ", bodyPart := "
        + Utils.toString(bodyPart)
        + "}";
  }
}
