package com.arcia;

/**
 * Argument container
 */
public class FibArgs {
  public int num = 12;
  public int profileCount = 0;

  public FibArgs(String[] args) {
    for (String arg : args) {
      if (arg.contains("--profile=") || arg.contains("-p=")) {
        profileCount = Integer.parseInt(arg.split("=")[1]);
        continue;
      }
      if (arg.equals("--profile") || arg.equals("-p")) {
        profileCount = 1;
        continue;
      }

      try {
        num = Integer.parseInt(arg);
        continue;
      } catch (NumberFormatException e) {

      }
    }

  }

  public boolean shouldProfile() {
    return profileCount > 0;
  }
}