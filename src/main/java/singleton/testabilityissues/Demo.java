package singleton.testabilityissues;


import com.google.common.collect.Iterables;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;


//load the capitals.text only ONCE
class SingletonDatabase {
	private static final SingletonDatabase INSTANCE = new SingletonDatabase();
	private static int instanceCount = 0;
	private Dictionary<String, Integer> capitals = new Hashtable<>();

	private SingletonDatabase() {
		instanceCount++;
		System.out.println("Initializing database...");


		try {
			File file = new File(
					SingletonDatabase.class.getProtectionDomain()
							.getCodeSource().getLocation().getPath()
			);
			Path fullPath = Paths.get(file.getPath(), "capitals.txt");
			List<String> lines = Files.readAllLines(fullPath);

			//init database
			Iterables.partition(lines, 2)
					.forEach(kv -> capitals.put(
							kv.get(0).trim(),
							Integer.parseInt(kv.get(1))
					));

			System.out.println(lines);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static int getCount() {
		return instanceCount;
	}

	public static SingletonDatabase getINSTANCE() {
		return INSTANCE;
	}

	public int getPopulation(String name){
		return capitals.get(name);
	}
}

//research on different cities
class SingletonRecordFinder{
	public int getTotalPopulation(List<String> names){
		int result = 0;
		for(String name: names){
			result += SingletonDatabase.getINSTANCE().getPopulation(name); //how do we test
		}
		return result;
	}
}

class Tests{
	@Test
	/**
	 * Problem is loading real database here. This is more of a ITTest
	 */
	public void singletonTotalPopulationTest(){
		SingletonRecordFinder rf = new SingletonRecordFinder();
		List <String> cities = Arrays.asList("Seoul", "Mexico City");
		int tp = rf.getTotalPopulation(cities);
		System.out.println(17500000+17400000 == tp); //this test is going to work

	}
}

public class Demo {
}
