
public class Messages {
	private final byte _chokeType = 0;
	private final byte _unchokeType = 1;
	private final byte _interestedType = 2;
	private final byte _notInterestedType = 3;
	private final byte _haveType = 4;
	private final byte _bitfieldType = 5;
	private final byte _requestType = 6;
	private final byte _pieceType = 7;
	private byte[] _requestPayload;
	private byte[] _piecePayload;
	private byte[] _bitfieldPayload;
	private byte[] _havePayload;
			
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

	//Getters
	
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
	

}
