package com.mfes.model.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class ChestQuote {
  private static int hc = 0;
  private static ChestQuote instance = null;

  public ChestQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static ChestQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new ChestQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof ChestQuote;
  }

  public String toString() {

    return "<Chest>";
  }
}
