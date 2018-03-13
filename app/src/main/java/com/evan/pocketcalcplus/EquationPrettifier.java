package com.evan.pocketcalcplus;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Locale;

public class EquationPrettifier {
    private static String convertStringToScientific(String input) {
        return String.format(Locale.getDefault(), "%3.4G", new BigDecimal(input));
    }

    private static String concatenateBuffer(LinkedList<Character> input) {
        String output = "";
        while (!(input.size() == 0)) {
            output = output + input.remove();
        }
        return output;
    }

    public static String prettifyInput(String input) {
        StringBuilder output = new StringBuilder();
        LinkedList<Character> buffer = new LinkedList<>();

        for (Character current : input.toCharArray()) {
            switch (current.toString()) {
                case "0":
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                case ".":
                    // Part of a number, add onto stack.
                    buffer.add(current);
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                case "%":
                    // Operators, previous number is done.
                    if (!buffer.isEmpty()) {
                        output.append(convertStringToScientific(concatenateBuffer(buffer)));
                    }
                    output.append(' ');
                    output.append(current);
                    output.append(' ');
                    break;
                case RegexParser.OPERATOR_SIN:
                    output.append("sin(");
                    if (!buffer.isEmpty()) {
                        output.append(convertStringToScientific(concatenateBuffer(buffer)));
                    }
                    output.append(')');
                    break;
                case RegexParser.OPERATOR_COS:
                    output.append("cos(");
                    if (!buffer.isEmpty()) {
                        output.append(convertStringToScientific(concatenateBuffer(buffer)));
                    }
                    output.append(')');
                    break;
                case RegexParser.OPERATOR_TAN:
                    output.append("tan(");
                    if (!buffer.isEmpty()) {
                        output.append(convertStringToScientific(concatenateBuffer(buffer)));
                    }
                    output.append(')');
                    break;
                case RegexParser.OPERATOR_ARCSIN:
                    output.append("asin(");
                    if (!buffer.isEmpty()) {
                        output.append(convertStringToScientific(concatenateBuffer(buffer)));
                    }
                    output.append(')');
                    break;
                case RegexParser.OPERATOR_ARCCOS:
                    output.append("acos(");
                    if (!buffer.isEmpty()) {
                        output.append(convertStringToScientific(concatenateBuffer(buffer)));
                    }
                    output.append(')');
                    break;
                case RegexParser.OPERATOR_ARCTAN:
                    output.append("atan(");
                    if (!buffer.isEmpty()) {
                        output.append(convertStringToScientific(concatenateBuffer(buffer)));
                    }
                    output.append(')');
                    break;
                default:
                    break;
            }
        }
        if (!buffer.isEmpty()) {
            output.append(convertStringToScientific(concatenateBuffer(buffer)));
        }

        return output.toString();
    }
}
