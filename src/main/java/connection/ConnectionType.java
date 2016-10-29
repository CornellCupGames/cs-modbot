public class ConnectionType {

	public static enum Type {
		NO_WAIT,
		BLOCK,
		TIMEOUT
	}

	Type type; 
	Integer field;

	private ConnectionType(Type type, int field) {
		this.type = t;
		this.field = f;
	}

	public static ConnectionType NoWait() {
		return new ConnectionType(NO_WAIT, null);
	}

	public static ConnectionType Block() {
		return new ConnectionType(BLOCK, null);
	}

	public static ConnectionType Timeout(int timeout) {
		return new ConnectionType(TIMEOUT, timeout);
	}

	public Type getType() {
		return type;
	}

	public Integer getValue() {
		return field;
	}

}