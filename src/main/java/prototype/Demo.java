package prototype;


import java.util.Arrays;

//cloneable is a marker interface
//unfortunately this is a ill-suggestion
//doesn't mention about deep-copy/shallow-copy(copy from references, not helpful)
class Address implements Cloneable{
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

	//notice the exception it throws
	//protected modifier, however we can change it public
	//modify the super.clone() to call this() - NAIVE APPROACH
	@Override
	public Object clone() throws CloneNotSupportedException {
		//string is immutable, copy is fine
		//int is copy-value
		return new Address(streetName, houseNumber);
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

	@Override
	protected Object clone() throws CloneNotSupportedException {
		//this is where things get tricky
		//GUESS: because names is an array and we are copying references
		//also same thing for address, which is copying references
		return new Person(names.clone(), (Address) address.clone());
	}
}

public class Demo {
	public static void main(String[] args) throws CloneNotSupportedException{
		//make a person
		Person john = new Person(new String[]{"John", "Smith"}, new Address("London Road", 123));
		//make another person who lives next door to john
		//we can make a new object to achieve this. However, it becomes costly/unnecessary to create a new object when there is a minor modification to another object

		//here comes the approaches to achieve this
		//copying reference is a BAD IDEA
		// Person jane = john;
		// jane.names[0] = "Jane";
		// jane.address.houseNumber = 124;
		//
		// //since the object is copied by reference both john and jane refers to same object
		// System.out.println(john);
		// System.out.println(jane);

		//other approach is to use clone
		//notice the casting
		Person jane = (Person) john.clone();
		jane.names[0] = "Jane";
		jane.address.houseNumber = 124;

		//once clone is implemented, we get a different copy for john and jane
		//however notice the pain-staking works needs to be done
		//marker-interface
		//call clone across various members
		//casting
		//easy cracks
		System.out.println(john);
		System.out.println(jane);

	}
}
