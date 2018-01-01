package com.mfes.model.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class ArmQuote {
  private static int hc = 0;
  private static ArmQuote instance = null;

  public ArmQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static ArmQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new ArmQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof ArmQuote;
  }

  public String toString() {

    return "<Arm>";
  }
}
