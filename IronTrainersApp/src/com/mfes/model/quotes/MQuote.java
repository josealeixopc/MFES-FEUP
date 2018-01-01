package com.mfes.model.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class MQuote {
  private static int hc = 0;
  private static MQuote instance = null;

  public MQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static MQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new MQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof MQuote;
  }

  public String toString() {

    return "<M>";
  }
}
