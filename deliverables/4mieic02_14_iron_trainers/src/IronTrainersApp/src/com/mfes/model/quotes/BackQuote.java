package com.mfes.model.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class BackQuote {
  private static int hc = 0;
  private static BackQuote instance = null;

  public BackQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static BackQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new BackQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof BackQuote;
  }

  public String toString() {

    return "<Back>";
  }
}
