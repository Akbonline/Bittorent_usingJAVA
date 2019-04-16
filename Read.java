import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

//TODO: Do we need to take care of "private boolean _handshakeReceived = false;"???

public class Read extends DataInputStream{
	//Constructor
	public Read(InputStream in) {
		super(in);
		// TODO Auto-generated constructor stub
	}
	
	//when handshake is not received yet.    TODO: Do we need to implement the condition where it has been received?
	public HandshakeMessage readData() throws IOException{
		HandshakeMessage handshakeMessage = new HandshakeMessage();
		handshakeMessage.readHandshakeMessage(this);
		return handshakeMessage;
	}
	

}
