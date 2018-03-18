package com.evan.pocketcalcplus;

import org.junit.Test;


import java.math.BigDecimal;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.assertFalse;

/**
 * Created by Eric Myllyoja on 3/12/2018.
 */

public class CalculatorUnitTests {
    @Test
    public void infixParsing_isCorrect() throws Exception {
        // Testing for RegexParser.inputToPrefix();

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
    @Test
    public void equationParsing_isCorrect() throws Exception {
        // Testing for RegexParser.parsePrefix();
        assertEquals(4, 2 + 2);

        assertEquals(new BigDecimal("16"), RegexParser.parsePrefix("+ 8 8"));

        assertEquals(new BigDecimal("9"), RegexParser.parsePrefix("+ 5 4"));
        assertEquals(new BigDecimal("1"), RegexParser.parsePrefix("- 5 4"));
        assertEquals(new BigDecimal("20"), RegexParser.parsePrefix("* 5 4"));
        assertEquals(new BigDecimal("5"), RegexParser.parsePrefix("/ 20 4"));

        assertEquals(new BigDecimal("26"), RegexParser.parsePrefix("5*4+6"));
    }
}
