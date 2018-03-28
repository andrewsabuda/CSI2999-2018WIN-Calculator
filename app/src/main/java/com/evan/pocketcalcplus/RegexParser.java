package com.evan.pocketcalcplus;

import android.text.TextUtils;
import android.util.Log;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

import ch.obermuhlner.math.big.BigDecimalMath;

public class RegexParser {
    // Use Greek symbols as an operator instead of sin/cos/tan instead of letters (those are for variables!).
    // The choice to use Greek symbols in particular is arbitrary.
    static final String OPERATOR_SIN = "\u0401";
    static final String OPERATOR_COS = "\u0402";
    static final String OPERATOR_TAN = "\u0403";
    static final String OPERATOR_ARCSIN = "\u0404";
    static final String OPERATOR_ARCCOS = "\u0405";
    static final String OPERATOR_ARCTAN = "\u0406";
    static final String OPERATOR_LOG = "\u0407";
    static final String OPERATOR_PI = "\u03A0"; // The actual Pi symbol.
    static final String OPERATOR_EULER = "\u2107"; // The actual Euler symbol.

    public static MathContext mathContext = new MathContext(20);

    private static BigDecimal radiansToDegrees(BigDecimal radians) {
        return radians.multiply(new BigDecimal("180")).divide(BigDecimalMath.pi(mathContext), BigDecimal.ROUND_HALF_UP);
    }

    private static BigDecimal degreesToRadians(BigDecimal degrees) {
        return degrees.multiply(BigDecimalMath.pi(mathContext)).divide(new BigDecimal("180"), BigDecimal.ROUND_HALF_UP);
    }

    private static BigDecimal performOperation(String operator, BigDecimal operand1, BigDecimal operand2)
        throws ArithmeticException {
        // Two-operator expressions.
        switch(operator) {
            case "+":
                return operand1.add(operand2);
            case "-":
                return operand1.subtract(operand2);
            case "*":
                return operand1.multiply(operand2);
            case "/":
                // ArithmaticException here means invalid divisor (zero).
                return operand1.divide(operand2, BigDecimal.ROUND_HALF_UP);
            case "%":
                return operand1.remainder(operand2);
            case "^":
                // ArithmaticException here means invalid exponent (fractional/massive).
                return BigDecimalMath.pow(operand1, operand2, mathContext);
            default:
                System.out.println("Bad operator: " + operator);
                return new BigDecimal("0");
        }
    }

    private static BigDecimal performOperation(String operator, BigDecimal operand)
            throws ArithmeticException {
        // One-operator expressions.
        switch(operator) {
            case OPERATOR_SIN:
                return BigDecimalMath.sin(operand, mathContext);
            case OPERATOR_COS:
                return BigDecimalMath.cos(operand, mathContext);
            case OPERATOR_TAN:
                return BigDecimalMath.tan(operand, mathContext);
            case OPERATOR_ARCSIN:
                return BigDecimalMath.asin(operand, mathContext);
            case OPERATOR_ARCCOS:
                return BigDecimalMath.acos(operand, mathContext);
            case OPERATOR_ARCTAN:
                return BigDecimalMath.atan(operand, mathContext);
            case OPERATOR_LOG:
                return BigDecimalMath.log(operand, mathContext);
            default:
                System.out.println("Bad operator: " + operator);
                return new BigDecimal("0");
        }
    }

    private static BigDecimal parsePrefixRecursive(ArrayList<String> input)
        throws ArithmeticException {
        // Pop the first element of the array list.
        if (input.size() == 0) return new BigDecimal("0");

        String current = input.remove(0);
        if (current.matches("[.0-9a-zA-Z]+")) {
            // Letter/variable name/numeric/decimal separator
            return new BigDecimal(current);
        } else if (isConstantSymbol(current)) {
            switch(current) {
                case OPERATOR_PI:
                    return BigDecimalMath.pi(mathContext);
                case OPERATOR_EULER:
                    return BigDecimalMath.e(mathContext);
                default:
                    return new BigDecimal("0");
            }
        } else if (isTwoOperandOperator(current)) {
            // If it's a operator, parse the next two numbers and perform the operation.
            BigDecimal operand1 = parsePrefixRecursive(input);
            BigDecimal operand2 = parsePrefixRecursive(input);
            return performOperation(current, operand1, operand2);
        } else if (isOneOperandOperator(current)) {
            // If it's a operator, parse the next number and perform the operation.
            BigDecimal operand = parsePrefixRecursive(input);
            return performOperation(current, operand);
        } else {
            Log.i("RegexParser","No Operation:"+current);
            return new BigDecimal("0");
        }
    }

