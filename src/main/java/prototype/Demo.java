package prototype;


import java.util.Arrays;

class Address{
	public String streetName;
	public int houseNumber;

	public Address(String streetName, int houseNumber) {
		this.streetName = streetName;
		this.houseNumber = houseNumber;
	}

	@Override
	public String toString() {
		return "Address{" +
				"streetName='" + streetName + '\'' +
				", houseNumber=" + houseNumber +
				'}';
	}
}

class Person{
	public String[] names;
	public Address address;

	public Person(String[] names, Address address) {
		this.names = names;
		this.address = address;
	}

	@Override
	public String toString() {
		return "Person{" +
				"names=" + Arrays.toString(names) +
				", address=" + address +
				'}';
	}
}

public class Demo {
	public static void main(String[] args) {
		//make a person
		Person john = new Person(new String[]{"John", "Smith"}, new Address("London Road", 123));
		//make another person who lives next door to john
		//we can make a new object to achieve this. However, it becomes costly/unnecessary to create a new object when there is a minor modification to another object
		//here comes the approaches to achieve this
		//copying reference is a BAD IDEA
		Person jane = john;
		jane.names[0] = "Jane";
		jane.address.houseNumber = 124;

		//since the object is copied by reference both john and jane refers to same object
		System.out.println(john);
		System.out.println(jane);

	}
}
