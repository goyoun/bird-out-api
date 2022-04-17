package api.birdout.utils;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomUtil {

  public String generateAlphanumericString(int size) {
    String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
    String numbers = "0123456789";

    // combine all strings
    String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;

    // create random string builder
    StringBuilder sb = new StringBuilder();

    // create an object of Random class
    Random random = new Random();

    for(int i = 0; i < size; i++) {
      int index = random.nextInt(alphaNumeric.length());
      char randomChar = alphaNumeric.charAt(index);
      sb.append(randomChar);
    }
    return sb.toString();
  }

}
