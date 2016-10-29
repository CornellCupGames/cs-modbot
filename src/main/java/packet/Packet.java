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
		Command c = COMMAND_MAP.get(type).getConstructor(String[].class).newInstance(values);
		commands.add(c);
		commandTypes.add(type);
	}

}