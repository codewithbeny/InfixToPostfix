package infix.postfix;

import java.util.Stack;

public class InfixToPostfix {
    public String infixToPostfix(String infixString){
        String postfix = "";
        Stack<Character> stack = new Stack<>();
        // Step 1
        stack.push('(');
        infixString=infixString.concat(String.valueOf(')'));
        // Step 2
        for (int i = 0; i < infixString.length(); i++) {
            char character = infixString.charAt(i);
            // Step 3
            if(Character.isAlphabetic(character) || Character.isDigit(character)){
                postfix = postfix.concat(String.valueOf(character));
            }
            // Step 4
            else if(character=='('){
                stack.push(character);
            }
            // Step 5
            else if(isOperator(character)){
                while (!stack.empty()){
                    if(precedence(stack.peek())>=precedence(character)){
                        postfix = postfix.concat(String.valueOf(stack.pop()));

                    }else{
                        stack.push(character);
                        break;
                    }
                }
            }
            // Step 6
            else if(character==')'){
                while (!stack.empty()){
                    if(stack.peek()!='('){
                        postfix=postfix.concat(String.valueOf(stack.pop()));
                    }else{
                        stack.pop();
                        break;
                    }
                }
            }
        }
        return postfix;
    }
    private boolean isOperator(char character){
        boolean response = false;
        switch (character){
            case '^':
            case '/':
            case '+':
            case '-':
            case '*':
                response=true;
        }
        return response;
    }
    private int precedence(char operator){
        int response = 0;
        switch (operator){
            case '^':
                response = 3;
                break;
            case '/':
            case '*':
                response = 2;
                break;
            case '-':
            case '+':
                response = 1;

        }
        return response;
    }
    //  Postfix Evaluation
    public double postfixEvaluation(String postfix){
        double response = 0;
        Stack<Double> stack=new Stack<>();
        // Step 1
        postfix=postfix.concat(String.valueOf(')'));
        // Step 2
        for (int i = 0; i < postfix.length(); i++) {
            char character=postfix.charAt(i);
            if(Character.isDigit(character)){
                stack.push((double)character);
            }else if(isOperator(character)){
                stack.push(evaluate(stack.pop(),stack.pop(),character));
            }

        }
        response=stack.peek();
        return response;
    }

    private  double evaluate(double first,double second, char operator){
        double response = 0;
        switch (operator){
            case '^':
                response=Math.pow(first,second);
                break;
            case '/':
                response = (double) first / second;
                break;
            case '*':
                response = (double) first * second;
                break;
            case '+':
                response = (double) first + second;
                break;
            case '-':
                response = (double) first - second;

           }


        return response;
    }
}
