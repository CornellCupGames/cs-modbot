package connection;
public class ConnectionType {

	public static enum Type {
		NO_WAIT,
		BLOCK,
		TIMEOUT
	}

	Type type; 
	Integer field;

	private ConnectionType(Type type, Integer field) {
		this.type = type;
		this.field = field;
	}

	public static ConnectionType NoWait() {
		return new ConnectionType(Type.NO_WAIT, null);
	}

	public static ConnectionType Block() {
		return new ConnectionType(Type.BLOCK, null);
	}

	public static ConnectionType Timeout(int timeout) {
		return new ConnectionType(Type.TIMEOUT, timeout);
	}

	public Type getType() {
		return type;
	}

	public Integer getValue() {
		return field;
	}

}