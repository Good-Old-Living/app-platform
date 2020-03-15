package meru.app;

import java.text.DecimalFormat;

public class Currency {

  private static final DecimalFormat FLOAT_FORMAT = new DecimalFormat("#.##");

  public static final String toString(float value) {
    return FLOAT_FORMAT.format(value);
  }

}
