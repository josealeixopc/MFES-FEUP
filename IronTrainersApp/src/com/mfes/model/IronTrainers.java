package com.mfes.model;

import java.util.*;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class IronTrainers {
  private VDMSet clients;
  private VDMSet trainers;
  private VDMSet allUsers;
  private VDMSet exercises;
  private String user;

  public void cg_init_IronTrainers_1() {

    clients = SetUtil.set();
    trainers = SetUtil.set();
    allUsers = SetUtil.set();
    exercises = SetUtil.set();
    user = "undefined";
  }

  public IronTrainers() {

    cg_init_IronTrainers_1();
  }

  public Boolean login(final String email) {

    if (Utils.equals(user, "undefined")) {
      user = email;
      return true;

    } else {
      return false;
    }
  }

  public void logout() {

    user = "undefined";
  }

  public void updateDesiredWeight(final Number r) {

    final Client cli = getClientByEmail(user);
    if (cli instanceof Client) {
      cli.getMilestone().setDesiredWeight(r);
    }
  }

  public Milestone consultMilestone(final String email) {

    final Client cli = getClientByEmail(email);
    if (cli instanceof Client) {
      return cli.getMilestone();

    } else {
      return new Milestone();
    }
  }

  public VDMSet getAllUsers() {

    return Utils.copy(allUsers);
  }

  public String getUser() {

    return user;
  }

  public Boolean userIsClient(final String cli) {

    if (Utils.equals(cli, "undefined")) {
      return false;

    } else {
      for (Iterator iterator_1 = clients.iterator(); iterator_1.hasNext(); ) {
        Client c = (Client) iterator_1.next();
        if (Utils.equals(cli, c.getEmail())) {
          return true;
        }
      }
      return false;
    }
  }

  public Client getClientByEmail(final String cli) {

    for (Iterator iterator_2 = clients.iterator(); iterator_2.hasNext(); ) {
      Client c = (Client) iterator_2.next();
      if (Utils.equals(cli, c.getEmail())) {
        return c;
      }
    }
    return new Client();
  }

  public Client getCurrentClient() {

    return getClientByEmail(user);
  }

  public VDMSet getClients() {

    return Utils.copy(clients);
  }

  public void addClient(final Client client) {

    VDMSet atomicTmp_1 = SetUtil.union(Utils.copy(clients), SetUtil.set(client));
    VDMSet atomicTmp_2 = SetUtil.union(Utils.copy(allUsers), SetUtil.set(client.getEmail()));
    {
        /* Start of atomic statement */
      clients = Utils.copy(atomicTmp_1);
      allUsers = Utils.copy(atomicTmp_2);
    } /* End of atomic statement */
  }

  public void removeClient(final Client client) {

    VDMSet atomicTmp_3 = SetUtil.diff(Utils.copy(clients), SetUtil.set(client));
    VDMSet atomicTmp_4 = SetUtil.diff(Utils.copy(allUsers), SetUtil.set(client.getEmail()));
    {
        /* Start of atomic statement */
      clients = Utils.copy(atomicTmp_3);
      allUsers = Utils.copy(atomicTmp_4);
    } /* End of atomic statement */
  }

  public VDMSet getTrainers() {

    return Utils.copy(trainers);
  }

  public Boolean userIsTrainer(final String email) {

    if (Utils.equals(user, "undefined")) {
      return false;

    } else {
      for (Iterator iterator_3 = trainers.iterator(); iterator_3.hasNext(); ) {
        Trainer t = (Trainer) iterator_3.next();
        if (Utils.equals(email, t.getEmail())) {
          return true;
        }
      }
      return false;
    }
  }

  public void addTrainer(final Trainer trainer) {

    VDMSet atomicTmp_5 = SetUtil.union(Utils.copy(trainers), SetUtil.set(trainer));
    VDMSet atomicTmp_6 = SetUtil.union(Utils.copy(allUsers), SetUtil.set(trainer.getEmail()));
    {
        /* Start of atomic statement */
      trainers = Utils.copy(atomicTmp_5);
      allUsers = Utils.copy(atomicTmp_6);
    } /* End of atomic statement */
  }

  public void removeTrainer(final Trainer trainer) {

    VDMSet atomicTmp_7 = SetUtil.diff(Utils.copy(trainers), SetUtil.set(trainer));
    VDMSet atomicTmp_8 = SetUtil.diff(Utils.copy(allUsers), SetUtil.set(trainer.getEmail()));
    {
        /* Start of atomic statement */
      trainers = Utils.copy(atomicTmp_7);
      allUsers = Utils.copy(atomicTmp_8);
    } /* End of atomic statement */
  }

  public VDMSet getExercises() {

    return Utils.copy(exercises);
  }

  public Boolean exerciseExists(final String name) {

    for (Iterator iterator_4 = exercises.iterator(); iterator_4.hasNext(); ) {
      Exercise ex = (Exercise) iterator_4.next();
      if (Utils.equals(name, ex.getName())) {
        return true;
      }
    }
    return false;
  }

  public Exercise getExercise(final String name) {

    for (Iterator iterator_5 = exercises.iterator(); iterator_5.hasNext(); ) {
      Exercise ex = (Exercise) iterator_5.next();
      if (Utils.equals(name, ex.getName())) {
        return ex;
      }
    }
    return new Exercise();
  }

  public void addExercise(final Exercise exercise) {

    exercises = SetUtil.union(Utils.copy(exercises), SetUtil.set(exercise));
  }

  public void removeExercise(final Exercise exercise) {

    exercises = SetUtil.diff(Utils.copy(exercises), SetUtil.set(exercise));
  }

  public Series createSeriesLoad(final Number l, final Number r, final Exercise e) {

    return new Series(l, r, e);
  }

  public Series createSeriesTime(final Number r, final Exercise e, final Number t) {

    return new Series(r, e, t);
  }

  public MySet createSet() {

    return new MySet();
  }

  public void addSeriesToSet(final MySet s, final Series srs) {

    s.addSeries(srs);
  }

  public void setDailyPlan(final String email, final Number day, final MySet s) {

    {
      final Client cli = getClientByEmail(email);
      {
        cli.getMilestone().getTrainingPlan().setDailyPlan(day, s);
      }
    }
  }

  public MySet getDailyPlan(final String email, final Number day) {

    {
      final Client cli = getClientByEmail(email);
      {
        return cli.getMilestone().getTrainingPlan().getDailyPlan(day);
      }
    }
  }

  public String toString() {

    return "IronTrainers{"
        + "clients := "
        + Utils.toString(clients)
        + ", trainers := "
        + Utils.toString(trainers)
        + ", allUsers := "
        + Utils.toString(allUsers)
        + ", exercises := "
        + Utils.toString(exercises)
        + ", user := "
        + Utils.toString(user)
        + "}";
  }
}
