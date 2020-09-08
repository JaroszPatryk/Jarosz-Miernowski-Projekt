package io.mbab.sda.groupproject.menu;

import java.util.Scanner;

/* Scanner jest klasa finalną, nie można zamockować, wraper pozwoli na mockowanie na potrzeby testów*/
public class CustomScanner {

  private final Scanner scanner = new Scanner(System.in);

  public String nextLine() {
    return scanner.nextLine();
  }

  public double nextDouble() {

    String userInputString = scanner.nextLine();
    double userInputDouble = 0d;
    try{
    userInputDouble = Double.parseDouble(userInputString);
    }catch(Exception ex){
      System.out.println("Podano niewłaściwą wartość!. Spróbuj jeszcze raz.");
      nextDouble();
    }
    return userInputDouble;
  }
}
