package singleton.enumsingleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//serializing and deserialize.
public class Demo{
	//serializing - take a basic singleton and save it a file
	static void saveToFile(EnumBasedSingleton singleton, String fileName) throws Exception{
		try(FileOutputStream fileOutputStream = new FileOutputStream(fileName); ObjectOutputStream out = new ObjectOutputStream(fileOutputStream)){
			out.writeObject(singleton);
		}
	}

	//deserialize - returns a basic singleton
	static EnumBasedSingleton readFromFile(String fileName) throws Exception{
		try(FileInputStream fileInputStream = new FileInputStream(fileName); ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
			return (EnumBasedSingleton) objectInputStream.readObject();
		}
	}

	public static void main(String[] args) throws Exception{
		String fileName = "myFile.bin";
		/*EnumBasedSingleton singleton1 = EnumBasedSingleton.INSTANCE;
		singleton1.setValue(111); //name of the value is saved not the value
		saveToFile(singleton1, fileName);*/

		//read and check if its the same object and has same object reference
		EnumBasedSingleton singleton2 = readFromFile(fileName); //doesn't read the 111 value that we saved earlier.
		System.out.println(singleton2.getValue());
	}
}