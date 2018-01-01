package com.mfes.model.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class FQuote {
  private static int hc = 0;
  private static FQuote instance = null;

  public FQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static FQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new FQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof FQuote;
  }

  public String toString() {

    return "<F>";
  }
}
