package com.mfes.model;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class TrainingPlan {
  private VDMMap plan;

  public void cg_init_TrainingPlan_1() {

    plan = MapUtil.map();
  }

  public TrainingPlan() {

    cg_init_TrainingPlan_1();
  }

  public VDMMap getPlan() {

    return Utils.copy(plan);
  }

  public MySet getDailyPlan(final Number day) {

    return ((MySet) Utils.get(plan, day));
  }

  public void setDailyPlan(final Number day, final MySet p) {

    Utils.mapSeqUpdate(plan, day, p);
  }

  public String toString() {

    return "TrainingPlan{" + "plan := " + Utils.toString(plan) + "}";
  }
}
