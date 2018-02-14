package clay.calcplus;

/**
 * Created by Clay on 1/28/2018.
 */

public class StringTranslator {
    int operand1, operand2, opcode;
    String opstring;

    public int getOperand1() {
        return operand1;
    }

    public int getOperand2() {
        return operand2;
    }

    public int getOpcode() {
        return opcode;
    }

    public void readTwoOperandSingleDigit(String s){
        //assumes the form operand-operator-operand
        operand1 = Character.getNumericValue(s.charAt(0));
        operand2 = Character.getNumericValue(s.charAt(2));
        switch (s.charAt(1)){
            case '+': opcode = 0;
                break;
            case '-': opcode = 1;
                break;
            case '*': opcode = 2;
                break;
            case '/': opcode = 3;
                break;
            default: opcode = -1;
                break;
        }
    }

    public void readTwoOperand(String s){
        //assumes the form operand-operator-operand
        opstring = s;
        int opdigitcount = 0; //current operand digit count
        boolean operatorencountered = false; //boolean keeping track of if we read an operator yet
        operand1 = operand2 = 0;
        opcode = -1;
        for (int i = s.length()-1; i>=0; i--){
            //reads the string from right to left, increasing a digit count to keep track of
            // what power of ten we're on, i.e. 123 means we read and add in this order:
            //(3*1)+(2*10)+(1*100)
            if (s.charAt(i) != '+' && s.charAt(i) != '-' && s.charAt(i) != '*' && s.charAt(i) != '/') {
                if (!operatorencountered)
                    operand2 += (Character.getNumericValue(s.charAt(i))) * (Math.pow(10, opdigitcount));
                else
                    operand1 += (Character.getNumericValue(s.charAt(i))) * (Math.pow(10, opdigitcount));
                opdigitcount += 1;
            }
            else{
                switch (s.charAt(i)){
                    case '+': opcode = 0;
                        break;
                    case '-': opcode = 1;
                        break;
                    case '*': opcode = 2;
                        break;
                    case '/': opcode = 3;
                        break;
                    default: opcode = -1;
                        break;
                }
                opdigitcount = 0;
                operatorencountered = true;
            }
        }
    }

    public int performTwoOperand(){ //method for testing the readTwoOperand
        int result;
        switch (opcode){
            case 0: result = operand1+operand2;
                break;
            case 1: result = operand1-operand2;
                break;
            case 2: result = operand1*operand2;
                break;
            case 3: result = operand1/operand2;
                break;
            default: result = 1888889;
                break;
        }
        System.out.print(opstring+" = "+result+"\n");
        return result;
    }

    public static void main(String args[]){
        StringTranslator str = new StringTranslator();
        str.readTwoOperand("200+25");
        str.performTwoOperand();
        str.readTwoOperand("300-75");
        str.performTwoOperand();
        str.readTwoOperand("100*5");
        str.performTwoOperand();
        str.readTwoOperand("100/25");
        str.performTwoOperand();
    }

}
