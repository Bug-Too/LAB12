import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

public class Tokenizer {
    protected int lineCount = 0;
    protected int index = 0;
    protected String l;
    private final Queue<String> tokens = new LinkedList<>();

    public Tokenizer(String l, int lineCount){
        this.lineCount = lineCount;
        this.l = l;
        token();
    }

    public void token() throws ArithmeticException,InputMismatchException{
        index = 0;
        if(l.length() == 0) throw new InputMismatchException("Empty line: " + lineCount);
        String noSpaceLine = l.replaceAll("\\s+","");

        while (index < noSpaceLine.length()){
            if(noSpaceLine.length()>index && isOperand(noSpaceLine.charAt(index)) ) tokens.add(String.valueOf(findOperand(noSpaceLine)));
            if(noSpaceLine.length()>index && isOperator(noSpaceLine.charAt(index))  )tokens.add(String.valueOf(findOperater(noSpaceLine)));
            if(isParenthesisAtEnd(noSpaceLine)){
                tokens.add(")");
            }
        }

    }


    public double findOperand(String t) throws InputMismatchException{
        String tmp = "";

        while (isEnd(t) && isOperand(t.charAt(index))){
            tmp = tmp + t.charAt(index);
            index = index + 1;
        }
        return Double.parseDouble(tmp);
    }

    public char findOperater(String t) throws InputMismatchException{
        if(isOperator(t.charAt(index))){
            index = index + 1;
            return t.charAt(index - 1);
        }else{
            throw new InputMismatchException("Invalid input line: " + lineCount);
        }
    }

    public boolean isParenthesisAtEnd(String t){
        if(index>=t.length()) return false;
        if(t.charAt(index) == ')'){
            index = index + 1;
            return true;
        }
        return false;
    }

    public boolean isOperand(char c){
        return c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9';
    }

    public boolean isOperator(char c){
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c ==')';
    }

    public boolean isEnd(String t){
        return index != t.length();
    }
    public boolean isEmpty(){
        return tokens.isEmpty();
    }


    public String peek() {
        return tokens.peek();
    }

    public String consume() {
        return tokens.poll();
    }

    public boolean peek(String s) {
        if (tokens.isEmpty()) return false;
        else return peek().equals(s);
    }

    public void consume(String s) throws SyntaxError {
        if (peek(s)) consume();
        else throw new SyntaxError("Invalid input line: " + lineCount);
    }

}
