import java.io.*;
import java.lang.*;
class TemperatureException extends Exception {
	private int temperature; //���ܿ� ���� �߰� ����

	TemperatureException() { super("���� �Ұ�"); }
	TemperatureException(String msg) { super(msg); }

	void setTemperature(int t) { temperature = t; }
	int getTemperature() { return temperature; }
} //TemperatureException

public class ���ڷΰ��� {
   	static int ����οµ� = 10000;

   	public static void main(String[] args) {
		try {	
			�����ΰ���(); 
			return;
		} catch (TemperatureException e) {
			System.out.println();
			System.out.println(e.getMessage() + ": " + e.getTemperature());
		} finally { 
			System.out.println("���ڷ� �˴ٿ�");
		}
   	} //main()

	static int ����() {
		int amount;
		amount = (int)(Math.random() * 1000);
		return amount;
  	} //����( )

	static int ����( ) {
		int temp=0;
		switch ((int)(Math.random() * 3)) {
			case 0: temp = -500; 	break;
			case 1: temp = 0; 	break;
			case 2: temp = 500;	break;
		}
		return temp;
    	} //����( )

	static void �����ΰ���( ) throws TemperatureException {
		TemperatureException e;
		while (true) {
	   		if (����οµ� > 15000) {
				TemperatureException e1 = new TemperatureException(); //���� �Ұ�
		   		e1.setTemperature(����οµ�);  
				throw e1;
			} else if (����οµ� > 12000) {
				����οµ� += ����();
				System.out.println("���: " + ����οµ�);
		   	} else {
				System.out.println();
	   			System.out.println("����: " + ����οµ�);
				����οµ� += ����();
			}
	   	}
  	} //�����ΰ���()
} //���ڷΰ���
