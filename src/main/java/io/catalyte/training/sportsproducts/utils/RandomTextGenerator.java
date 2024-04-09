package io.catalyte.training.sportsproducts.utils;

import java.util.Random;

public class RandomTextGenerator {

  private static final String WORDS = "Lorem ipsum dolor sit amet consectetur adipiscing elit sed do eiusmod tempor incididunt "
      + "ut labore et dolore magna aliqua Ut enim ad minim veniam quis nostrud exercitation ullamco laboris nisi ut aliquip ex "
      + "ea commodo consequat Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur "
      + "Excepteur sint occaecat cupidatat non proident sunt in culpa qui officia deserunt mollit anim id est laborum";

  private static final String[] WORDS_ARRAY = WORDS.split(" ");

  public static String generateRandomText() {
    Random random = new Random();
    StringBuilder textBuilder = new StringBuilder();
    int targetLength = random.nextInt(55) + 200;
    int currentLength = 0;
    while (currentLength < targetLength) {
      int randomIndex = random.nextInt(WORDS_ARRAY.length);
      String word = WORDS_ARRAY[randomIndex];
      if (currentLength + word.length() <= targetLength) {
        textBuilder.append(word).append(" ");
        currentLength += word.length() + 1;
      } else {
        break;
      }
    }
// Capitalize the 1st letter of the 1st word
    if (textBuilder.length() > 0) {
      textBuilder.setCharAt(0, Character.toUpperCase(textBuilder.charAt(0)));
    }
    return textBuilder.toString().trim();
  }
}