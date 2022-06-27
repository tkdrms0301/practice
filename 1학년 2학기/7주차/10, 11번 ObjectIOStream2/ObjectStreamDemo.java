import java.io.*;
public class ObjectStreamDemo {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Order[] orders = {
			new Order("10���� ����", 10.0f, 10),
			new Order("��ũ���׽����� ����Ʈ���������", 20.0f, 100),
			new Order("���׿��� ��ȭ", 30.0f, 1000)
		};
		ObjectOutputStream out = null;
		ObjectInputStream in = null;
		
		try {
			out = new ObjectOutputStream(new FileOutputStream("orders.dat"));
			for (Order o : orders) { out.writeObject(o); }
		
			float total=0.0f;
			in = new ObjectInputStream(new FileInputStream("orders.dat"));
			for (Order o : orders) {
				o = (Order)in.readObject();
				System.out.format("You ordered %d unit of %s at $%.1f%n", o.getUnit(), o.getTitle(), o.getPrice());
				total += o.getUnit() * o.getPrice();
			}
			System.out.format("Total: %.2f$", total);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try { if(out != null) out.close(); } catch(IOException e) {}
			try { if(in != null) in.close(); } catch(IOException e) {}
		}
	}
}