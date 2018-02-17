package com.evan.pocketcalcplus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexParser {
    private static BigDecimal performOperation(String operator, BigDecimal operand1, BigDecimal operand2)
        throws ArithmeticException {
        switch(operator) {
            case "+":
                return operand1.add(operand2);
            case "-":
                return operand1.subtract(operand2);
            case "*":
                return operand1.multiply(operand2);
            case "/":
                // ArithmaticException here means invalid divisor (zero).
                return operand1.divide(operand2);
            case "%":
                return operand1.remainder(operand2);
            case "^":
                // ArithmaticException here means invalid exponent (fractional/massive).
                return operand1.pow(operand2.intValueExact());
            default:
                System.out.println("Bad operator:"+operator);
                return new BigDecimal("0");
        }
    }

    private static BigDecimal parsePrefixRecursive(ArrayList<String> input)
        throws ArithmeticException {
        // Pop the first element of the array list.
        String current = input.remove(0);

        // Check for if it's a number or operator.
        Pattern patternOperator = Pattern.compile("[-+*/^%]");
        Matcher matchOperator = patternOperator.matcher(current);
        Pattern patternNumber = Pattern.compile("-?([.0-9])+");
        Matcher matchNumber = patternNumber.matcher(current);

        if(matchNumber.matches()) {
            // If it's a number, parse it.
            return new BigDecimal(current);
        } else if (matchOperator.matches()) {
            // If it's a operator, parse the next two numbers and perform the operation.
            BigDecimal operand1 = parsePrefixRecursive(input);
            BigDecimal operand2 = parsePrefixRecursive(input);
            return performOperation(current, operand1, operand2);
        } else {
            System.out.println("No Operation:"+current);
            return new BigDecimal("0");
        }
    }

    private static BigDecimal parsePrefix(String input) {
        ArrayList<String> split = new ArrayList<String>(
                Arrays.asList(input.split(" ")));
        split.removeAll(Arrays.asList("", null));
        return parsePrefixRecursive(split);
    }

    private static String inputToPrefix(String input){
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

        return joinString(' ', output.toArray(new String[output.size()]));
    }

    private static String joinString(char delimiter, String[] output) {
        String joinedString = new String();
        for(String s: output) {
            joinedString = joinedString + s + delimiter;
        }
        return joinedString.substring(0, joinedString.length() - 1);
    }

    public static String parseEquation(String equation) {
        String prefixEquation = inputToPrefix(equation);
        BigDecimal parsedBigDecimal = parsePrefix(prefixEquation);
        return parsedBigDecimal.toPlainString();
    }
}