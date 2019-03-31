package utils;

public interface Observable<O> {

	void attach(O observer);
	void detach(O observer);
	
}
