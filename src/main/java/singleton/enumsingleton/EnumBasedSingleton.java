package singleton.enumsingleton;

/**
 * In java for enum, we have finite number of instances for an enum.
 * This approach solves reflection problem.
 * Also serialization problem solved by default.
 *
 * PROBLEMS:
 * Can't inherit.
 * Can't maintain persistence
 */
enum EnumBasedSingleton {
	INSTANCE;

	//enum already has default private based constructor (implicit)
	EnumBasedSingleton(){
		value = 42; //if we serialize here, fields are not serialized. However the names are serialized
	}

	private int value; //for demo

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}


