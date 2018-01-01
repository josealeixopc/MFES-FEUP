package com.mfes.model;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class MySet {
  private VDMSeq series;

  public void cg_init_MySet_2() {

    series = SeqUtil.seq();
  }

  public void cg_init_MySet_1(final VDMSeq s) {

    series = Utils.copy(s);
  }

  public MySet(final VDMSeq s) {

    cg_init_MySet_1(Utils.copy(s));
  }

  public MySet() {

    cg_init_MySet_2();
  }

  public VDMSeq getSeries() {

    return Utils.copy(series);
  }

  private Number getIndex(final Series s, final VDMSeq list, final Number index) {

    if (Utils.equals(list.size(), 0L)) {
      return -1L;

    } else {
      if (Utils.equals(s, ((Series) list.get(0)))) {
        return index;

      } else {
        return getIndex(s, SeqUtil.tail(Utils.copy(list)), index.longValue() + 1L);
      }
    }
  }

  public Number getIndex(final Series s) {

    return getIndex(s, Utils.copy(series), 0L);
  }

  public void addSeries(final Series s) {

    if (Utils.equals(getIndex(s), -1L)) {
      series = SeqUtil.conc(Utils.copy(series), SeqUtil.seq(s));
    }
  }

  public String toString() {

    return "MySet{" + "series := " + Utils.toString(series) + "}";
  }
}
