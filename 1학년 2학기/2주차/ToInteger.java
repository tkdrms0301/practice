import java.util.Scanner;

public class ToInteger
{
	static int toInteger(String str){
		int num = 1;
		int output = 0;
		for(int i = str.length()-1; i>=0; i--){
			output = output + ((int)str.charAt(i)-48) * num; // �ƽ�Ű�ڵ�ǥ���� 0�� 48�� �ش�� 1~9�� �ش��ϴ� ������ ����
			num = num * 10;
		}
		return output;
	}
	public static void main(String[] args)
	{
		Scanner s=new Scanner(System.in);
		String input;
		input = s.nextLine();
		System.out.print(toInteger(input));
	}
}