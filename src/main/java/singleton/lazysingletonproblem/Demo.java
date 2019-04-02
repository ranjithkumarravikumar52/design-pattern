package singleton.lazysingletonproblem;

/**
 * Lazy initialization: Sometimes we want the initialization(object creation) only when we call the
 * get instance method. NOT in static block or method.
 */
class LazySingleton{
	private static LazySingleton INSTANCE;
	private LazySingleton(){
		System.out.println("initializing a lazy singleton");
	}

	/**
	 * We are waiting object creation till someone calls our getInstance().
	 * however this might cause race condition. Imagine two threads checking null conditions at the same time and creates
	 * two object.
	 * Solution is to use synchronized modifier. However this will call cause performance issues.
	 * Other approach is double-checked locking
	 */
	/*public static LazySingleton getINSTANCE(){
		if(INSTANCE == null){
			INSTANCE = new LazySingleton();
		}
		return INSTANCE;
	}*/

	//double-checked locking also thread safe.
	public static LazySingleton getINSTANCE(){
		if(INSTANCE == null){
			synchronized (LazySingleton.class){
				if(INSTANCE == null){
					INSTANCE = new LazySingleton();
				}
			}
		}
		return INSTANCE;
	}
}



public class Demo {
}
