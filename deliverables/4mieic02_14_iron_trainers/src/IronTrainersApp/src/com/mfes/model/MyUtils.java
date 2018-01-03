package com.mfes.model;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class MyUtils {
  private static final Date currentDate = new Date(3L, 1L, 2018L);
  public static VDMMap daysInMonth =
      MapUtil.map(
          new Maplet(1L, 31L),
          new Maplet(2L, 29L),
          new Maplet(3L, 31L),
          new Maplet(4L, 30L),
          new Maplet(5L, 31L),
          new Maplet(6L, 30L),
          new Maplet(7L, 31L),
          new Maplet(8L, 31L),
          new Maplet(9L, 30L),
          new Maplet(10L, 31L),
          new Maplet(11L, 30L),
          new Maplet(12L, 31L));

  public static Date getCurrentDate() {

    return Utils.copy(MyUtils.currentDate);
  }

  public MyUtils() {}

  public String toString() {

    return "MyUtils{"
        + "currentDate = "
        + Utils.toString(currentDate)
        + ", daysInMonth := "
        + Utils.toString(daysInMonth)
        + "}";
  }

  public static class Date implements Record {
    public Number day;
    public Number month;
    public Number year;

    public Date(final Number _day, final Number _month, final Number _year) {

      day = _day;
      month = _month;
      year = _year;
    }

    public boolean equals(final Object obj) {

      if (!(obj instanceof Date)) {
        return false;
      }

      Date other = ((Date) obj);

      return (Utils.equals(day, other.day))
          && (Utils.equals(month, other.month))
          && (Utils.equals(year, other.year));
    }

    public int hashCode() {

      return Utils.hashCode(day, month, year);
    }

    public Date copy() {

      return new Date(day, month, year);
    }

    public String toString() {

      return "mk_MyUtils`Date" + Utils.formatFields(day, month, year);
    }
  }

  public static Boolean inv_Date(final Date d) {

    Boolean andResult_43 = false;

    if (d.month.longValue() > 0L) {
      Boolean andResult_44 = false;

      if (d.month.longValue() <= 12L) {
        Boolean andResult_45 = false;

        if (d.day.longValue() > 0L) {
          Boolean andResult_46 = false;

          if (d.day.longValue() <= 31L) {
            if (d.year.longValue() > 1900L) {
              andResult_46 = true;
            }
          }

          if (andResult_46) {
            andResult_45 = true;
          }
        }

        if (andResult_45) {
          andResult_44 = true;
        }
      }

      if (andResult_44) {
        andResult_43 = true;
      }
    }

    return andResult_43;
  }
}
