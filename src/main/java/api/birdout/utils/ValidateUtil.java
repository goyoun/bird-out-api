package api.birdout.utils;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import api.birdout.common.consts.Verify;

@Component
public class ValidateUtil {
  
  // true: only number, false: not only number
  public boolean checkOnlyNum(String target) {
    String onlyNumberPattern = Verify.ONLY_NUM_REG;
    return Pattern.matches(onlyNumberPattern, target);
  }

}
