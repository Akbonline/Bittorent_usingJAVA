import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Messages {
	//Types of messages
	private final byte _chokeType = (byte) 0;
	private final byte _unchokeType = (byte) 1;
	private final byte _interestedType = (byte) 2;
	private final byte _notInterestedType = (byte) 3;
	private final byte _haveType = (byte) 4;
	private final byte _bitfieldType = (byte) 5;
	private final byte _requestType = (byte) 6;
	private final byte _pieceType = (byte) 7;
	private byte[] _requestPayload;
	private byte[] _piecePayload;
	private byte[] _bitfieldPayload;
	private byte[] _havePayload;
	//Message
	private byte type;
	private byte[] payload;
	private int messageLength; 
			
	//Constructor
	Messages(byte type, byte[] payload) throws ClassNotFoundException {
		this.type = type;
		this.payload = payload;
		defineMessageLength(); //TODO: when is it needed?
		defineMessage();
	}
	
	//Defining the length of the message
	public void defineMessageLength(){
		if (this.getPayload() == null) {
			this.setMessageLength(0);
		} else {  //length of the type which is 1 + length of the payload
			this.setMessageLength(this.getPayload().length + 1);
		}
	}
	
	//Defining what is the message type
	public void defineMessage() throws ClassNotFoundException{
		byte t = this.getType();
		if (Byte.compare(t, (byte) 0) == 0) {
			choke();}
		else if (Byte.compare(t, (byte) 1) == 0) {
			unchoke();}
		else if (Byte.compare(t, (byte) 2) == 0) {
			interested();}
		else if (Byte.compare(t, (byte) 3) == 0) {
			notInterested();}
		else if (Byte.compare(t, (byte) 4) == 0) {
			have(new byte[this.getPayload().length]);}
		else if (Byte.compare(t, (byte) 5) == 0) {
			bitfield(new byte[this.getPayload().length]);}
		else if (Byte.compare(t, (byte) 6) == 0) {
			request(new byte[this.getPayload().length]);}
		else if (Byte.compare(t, (byte) 7) == 0) {
			piece(new byte[this.getPayload().length]);}
		else {
			throw new ClassNotFoundException ("type is not valid: " + Byte.toString(t));
		}
	}
			
	//Unchoke
	public void unchoke(){
		byte unchokeType = this._unchokeType;
	}
	
	//Choke
	public void choke(){
		byte chokeType = this._chokeType;
	}
	
	//Have
	public void have(byte[] payload){
		byte haveType = this._haveType;
		this.set_havePayload(payload);
	}
	
	//Interested
	public void interested(){
		byte interestedType = this._interestedType;
	}
	
	//Interested
	public void notInterested(){
		byte notInterestedType = this._notInterestedType;
	}
	
	//Bitfield
	public void bitfield(byte[] bitfield) {
		byte bitfieldType = this._bitfieldType;
		this.set_bitfieldPayload(bitfield);
	}
	
	//TODO: Bitfield constructor with bitset as input, if needed
	
	
	//Request
	public void request(byte[] payload){
		byte requestType = this._requestType;
		this.set_requestPayload(payload);
	}
	
	//Piece
	public void piece(byte[] payload){
		byte pieceType = this._pieceType;
		this.set_piecePayload(payload);
	}

	//TODO: Adding the method to get the byte number and returns which type it is. (Type.java)
	//TODO: Implementing Request (int pieceIdx), if necessary (Request.java)
	//TODO: Implementing Have (int pieceIdx), if necessary (Have.java)
	//TODO: Implement piece.java (IMPORTANT)

	//Reading Message //TODO: Needs to be re-structured
	public void readMessage (DataInputStream in) throws IOException{
        if ((this.getPayload() != null) && (this.getPayload().length) > 0) {
            in.readFully(this.getPayload(), 0, this.getPayload().length);
        }
    }
	
	//Writing Message //TODO: Needs to be re-structured
	public void writeMessage (DataOutputStream out) throws IOException{
        out.writeInt (this.getMessageLength());
        out.writeByte (this.getType());
        if ((this.getPayload() != null) && (this.getPayload().length > 0)) {
            out.write (this.getPayload(), 0, this.getPayload().length);
        }
    }
	
	//Setters and Getters
	
	public byte get_chokeType() {
		return _chokeType;
	}
	
	public byte get_unchokeType() {
		return _unchokeType;
	}

	public byte get_interestedType() {
		return _interestedType;
	}

	public byte get_notInterestedType() {
		return _notInterestedType;
	}

	public byte get_haveType() {
		return _haveType;
	}

	public byte get_bitfieldType() {
		return _bitfieldType;
	}

	public byte get_requestType() {
		return _requestType;
	}

	public byte get_pieceType() {
		return _pieceType;
	}

	public byte[] get_requestPayload() {
		return _requestPayload;
	}

	public void set_requestPayload(byte[] _requestPayload) {
		this._requestPayload = _requestPayload;
	}

	public byte[] get_piecePayload() {
		return _piecePayload;
	}

	public void set_piecePayload(byte[] _piecePayload) {
		this._piecePayload = _piecePayload;
	}

	public byte[] get_bitfieldPayload() {
		return _bitfieldPayload;
	}

	public void set_bitfieldPayload(byte[] _bitfieldPayload) {
		this._bitfieldPayload = _bitfieldPayload;
	}

	public byte[] get_havePayload() {
		return _havePayload;
	}

	public void set_havePayload(byte[] _havePayload) {
		this._havePayload = _havePayload;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public byte[] getPayload() {
		return payload;
	}

	public void setPayload(byte[] payload) {
		this.payload = payload;
	}

	public int getMessageLength() {
		return messageLength;
	}

	public void setMessageLength(int i) {
		this.messageLength = i;
	}
}
