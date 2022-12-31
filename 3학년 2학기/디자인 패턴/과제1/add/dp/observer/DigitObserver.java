package add.dp.observer;


public class DigitObserver extends RandomNumberGenerator implements Observer {
	public void update(NumberGenerator generator) {
		System.out.println("DigitObserver:" + generator.getNumber()); 
		try {
			Thread.sleep(100); 
		} catch (InterruptedException e) {
		}
	}
}
