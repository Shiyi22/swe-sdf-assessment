package sdf;

import java.io.Console;

public class Calculator {

    public static void main(String[] args) {
        
        // assume all numbers and math operators are entered correctly 
        System.out.println("Welcome.");
        Console cons = System.console(); 

        String line = "", operator = ""; 
        String[] data = null; 
        boolean exit = false; 
        double result = 0.0d, last = 0.0d, oprd1 = 0.0d, oprd2 = 0.0d; 

        while (!exit) {
            line = cons.readLine("> "); 
            data = line.trim().split(" "); 
            
            if (line.trim().equals("exit")) {
                System.out.println("Bye bye");
                exit = true; 
                continue;
            }
            operator = data[1];

            // oprd could be $last or a number input 
            if ((data[0].equals("$last")) && !(data[2].equals("$last"))) {
                oprd1 = last; 
                oprd2 = Double.parseDouble(data[2]);
            } else if ((data[2].equals("$last")) && !(data[0].equals("$last"))) {
                oprd1 = Double.parseDouble(data[0]); 
                oprd2 = last;
            } else if ((data[0].equals("$last")) && (data[2].equals("$last"))) {
                oprd1 = last; 
                oprd2 = last;
            }   else {
                oprd1 = Double.parseDouble(data[0]); 
                oprd2 = Double.parseDouble(data[2]); 
            }
            
            switch (operator) {
                case Constants.ADD: result = oprd1 + oprd2; 
                    break; 
                case Constants.MINUS: result = oprd1 - oprd2;
                    break;
                case Constants.TIMES: result = oprd1 * oprd2;
                    break; 
                case Constants.DIVIDE: result = oprd1 / oprd2;
                    break; 
                default: System.out.println("Please check your formula");
                    break; 
            }

            System.out.println(Math.round(result));
            last = result;
        }
    }
}
