package singleton.monostate;

/**
 * Imagine in our company we have a CEO with some fields.
 * Now, if we want to make our POJO instantly singleton. we have to change the fields to static.
 * Now all the instances to a single storage element
 */
class ChiefExecutiveOfficer{
	private static String name;
	private static int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		ChiefExecutiveOfficer.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		ChiefExecutiveOfficer.age = age;
	}

	@Override
	public String toString() {
		return "ChiefExecutiveOfficer{" +
				"name='" + name + '\'' +
				", age=" + age +
				'}';
	}
}
public class Demo {
	public static void main(String[] args) {
		ChiefExecutiveOfficer chiefExecutiveOfficer = new ChiefExecutiveOfficer();
		chiefExecutiveOfficer.setName("Adam Smith");
		chiefExecutiveOfficer.setAge(55);

		ChiefExecutiveOfficer chiefExecutiveOfficer1 = new ChiefExecutiveOfficer();
		System.out.println(chiefExecutiveOfficer1); //already initialized, however no indication that this is already initialized
	}
}
