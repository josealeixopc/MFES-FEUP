package com.mfes.model.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class LegQuote {
  private static int hc = 0;
  private static LegQuote instance = null;

  public LegQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static LegQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new LegQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof LegQuote;
  }

  public String toString() {

    return "<Leg>";
  }
}
