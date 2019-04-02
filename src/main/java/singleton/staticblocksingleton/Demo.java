package singleton.staticblocksingleton;

import java.io.File;
import java.io.IOException;

/**
 * Exceptions inside singleton constructor is problematic, however to overcome that we use static block singleton.
 * Let's see in the below example.
 */
class StaticBlockSingleton{

	//The below usual way of declaring single instance is problematic when we have a singleton constructor that throws,
	//exception. Since we are dependent on static and the final modifier its not possible to instantiate singleton as
	//we did it in our basic singleton
	/*private static final StaticBlockSingleton INSTANCE = null;

	static {
		try {
			INSTANCE = new StaticBlockSingleton();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/

	//However we can approach this by removing final keyword
	private static StaticBlockSingleton INSTANCE = null;

	static {
		try {
			INSTANCE = new StaticBlockSingleton();
		} catch (IOException e) {
			System.err.println("failed to create singleton");
			e.printStackTrace();
		}
	}

	/**
	 * additional pooling logic can go in here
	 */
	public static StaticBlockSingleton getINSTANCE() {
		return INSTANCE;
	}

	private StaticBlockSingleton() throws IOException {
		System.out.println("singleton is initializing....");
		File.createTempFile(".", "."); //for demonstration
	}
}

public class Demo {
}
