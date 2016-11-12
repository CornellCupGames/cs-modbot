package connection;
import packet.Packet;

public interface Connection {

	public Packet transaction(Packet p, ConnectionType c, boolean receive_packet);

}