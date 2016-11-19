package modbotsim;
import connection.ConnectionECE;
import connection.ConnectionType;
import packet.Packet;
import packet.Packet.CommandType;
import packet.commands.*;
import java.util.List;

public class ModbotSimConnection implements ConnectionECE {

	Modbot modbot;

	public ModbotSimConnection() {
		modbot = new Modbot(0, 0);
	}

	public Packet transaction(Packet p, ConnectionType c, boolean receive_packet) {
		List<Command> commands = p.commands();
		List<Packet.CommandType> commandTypes = p.commandTypes();

		Packet returnPacket = new Packet();

		for (int i = 0; i < commandTypes.size(); i++) {
			switch (commandTypes.get(i)) {
				case PING:
					ping(returnPacket);
					break;
				case SET_MOTOR_SPEED:
					set_motor_speed((SetMotorSpeed) commands.get(i), returnPacket);
					break;
				default:
					break;
			}
		}
		if (returnPacket.empty()) {
			return null;
		}
		return returnPacket;
	}

	private void ping(Packet returnPacket) {
		System.out.println("adding");
		returnPacket.addCommand(CommandType.PING);
	}

	private void set_motor_speed(SetMotorSpeed sms, Packet returnPacket) {
		int motorid = sms.motor_id();
		double motorspeed = sms.motor_speed();
		modbot.set_velocity(motorid, motorspeed);
		returnPacket.addCommand(CommandType.PING);
	}
}