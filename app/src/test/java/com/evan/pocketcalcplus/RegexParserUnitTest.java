package com.evan.pocketcalcplus;

import org.junit.Test;


import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.assertFalse;

/**
 * Created by Eric Myllyoja on 3/12/2018.
 */

public class RegexParserUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        // Test assertion.
        assertEquals(4, 2 + 2);

        assertTrue(RegexParser.isTwoOperandOperator("+"));
        assertTrue(RegexParser.isTwoOperandOperator("-"));
        assertTrue(RegexParser.isTwoOperandOperator("*"));
        assertTrue(RegexParser.isTwoOperandOperator("/"));

        assertTrue(RegexParser.getTwoOperandPrecedence("*") >
                RegexParser.getTwoOperandPrecedence("+"));
        assertTrue(RegexParser.getTwoOperandPrecedence("/") >
                RegexParser.getTwoOperandPrecedence("-"));

        assertEquals("+ a b", RegexParser.inputToPrefix("a + b"));
        assertEquals("+ a b", RegexParser.inputToPrefix("a+b"));
        assertEquals("- a b", RegexParser.inputToPrefix("a - b"));
        assertEquals("- a b", RegexParser.inputToPrefix("a-b"));
        assertEquals("* a b", RegexParser.inputToPrefix("a * b"));
        assertEquals("* a b", RegexParser.inputToPrefix("a*b"));
        assertEquals("/ a b", RegexParser.inputToPrefix("a / b"));
        assertEquals("/ a b", RegexParser.inputToPrefix("a/b"));
        assertEquals("^ a b", RegexParser.inputToPrefix("a ^ b"));
        assertEquals("^ a b", RegexParser.inputToPrefix("a^b"));
        assertEquals("% a b", RegexParser.inputToPrefix("a % b"));
        assertEquals("% a b", RegexParser.inputToPrefix("a%b"));

        assertEquals("+ * a b c", RegexParser.inputToPrefix("a * b + c"));
        assertEquals("+ * a b c", RegexParser.inputToPrefix("a*b+c"));

        assertEquals("\u0401 a", RegexParser.inputToPrefix("\u0401 a"));
        assertEquals("\u0402 a", RegexParser.inputToPrefix("\u0402a"));
    }
}
