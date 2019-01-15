package com.arcia;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Fib
 */
public class Fib {
  private static HashMap<Integer, Integer> memoized = new HashMap<Integer, Integer>();

  public static void main(String[] args) {
    FibArgs fibArgs = new FibArgs(args);
    run(fibArgs);
  }

  public static void run(FibArgs args) {
    memoized.put(1, 1);
    memoized.put(2, 1);
    if (args.shouldProfile())
      profile(args.num, args.profileCount);
    else
      System.out.println(args.num > 46 ? bigFibonacci(args.num) : fibonacci(args.num));
  }

  private static <T> String profileString(int num, T result, long startTime, long endTime, int iterations) {
    return String.format("fibonacci(%s) == %s time taken: %s for %s iterations", num, result, endTime - startTime,
        iterations);
  }

  /**
   * Tests timing and results of fibonacci and bigFibonacci
   * 
   * @param num
   */
  private static void profile(int num, int iterations) {
    long startTime = System.nanoTime();
    int result = 0;
    for (int i = 0; i < iterations; i++) {
      result = fibonacci(num);
    }
    long endTime = System.nanoTime();
    System.out.println(profileString(num, result, startTime, endTime, iterations));
    long startTime2 = System.nanoTime();
    var result2 = BigInteger.ZERO;
    for (int i = 0; i < iterations; i++) {
      result2 = bigFibonacci(num);
    }
    long endTime2 = System.nanoTime();
    System.out.println(profileString(num, result2, startTime2, endTime2, iterations));
  }

  /**
   * Only valid for n < 0
   * 
   * @param n < 0
   * @return negative n indexes of the fibonacci sequence
   */
  private static int negFib(int n) {
    return (int) Math.pow(-1, n + 1) * fibonacci(Math.abs(n));
  }

  /**
   * Only valid for 46 > n > 0
   * 
   * @param n positive integer
   * @return positive indexes of hte fibonnaci sequence
   */
  private static int posFib(int n) {
    return fibonacci(n - 1) + fibonacci(n - 2);
  }

  /**
   * Inaccurate when n > 46
   * 
   * @param n < 46
   * @return fibonacci value at index n
   */
  public static int fibonacci(int n) {
    if (memoized.containsKey(n))
      return memoized.get(n);
    int answer = n < 0 ? negFib(n) : posFib(n);
    memoized.put(n, answer);
    return answer;
  }

  /**
   * Handles Larger n values than the fibonacci method above, but with slower
   * performance at low n values
   * 
   * @param n
   * @return finbonacci value at index n
   */
  public static BigInteger bigFibonacci(int n) {
    if (n < 2 && n > 0)
      return BigInteger.ONE;
    BigInteger[] previous = new BigInteger[] { BigInteger.ONE, BigInteger.ONE };
    BigInteger current = null;
    for (int i = 2; i < n; i++) {
      current = previous[0].add(previous[1]);
      previous[1] = previous[0];
      previous[0] = current;
    }
    return current;
  }
}
