package prototype.serialization;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

class Foo implements Serializable{
	public int stuff;
	public String whatever;

	public Foo(int stuff, String whatever) {
		this.stuff = stuff;
		this.whatever = whatever;
	}

	@Override
	public String toString() {
		return "Foo{" +
				"stuff=" + stuff +
				", whatever='" + whatever + '\'' +
				'}';
	}
}
public class Demo {
	public static void main(String[] args) {
		Foo foo = new Foo(123, "whatever information");
		//serialize<->deserialize
		//much more powerful than copy-constructors
		//handles composition or references
		Foo foo2 = SerializationUtils.roundtrip(foo);
		foo2.whatever = "abcd";

		System.out.println(foo);
		System.out.println(foo2);
	}
}
