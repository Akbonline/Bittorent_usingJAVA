import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Write extends DataOutputStream {
	//Constructor
	public Write(OutputStream out) {
		super(out);
		// TODO Auto-generated constructor stub
	}
	
	public void writeObjectData(Object object) throws IOException{
		if (HandshakeMessage.class.isInstance(object)){
			((HandshakeMessage) object).writeHandshakeMessage(this);
		}
		else if (Messages.class.isInstance(object)){
			((Messages) object).writeMessage(this);
		}
		else {
			//DEBUG
			System.out.println("something is wrong with the output stream.");
		}
	}	
	
}
