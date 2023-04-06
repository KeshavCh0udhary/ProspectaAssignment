package CSV;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		//String s = "A1: 5,A2: 7,A3: 9,B1: 3,B2: 8,B3: =4+5,C1: =5+A1,C2: =A2+B2,C3: =C2+B3";
		System.out.println(getValues(input)); 
	}
	
	private static String csvWriter(Map<String,Integer> map) {
		
		Iterator<Entry<String, Integer>> itr = map.entrySet().iterator();
        
		StringBuilder bag = new StringBuilder();
		
        while(itr.hasNext()){
             Entry<String, Integer> entry = itr.next();
             bag.append(entry.getKey()+": "+entry.getValue()+",");
        }
        
		return bag.toString();
	}
	
	public static String getValues(String input) {
	
		String[] arr = input.split(",");
		
		if(arr.length == 0) return "Invalid Input";
		
		
		Map<String,Integer> map = new LinkedHashMap<>();
				
		try {
			
			for(String str : arr) {
				
				String[] cellValue = str.split(": ");
             
				if(cellValue.length != 2) return "Invalid Input";
				
				String cell = cellValue[0];
				String value = cellValue[1];
				
				
				if(isValidCell(cell)) {
					
					if(isNum(value)) map.put(cell, Integer.parseInt(value));
					
					else {
						
						if(isValidExpression(value)) {
							String exp = value.substring(1);
							
							String[] expression = exp.split("[+*/-]");
							
							boolean add = false;
							boolean subtract = false;
							boolean multiply = false;
							boolean divide = false;
							
							if(exp.contains("+")) add = true;
							if(exp.contains("-")) subtract = true;
							if(exp.contains("*")) multiply = true;
							if(exp.contains("/")) divide = true;
							
							Integer val1 = null;
							Integer val2 = null;
							
							if(isValidCell(expression[0])) {
								
								if(map.containsKey(expression[0])) val1 = map.get(expression[0]);
									
								else return "Invalid Input";
							}
							if(isValidCell(expression[1])) {
								
								if(map.containsKey(expression[1])) val2 = map.get(expression[1]);
								
								else return "Invalid Input";
							}
							
				
							if(isNum(expression[0]) && isNum(expression[1])) {
								
								Integer result = null; 
								Integer n1 =  Integer.parseInt(expression[0]);
								Integer n2 =  Integer.parseInt(expression[1]);
								if(add) result = add(n1, n2);
								
								if(subtract) result = subtract(n1,n2);
								
								if(multiply) result = multiply(n1, n2);
								
								if(divide) result = divide(n1,n2);
								
								map.put(cell, result);
								
							}else if(val1!=null && val2 !=null){
								
					            Integer result = null;
					            if(add) result = add(val1, val2);
					            
								if(subtract) result = subtract(val1, val2);
								
								if(multiply) result = multiply(val1, val2);
								
								if(divide) result = divide(val1, val2);
								
								map.put(cell, result);
								
							}else if(val1!=null && val2 == null) {
								
								Integer result = null; 
								Integer n1 =  val1;
								Integer n2 =  Integer.parseInt(expression[1]);
								if(add) result = add(n1, n2);
								
								if(subtract) result = subtract(n1,n2);
								
								if(multiply) result = multiply(n1, n2);
								
								if(divide) result = divide(n1,n2);
								
								map.put(cell, result);
								
							}else if(val1==null && val2 != null) {
								
								Integer result = null; 
								Integer n1 =  Integer.parseInt(expression[0]);
								Integer n2 = val2;
								if(add) result = add(n1, n2);
								
								if(subtract) result = subtract(n1,n2);
								
								if(multiply) result = multiply(n1, n2);
								
								if(divide) result = divide(n1,n2);
								
								map.put(cell, result);
							
							}else return "Invalid Input";
							
						}else return "Invalid Input";
					}
				}else return "Invalid Input";
			}
		} catch (NumberFormatException e) {
			return "Invalid Input";
		}
        return csvWriter(map);
        
	}
	
	//return true if cell pattern matches else return false
	private static boolean isValidCell(String str) {
		return Pattern.matches("^[A-Z]{1,2}[1-9]{1}[0-9]{0,4}$", str);
	}
	
	//return true if given input is number
	private static boolean isNum(String str) {
		return Pattern.matches("^[0-9]+$", str);
	}
	
	//return true if the given expression is a valid formula else return false
	private static boolean isValidExpression(String str) {
		return Pattern.matches("^=[A-Z0-9]+[+*/-][A-Z0-9]+$", str);
	}
	
	private static int add(int n1,int n2) {
		return n1 + n2;
	}
	
	private static int subtract(int n1,int n2) {
		return n1 - n2;
	}
	
	private static int multiply(int n1,int n2) {
		return n1 * n2;
	}
	
	private static int divide(int n1,int n2) {
		return n1/n2;
	}

}
