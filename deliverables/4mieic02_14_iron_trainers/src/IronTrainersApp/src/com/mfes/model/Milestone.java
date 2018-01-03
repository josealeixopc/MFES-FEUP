package com.mfes.model;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Milestone {
  private Number desiredWeight;
  private TrainingPlan trainingPlan;

  public void cg_init_Milestone_1(final Number d) {

    desiredWeight = d;
    trainingPlan = new TrainingPlan();
  }

  public Milestone(final Number d) {

    cg_init_Milestone_1(d);
  }

  public Number getDesiredWeight() {

    return desiredWeight;
  }

  public TrainingPlan getTrainingPlan() {

    return trainingPlan;
  }

  public void setDesiredWeight(final Number w) {

    desiredWeight = w;
  }

  public Milestone() {}

  public String toString() {

    return "Milestone{"
        + "desiredWeight := "
        + Utils.toString(desiredWeight)
        + ", trainingPlan := "
        + Utils.toString(trainingPlan)
        + "}";
  }
}