    static BigDecimal parsePrefix(String input) {
        ArrayList<String> split = new ArrayList<String>(
                Arrays.asList(input.split(" ")));
        split.removeAll(Arrays.asList("", null));
        return parsePrefixRecursive(split);
    }

    static int getTwoOperandPrecedence(String input) {
        switch(input) {
            case "^":
                return 3;
            case "*":
            case "/":
                return 2;
            case "+":
            case "-":
                return 1;
            default:
                return 0;
        }
    }

    static boolean isTwoOperandOperator(String input) {
        String[] operators = {
                "+", "-", "*", "/", "^", "%"
        };
        for (String operator : operators) {
            if (input.contains(operator)) {
                return true;
            }
        }
        // Fallthrough.
        return false;
    }
    private static boolean isOneOperandOperator(String input) {
        String[] operators = {
                OPERATOR_SIN, OPERATOR_COS, OPERATOR_TAN,
                OPERATOR_ARCSIN, OPERATOR_ARCCOS, OPERATOR_ARCTAN,
                OPERATOR_LOG
        };
        for (String operator : operators) {
            if (input.contains(operator)) {
                return true;
            }
        }
        // Fallthrough.
        return false;
    }
    private static boolean isConstantSymbol (String input) {
        String[] symbols = {
                OPERATOR_PI, OPERATOR_EULER
        };
        for (String symbol : symbols) {
            if (input.contains(symbol)) {
                return true;
            }
        }
        // Fallthrough.
        return false;
    }

    static String inputToPrefix(String input){
        ArrayList<String> output = new ArrayList<>();
        Stack<Character> oprStack = new Stack<>();
        StringBuilder numStack = new StringBuilder();

        for (Character ch : input.toCharArray()) {
            if (ch.toString().matches("[.0-9a-zA-Z]") || isConstantSymbol(ch.toString())) {
                // Letter/variable name/numeric/decimal separator
                numStack.append(ch.toString());
                // Don't add a space.
            } else if (ch.toString().matches("[({\\[]")) {
                // Opening Bracket
                oprStack.push(ch);
                output.add(numStack.toString());
                numStack = new StringBuilder();
            } else if (ch.toString().matches("[)}\\]]")) {
                // Closing Bracket
                switch(ch.toString()) {
                    case ")":
                        while (!oprStack.peek().toString().equals("(")) {
                            output.add(0, oprStack.pop().toString());
                        }
                        oprStack.pop();
                        break;
                    case "}":
                        break;
                    case "]":
                        break;
                    default:
                        break;
                }
                output.add(numStack.toString());
                numStack = new StringBuilder();
            } else if (isTwoOperandOperator(ch.toString())) {
                // Two-Operand Operators
                while (!oprStack.isEmpty() && getTwoOperandPrecedence(ch.toString()) <= getTwoOperandPrecedence(oprStack.peek().toString())) {
                    output.add(0, oprStack.pop().toString());
                }
                oprStack.push(ch);
                output.add(numStack.toString());
                numStack = new StringBuilder();
            } else if (isOneOperandOperator(ch.toString())) {
                output.add(0, ch.toString());
                output.add(numStack.toString());
                numStack = new StringBuilder();
            }
        }

        while (!oprStack.isEmpty()) {
            output.add(0, oprStack.pop().toString());
        }
        output.add(numStack.toString());

        return TextUtils.join(" ", output);
    }

    static String parseEquation(String equation) {
        String prefixEquation = inputToPrefix(equation);
        BigDecimal parsedBigDecimal = parsePrefix(prefixEquation);
        return parsedBigDecimal.toPlainString();
    }
}