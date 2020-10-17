package laba2;

import java.util.Stack;
/**
 * ������ ���� ��������� ��������� �������� ��������� ���������.
 * @author ����� ������������
 *
 */
public class Expression {
	/**
	 * ������ ������� ����������� ��������� � �������� �������� ������
	 * @param exp �������� ���������
	 * @return ���������� ��������� � ���� �������� �������� ������ 
	 */
	public static String expToRPN(String exp) {
		String s = "";
		Stack<Character> stack = new Stack<>();
		
		int priority;
		for (int i = 0; i<exp.length();i++) {
			priority = getPriority(exp.charAt(i));
			if(priority == 0)
				s+=exp.charAt(i);
			if(priority == 1)
				stack.push(exp.charAt(i));
			
			if(priority > 1) {
				s+=' ';
				
				while(!stack.empty()) {
					if(getPriority(stack.peek())>= priority)
						s+=stack.pop();
					else break;
				}
				stack.push(exp.charAt(i));
			}
			
			if(priority == -1) {
				s+=' ';
				while(getPriority(stack.peek())!= 1)
					s+=stack.pop();
				stack.pop();
			}
		}
		while(!stack.empty())
			s+=stack.pop();
		return s;
	}

	/**
	 * ������ ������� ��������� �������� ���������, ��������������� � �������� ������
	 * @param rpn ��������� � ���� �������� �������� ������
	 * @return �������� ���������
	 */
		
	public static double rpnToAnswer(String rpn) {
		String operand = new String();
		Stack<Double> stack = new Stack<>();
		
		for(int i=0; i < rpn.length(); i++) {
			if(rpn.charAt(i) == ' ') continue;
			if(getPriority(rpn.charAt(i))==0) {
				while(rpn.charAt(i)!= ' ' && getPriority(rpn.charAt(i)) == 0) {
					operand+=rpn.charAt(i++);
				    if(i == rpn.length()) break;
				}
			
			stack.push(Double.parseDouble(operand));
			operand = new String();
			}
	
		    if(getPriority(rpn.charAt(i)) > 1) {
		    	double a = stack.pop();
		    	double b = stack.pop();
		    	
		    	if (rpn.charAt(i) == '+')
		    		stack.push(b+a);
		    	if (rpn.charAt(i) == '-')
		    		stack.push(b-a);
		    	if (rpn.charAt(i) == '*')
		    		stack.push(b*a);
		    	if (rpn.charAt(i) == '/')
		    		stack.push(b/a);
		    }	
		}
		return stack.pop();
	}
	
	/**
	 * ����� ���������� ��������� ���������
	 * @param token ������, ������� ����� ��������� �� ��������� 
	 * @return ��������� �������
	 */
	public static int getPriority(char token) {
		if(token == '*' || token == '/')
			return 3;
		else if(token == '+' || token =='-')
			return 2;
		else if(token == '(')
			return 1;
		else if(token == ')')
			return -1;
		return 0;
	}
	
	/**
	 * ������� ��������� ������ �������������� ���������
	 * @param exp �������������� ��������� 
	 * @return ������� ��������������� ���������
	 */
	public double solution (String exp) {
		String nN = negativeNamber(exp);
		String rpn = expToRPN(nN);
		return rpnToAnswer(rpn);
	}
	
	/**
	 * ����� ����������� ��������� � �������������� ������� � ������������ ����� ��� ���
	 * @param exp ��������� ��� ��������������
	 * @return ��������� � �������� ��� ��� �����
	 */
	public String negativeNamber (String exp) {
		String negNb = new String();
		for (int token = 0; token < exp.length(); token++) {
			char symbol = exp.charAt(token);
			if (symbol == '-') {
				if (token == 0)
					negNb += '0';
				else if (exp.charAt(token-1) == '(')
					negNb += '0';
			}
			negNb += symbol;
		}
		return negNb;
	}


	
	
	/**
	 * ������� ������
	 * @param args
	 */
	public static void main(String args[])
	{
		String exp = "(2+10)/2-4*(-2)";
		System.out.println(new Expression().solution(exp));
     
	}
}
