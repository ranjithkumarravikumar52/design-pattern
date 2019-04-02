package singleton.innerstaticsingleton;

/**
 * This approach avoids implementing synchronized keyword and also solves the problems of race condition.
 *
 * <h1>initialization-on-demand holder</h1>
 * This is called the initialization-on-demand holder idiom. <br>
 *     In Java, encapsulating classes do not automatically initialize inner classes. So the inner class only gets initialized by getInstance(). Then again, class initialization is guaranteed to be sequential in Java, so the JVM implicitly renders it thread-safe.
 */
public class InnerStaticSingleton {

	private InnerStaticSingleton(){}

	//nested class
	private static class Impl{
		//inner static class variables can access outer class variables OR outside constructors
		private static final InnerStaticSingleton
				INSTANCE = new InnerStaticSingleton();
	}

	//expose
	public static InnerStaticSingleton getInstance(){
		return Impl.INSTANCE;
	}
}
