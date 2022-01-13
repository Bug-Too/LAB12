import java.io.*;
import java.util.InputMismatchException;

public class Lab12 {
    protected static int lineCount = 0;
    protected static int index = 0;
    public static void main(String[] args) {
            String inputFileDirectory = "C:\\Users\\puric\\Documents\\OOP\\Lab12\\src\\input.txt";
            String outputFileDirectory = "C:\\Users\\puric\\Documents\\OOP\\Lab12\\src\\output.txt";
            readAndWrite(inputFileDirectory,outputFileDirectory);
    }

    public static void readAndWrite(String inputFileDirectory , String outputFileDirectory){
        String line = "";
        Parser p = new Parser();
        try (BufferedReader br = new BufferedReader(new FileReader(inputFileDirectory));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileDirectory))
        ) {
            while ((line = br.readLine()) != null) {
                lineCount++;
                if (line.length() == 0) continue;


                try {
                    String outLine = line + " = " + p.calculator(line, lineCount)+ "\n";
                    System.out.print(outLine);
                    bw.append(outLine);
                }catch (ArithmeticException | InputMismatchException e){
                    e.printStackTrace();
                    System.out.println("Invalid input line: " + lineCount);
                } catch (SyntaxError e) {
                    e.printStackTrace();
                    System.out.println("Invalid input line: " + lineCount);
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

//ขอบคุณธนดลที่ให้ความช่วยเหลือ