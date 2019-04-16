import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ProtocolException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

public class HandshakeMessage {
	//Variables
	private final String _Header = "P2PFILESHARINGPROJ";
	private final byte[] zero = new byte[10];
	private byte[] peerID;
	
	//Constructors
	//Constructor with peerID as an array byte for peerID
	public HandshakeMessage (byte[] peerId) {
		if (peerId.length > 4) {
            throw new ArrayIndexOutOfBoundsException("peerId is more than 4-byte length.");
        } else {
        	this.setPeerID(peerID);
        }	
    }
	
	//Constructor with peerID as an int for peerID	//TODO: Needs to be re-structured
	public HandshakeMessage(int peerID){
		this.setPeerID(ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt(peerID).array());
	}
	
	//Constructor with no input
	public HandshakeMessage(){
		
	}
	
	//Reading handshake messages   //TODO: Needs to be re-structured
    public void readHandshakeMessage (DataInputStream in) throws IOException {
    	byte[] header = new byte[this._Header.length()];
        if (in.read(header, 0, this._Header.length()) < this._Header.length()) {
            throw new ProtocolException ("There is a problem with header.");
        }
        if (!this._Header.equals (new String(header, "US-ASCII"))) {
            throw new ProtocolException ("There is a problem with header.");
        }
        if (in.read(this.zero, 0, this.zero.length) <  this.zero.length) {
            throw new ProtocolException ("There is a problem with zero bits.");
        }
        if (in.read(this.getPeerID(), 0, this.getPeerID().length) <  this.getPeerID().length) {
            throw new ProtocolException ("There is a problem with peer ID.");
        }
    }	
	
	//Writing handshake messages   //TODO: Needs to be re-structured
    public void writeHandshakeMessage(DataOutputStream out) throws IOException {
        byte[] peerId = this._Header.getBytes(Charset.forName("US-ASCII"));
        if (peerId.length > this._Header.length()) {
            throw new IOException("header length does not match with the peerID length");
        }
        out.write (peerId, 0, peerId.length);
        out.write(this.zero, 0, this.zero.length);
        out.write(this.peerID, 0, this.peerID.length);
    }
	
	//Setters and Getters
	public byte[] getPeerID() {
		return peerID;
	}
	//TODO: Needs to be re-structured
	public int getIntPeerID(){
		int peerID = ByteBuffer.wrap(this.getPeerID()).order(ByteOrder.BIG_ENDIAN).getInt();
		return peerID;
	}

	public void setPeerID(byte[] peerID) {
		this.peerID = peerID;
	}
}
