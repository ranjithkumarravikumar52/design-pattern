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

import static org.junit.jupiter.api.Assertions.assertEquals;

interface Database{
	int getPopulation(String name);
}

//load the capitals.text only ONCE
class SingletonDatabase implements Database{
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

	@Override
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

/**
 * <b>True unit test case takes around 16ms, whereas IT takes around 63ms</b>
 */
class Tests{
	/**
	 * Problem is loading real database here. This is more of a ITTest
	 */
	@Test
	public void singletonTotalPopulationTest(){
		SingletonRecordFinder rf = new SingletonRecordFinder();
		List <String> cities = Arrays.asList("Seoul", "Mexico City");
		int tp = rf.getTotalPopulation(cities);
		assertEquals(17500000+17400000, tp); //this test is going to work

	}

	//unit test
	@Test
	public void dependentPopulationTest(){
		DummyDatabase dummyDatabase = new DummyDatabase();
		ConfigurableRecordFinder configurableRecordFinder = new ConfigurableRecordFinder(dummyDatabase);
		assertEquals(3, configurableRecordFinder.getTotalPopulation(
				Arrays.asList("alpha", "beta")
		));
	}
}

class ConfigurableRecordFinder{
	private Database database;

	//injection
	public ConfigurableRecordFinder(Database database) {
		this.database = database;
	}

	//configurable to inject any DB we want (real or dummy)
	public int getTotalPopulation(List<String> names){
		int result = 0;
		for(String name: names){
			result += database.getPopulation(name);
		}
		return result;
	}
}

class DummyDatabase implements Database{

	private Dictionary<String, Integer> data = new Hashtable<>();

	public DummyDatabase(){
		data.put("alpha", 1);
		data.put("beta", 2);
		data.put("theta", 3);
	}

	@Override
	public int getPopulation(String name) {
		return data.get(name);
	}
}

public class Demo {
}
