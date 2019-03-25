package singleton.basicsingleton;

/**
 * This class enforces the rule of having only one instance at any given time. In other words, we need to prevent creation of additional copies of our class.
 *
 * Steps:
 * Make the constructor private, restricting anyone to call constructor for object creation
 * Create a static final Instance variable which has reference to ONE object
 * Expose the INSTANCE field via getter method
 *
 */
class BasicSingleton {
	//since we restricted creation of objects via constructor, how can we expose object creation of this class
	//we create a single instance and return that single instance
	private static final BasicSingleton INSTANCE = new BasicSingleton();
	//for demonstration let's add some data
	private int value = 0;

	//how can we restrict creating objects
	//BasicSingleton basicSingleton = new BasicSingleton(); //illegal cos private access
	private BasicSingleton() {

	}

	//getter for our instance
	public static BasicSingleton getINSTANCE() {
		return INSTANCE;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}

public class Demo {
	public static void main(String[] args) {
		BasicSingleton singletonInstance1 = BasicSingleton.getINSTANCE();
		singletonInstance1.setValue(123);

		BasicSingleton singletonInstance2 = BasicSingleton.getINSTANCE();
		singletonInstance2.setValue(456);

		//If our singleton pattern implementation logic is valid then instance1 and instance2 should point to the same object
		//In other words, the recent changes to the object will be the one holding in the object
		System.out.println("Instance 1 value : "+singletonInstance1.getValue());
		System.out.println("Instance 2 value : "+singletonInstance2.getValue());
	}
}
