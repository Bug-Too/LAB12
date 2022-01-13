public class Parser {
    private Tokenizer tokenizer;
    public double calculator(String l,int lineCount) throws ArithmeticException,SyntaxError{
        tokenizer = new Tokenizer(l,lineCount);
        double result = parseE();
        if (!tokenizer.isEmpty()) throw new SyntaxError("Invalid input line: " + lineCount);
        if (!Double.isFinite(result)) throw new ArithmeticException("Line: " + lineCount + " is infinity may caused by divide by zero.");
        return result;
    }

    private double parseE() throws SyntaxError {
        double v = parseT();
        while (tokenizer.peek("+") || tokenizer.peek("-")) {
            String operator = tokenizer.consume();
            if (operator.equals("+")) {
                v += parseT();
            }
            else if (operator.equals("-")) {
                v -= parseT();
            }
        }
        return v;
    }

    private double parseT() throws SyntaxError {
        double v = parseF();
        while (tokenizer.peek("*") || tokenizer.peek("/") || tokenizer.peek("%")) {
            String operator = tokenizer.consume();
            switch (operator) {
                case "*" -> v *= parseF();
                case "/" -> v /= parseF();
                case "%" -> v %= parseF();
            }
        }
        return v;
    }

    private double parseF() throws SyntaxError {
        if (isNumber(tokenizer.peek())) {
            return Double.parseDouble(tokenizer.consume());
        } else {
            tokenizer.consume("(");
            double v = parseE();
            tokenizer.consume(")");
            return v;
        }
    }

    private static boolean isNumber(String str) {
        if (str == null) {
            return false;
        }

        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
