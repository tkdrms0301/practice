package add.dp.observer;

import java.util.Vector;
import java.util.Iterator;


public abstract class NumberGenerator implements Observer {
	
	private Vector observers = new Vector(); 

	public void addObserver(Observer observer) { 
		observers.add(observer);
	}
	public void deleteObserver(Observer observer) { 
		observers.remove(observer);
	}

	public void notifyObservers() { 
		Iterator it = observers.iterator();
		
		while (it.hasNext()) {
			Observer o = (Observer) it.next();
			o.update(this);
		}
	}

	public abstract int getNumber(); 

	public abstract void execute(); 
}
