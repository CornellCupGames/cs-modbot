package connection;
import packet.Packet;

public interface ConnectionECE {

	public Packet transaction(Packet p, ConnectionType c, boolean receive_packet);

}