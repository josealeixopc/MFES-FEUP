package com.mfes.model;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Trainer extends User {
  public void cg_init_Trainer_1(final String e, final String p, final String n) {

    cg_init_User_1(e, p, n);
  }

  public Trainer(final String e, final String p, final String n) {

    cg_init_Trainer_1(e, p, n);
  }

  public Trainer() {}

  public String toString() {

    return "Trainer{}";
  }
}
