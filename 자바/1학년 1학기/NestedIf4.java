import java.util.Scanner;
public class NestedIf4
{
	public static void main(String[] args)
		{
		Scanner s=new Scanner(System.in);
		System.out.print("����(m/f) ���� : ");
		char gender = s.next().charAt(0);
		int age=s.nextInt();

		if(gender=='f')
			if (age>20)
			System.out.println("���� ����");
			else
			System.out.println("���� �̼�����");
		else
			if(age>20)
			System.out.println("���� ����");
			else
			System.out.println("���� �̼�����");
		}
}