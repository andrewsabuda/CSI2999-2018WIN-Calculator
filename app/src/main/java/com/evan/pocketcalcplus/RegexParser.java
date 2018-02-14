package com.evan.pocketcalcplus;

import java.lang.Math;

import java.util.Arrays;
import java.util.ArrayList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexParser {

    public static double performOperation(String operator, double operand1, double operand2) {
        switch(operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if(operand2 == 0){
                    System.out.println("Error");
                }
                else {
                    return operand1 / operand2;
                }
            case "%":
                return operand1 % operand2;
            case "^":
                return Math.pow(operand1, operand2);
            default:
            System.out.println("Bad operator:"+operator);
            return 0;
        }
    }

    private static double parsePrefixRecursive(ArrayList<String> input) {
        // Pop the first element of the array list.
        String current = input.remove(0);

        // Check for if it's a number or operator.
        Pattern patternOperator = Pattern.compile("[-+*/^%]");
        Matcher matchOperator = patternOperator.matcher(current);
        Pattern patternNumber = Pattern.compile("-?([.0-9])+");
        Matcher matchNumber = patternNumber.matcher(current);

        if(matchNumber.matches()) {
            // If it's a number, parse it.
            return Double.parseDouble(current);
        } else if (matchOperator.matches()) {
            // If it's a operator, parse the next two numbers and perform the operation.
            double operand1 = parsePrefixRecursive(input);
            double operand2 = parsePrefixRecursive(input);
            return performOperation(current, operand1, operand2);
        } else {
            System.out.println("No Operation:"+current);
            return 0;
        }
    }

    public static double parsePrefix(String input) {
        ArrayList<String> split = new ArrayList<String>(
            Arrays.asList(input.split(" ")));
        split.removeAll(Arrays.asList("", null));
        return parsePrefixRecursive(split);
    }

    public static String inputToPrefix(String input){
        Pattern pattern = Pattern.compile("(?:([.0-9]+) ?([-+*/%^])?)");
        Matcher match = pattern.matcher(input);

        ArrayList<String> output = new ArrayList<String>();

        while (match.find()) {
            if (match.group(2) == null) {
                output.add(match.group(1));
                break;
            } else {
                output.add(0, match.group(2));
                output.add(match.group(1));
            }
        }

        return stringJoiner(' ', output.toArray(new String[output.size()]));
        //return String.join(" ", output.toArray(new String[output.size()]));
    }

    public static String stringJoiner(char delimiter, String[] output) {
        String joinedString = new String();
        for(String s: output) {
            joinedString = joinedString + s + delimiter;
        }

        return joinedString.substring(0, joinedString.length() - 1);
    }

    public static void main(String[] args) {
        // + - * / ^ %
        // No order of operations or parenthesis yet
        // Once parenthesis is done, order of operations can be done by inserting parens
        final String INPUT = "5*5+2/8-3^2%2";

        String prefixInput = inputToPrefix(INPUT);
        System.out.println(INPUT + ": " + prefixInput);
        System.out.println(INPUT + " = " + parsePrefix(prefixInput));
    }
}