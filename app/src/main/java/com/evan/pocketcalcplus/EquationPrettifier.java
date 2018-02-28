package com.evan.pocketcalcplus;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;
import java.util.Stack;

public class EquationPrettifier {
    private static String convertStringToScientific(String input) {
        return String.format(Locale.getDefault(), "%6.4G", new BigDecimal(input));
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

        for (char current : input.toCharArray()) {
            switch (current) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                case '.':
                    // Part of a number, add onto stack.
                    buffer.add(current);
                    break;
                case '+':
                case '-':
                case '*':
                case '/':
                case '%':
                    // Operators, previous number is done.
                    if (!buffer.isEmpty()) {
                        output.append(convertStringToScientific(concatenateBuffer(buffer)));
                    }
                    output.append(' ');
                    output.append(current);
                    output.append(' ');
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
