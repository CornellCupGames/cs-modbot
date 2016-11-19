package packet;
import java.lang.reflect.InvocationTargetException;
import packet.commands.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Packet {

	public static enum CommandType {
		PING, 
		SET_MOTOR_SPEED
	}

	private static int global_id;
	public static Map<CommandType, Class<? extends Command>> COMMAND_MAP;

	static {
		COMMAND_MAP = new HashMap<CommandType, Class<? extends Command>>();
		COMMAND_MAP.put(CommandType.PING, Ping.class);
		COMMAND_MAP.put(CommandType.SET_MOTOR_SPEED, SetMotorSpeed.class);
	}

	int id;
	List<Command> commands;
	List<CommandType> commandTypes;

	public Packet() {
		id = global_id++;
		commands = new ArrayList<Command>();
		commandTypes = new ArrayList<CommandType>();
	}

	public void addCommand(CommandType type, String... values) {
		try {
			Command c = COMMAND_MAP.get(type).getConstructor().newInstance();
			c.addValues(values);
			commands.add(c);
			commandTypes.add(type);
		} catch (NoSuchMethodException e) {
			System.err.println("Caught NoSuchMethodException: " + e.getMessage());
		} catch (InstantiationException e) {
			System.err.println("Caught InstantiationException: " + e.getMessage());
		} catch (IllegalAccessException e) {
			System.err.println("Caught IllegalAccessException: " + e.getMessage());
		} catch (InvocationTargetException e) {
			System.err.println("Caught InvocationTargetException: " + e.getMessage());
		}
	}

	public List<Command> commands() {
		return commands;
	}

	public List<CommandType> commandTypes() {
		return commandTypes;
	}

	public boolean empty() {
		return commands.size() == 0;
	}
	
}