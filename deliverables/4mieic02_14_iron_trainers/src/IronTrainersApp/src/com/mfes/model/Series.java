package com.mfes.model;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Series {
  public static final Number oneKgToLbs = 2.20462262;
  private Number load;
  private Number time;
  private Number repetitions;
  private Exercise exercise;

  public void cg_init_Series_2(final Number r, final Exercise e, final Number t) {

    repetitions = r;
    exercise = e;
    time = t;
    load = null;
  }

  public void cg_init_Series_1(final Number l, final Number r, final Exercise e) {

    load = l;
    repetitions = r;
    exercise = e;
    time = null;
  }

  public Series(final Number l, final Number r, final Exercise e) {

    cg_init_Series_1(l, r, e);
  }

  public Series(final Number r, final Exercise e, final Number t) {

    cg_init_Series_2(r, e, t);
  }

  public Number getLoad() {

    return load;
  }

  public Number getTime() {

    return time;
  }

  public Number getNumberOfRepetitions() {

    return repetitions;
  }

  public Exercise getExercise() {

    return exercise;
  }

  public Series() {}

  public static Number convertFromKgToLbs(final Number kgWeight) {

    return kgWeight.doubleValue() * Series.oneKgToLbs.doubleValue();
  }

  public String toString() {

    return "Series{"
        + "oneKgToLbs = "
        + Utils.toString(oneKgToLbs)
        + ", load := "
        + Utils.toString(load)
        + ", time := "
        + Utils.toString(time)
        + ", repetitions := "
        + Utils.toString(repetitions)
        + ", exercise := "
        + Utils.toString(exercise)
        + "}";
  }
}
