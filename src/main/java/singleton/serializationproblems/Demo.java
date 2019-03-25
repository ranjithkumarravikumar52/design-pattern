package singleton.serializationproblems;

import java.io.*;

/**
 * Serialization
 *
 * When we deserialize an object, the JVM will call private constructor to construct an object, breaking singleton pattern.
 *
 */
class BasicSingleton implements Serializable {
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

	//serializing - take a basic singleton and save it a file
	static void saveToFile(BasicSingleton singleton, String fileName) throws Exception{
		try(FileOutputStream fileOutputStream = new FileOutputStream(fileName); ObjectOutputStream out = new ObjectOutputStream(fileOutputStream)){
			out.writeObject(singleton);
		}
	}

	//deserialize - returns a basic singleton
	static BasicSingleton readFromFile(String fileName) throws Exception{
		try(FileInputStream fileInputStream = new FileInputStream(fileName); ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
			return (BasicSingleton) objectInputStream.readObject();
		}
	}


	public static void main(String[] args) throws Exception {
		//demonstrating serialization breaking singleton1 pattern

		//create singleton1 instance
		BasicSingleton singleton1 = BasicSingleton.getINSTANCE();
		singleton1.setValue(111);

		//create our file with some name
		String fileName = "singleton1.bin";
		saveToFile(singleton1, fileName);

		//after saving change the value
		singleton1.setValue(222);

		//read
		BasicSingleton singleton2 = readFromFile(fileName);

		//check both object references, if our singleton pattern is valid then both should have same value
		System.out.println("Are singleton1 and singleton2 equal? ");
		System.out.println(singleton1 == singleton2);

		//check both values, if our singleton pattern is valid then both should have same value
		System.out.println(singleton1.getValue());
		System.out.println(singleton2.getValue());
	}
}
