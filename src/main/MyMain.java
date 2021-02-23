package main;

import infix.postfix.InfixToPostfix;

public class MyMain {
    public static void main(String[] args) {
        InfixToPostfix converter =new InfixToPostfix();
        String infix = "A+(B*C-(D/E^F)*G)*H";
        System.out.println(converter.infixToPostfix(infix));
    }
}
