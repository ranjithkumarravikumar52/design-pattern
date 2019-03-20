package prototype.copyconstructors;



class Address {
	public String streetAddress, city, country;

	public Address(String streetAddress, String city, String country) {
		this.streetAddress = streetAddress;
		this.city = city;
		this.country = country;
	}

	//implement copy-constructor
	//copy each field
	public Address(Address other){
		this(other.streetAddress, other.city, other.country);
	}

	@Override
	public String toString() {
		return "Address{" +
				"streetAddress='" + streetAddress + '\'' +
				", city='" + city + '\'' +
				", country='" + country + '\'' +
				'}';
	}
}

class Employee{
	public String name;
	public Address address;

	public Employee(String name, Address address) {
		this.name = name;
		this.address = address;
	}

	//copy-constructor
	public Employee(Employee other){
		name = other.name;
		address = new Address(other.address);
	}

	@Override
	public String toString() {
		return "Employee{" +
				"name='" + name + '\'' +
				", address=" + address +
				'}';
	}
}

public class Demo {
	public static void main(String[] args) {
		Address ukAddress = new Address("123 London Road", "London", "UK");
		Employee john = new Employee("John", ukAddress);
		//now we want a new employee who lives at the same ukAddress
		Employee jane = new Employee("Jane", new Address(ukAddress));

		//OR a new employee with same name as john but from Germany
		//bad example ikr ;)
		//anyways, the takeaway from here is, this copy-constructor approach is more powerful than cloneable interface approach
		//CONS: however this becomes really problematic if we have say 20 or more arguments
		//Becomes problematic to scale.
		//Solution is to use serialization
		Employee germanJohn = new Employee(john);
		germanJohn.address.country = "Germany";

		System.out.println(john);
		System.out.println(germanJohn);
		System.out.println(jane);

	}
}
