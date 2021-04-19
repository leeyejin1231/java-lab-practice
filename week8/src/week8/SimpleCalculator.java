package week8;
import java.util.Scanner;
import org.w3c.dom.ranges.RangeException;
import java.io.*;

class AddZeroException extends Exception{
	public AddZeroException() {
		System.out.println("AddZeroException");
	}
}

class SubtractZeroException extends Exception{
	public SubtractZeroException() {
		System.out.println("SubtractZeroException");
	}
}

class OutOfRangeException extends Exception{
	public OutOfRangeException() {
		System.out.println("OutOfRangeException");
	}
}


public class SimpleCalculator {

	public static void main(String[] args) throws AddZeroException, SubtractZeroException, OutOfRangeException {
		
		
		int input1, input2, output=0;
		String operator;
		
		Scanner scn = new Scanner(System.in);
		String s = scn.next();

	    String[] strArray = s.split("(?=[-+*/()])|(?<=[^-+*/][-+*/])");		
	      
		try {
			
		    input1 = Integer.parseInt(strArray[0]);
			operator = strArray[1];
			input2 = Integer.parseInt(strArray[2]);
			
			Cal(input1, input2, operator);
			
			if(operator.equals("+")) {
				output = input1 + input2;
			}else
				output = input1 - input2; 
			
			System.out.println(output);
						
		} catch(AddZeroException e) {
			
		} catch(SubtractZeroException e) {
			
		} catch(OutOfRangeException e) {
			
		}finally{
			
		}
	}
	
	public static void Cal(int input1, int input2, String operator) throws AddZeroException, SubtractZeroException, OutOfRangeException{
		if(operator.equals("+")) {
			if(input1 == 0 || input2 ==0)
				throw new AddZeroException();
			if(input1<0 || input1 >1000 || input2 <0 || input2>1000 || (input1+input2)<0 || (input1+input2)>1000)
				throw new OutOfRangeException();
		}else {
			if(input1 == 0 || input2 ==0)
				throw new SubtractZeroException();
			if(input1<0 || input1 >1000 || input2 <0 || input2>1000 || (input1-input2)<0 || (input1-input2)>1000)
				throw new OutOfRangeException();
		}
		
	}
	

}






