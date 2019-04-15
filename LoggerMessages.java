import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LoggerMessages {
	private int peerID;   //Level of access may need to be changed.
	
	public LoggerMessages (int peerID) {
		this.peerID = peerID;
	}
	
	//TCP Connection:
	
	//Whenever a peer makes a TCP connection to other peer
	public String logTCPConnection (int otherPeer) 
	{
		String message = getSystemTime() + ": Peer " + this.peerID + " makes a connection to Peer " + otherPeer + ".";
		//_logHelper.info(message);  //TODO: Change based on Akshat's code
		return message;
	}
	
	//Whenever a peer is connected form another peer
	public String logTCPConnected (int otherPeer) 
	{
		String message = getSystemTime() + ": Peer " + this.peerID + " is connected from Peer " + otherPeer + ".";
		//_logHelper.info(message);  //TODO: Change based on Akshat's code
		return message;
	}
	
	//Change of preferred neighbors:
	//Whenever a peer changes its preferred neighbors
	public String logChangeOfPrefNeighbors(String listPrefNeighbors)
	{ //assuming that the input is a list of preferred neighbors being separated by comma ','. 
		String message = getSystemTime() + ": Peer " + this.peerID + " has the preferred neighbors " + listPrefNeighbors + ".";
		//_logHelper.info(message);  //TODO: Change based on Akshat's code
		return message;
	}
	
	//Change of optimistically unchoked neighbor
	//Whenever a peer changes its optimistically unchoked neighbor
	public String logOptUnchokNeighbor (int optUnchokNeighbor) 
	{
		String message = getSystemTime() + ": Peer " + this.peerID + " has the optimistically unchoked neighbor " + optUnchokNeighbor + ".";
		//_logHelper.info(message);  //TODO: Change based on Akshat's code
		return message;
	}
	
	//Unchoking
	//Whenever a peer is unchoked by a neighbor
	//When a peer receives a unchoking message from a neighbor
	public String logUnchoked (int neighborID) 
	{
		String message = getSystemTime() + ": Peer " + this.peerID + " is unchoked by " + neighborID + ".";
		//_logHelper.info(message);  //TODO: Change based on Akshat's code
		return message;
	}
	
	//Choking
	//Whenever a peer is unchoked by a neighbor
	//When a peer receives a unchoking message from a neighbor
	public String logChoked (int neighborID) 
	{
		String message = getSystemTime() + ": Peer " + this.peerID + " is choked by " + neighborID + ".";
		//_logHelper.info(message);  //TODO: Change based on Akshat's code
		return message;
	}
	
	//Receiving "have" message
	//Whenever a peer receives a "have" message
	public String logHave(int senderID, int index) 
	{
		String message = getSystemTime() + ": Peer " + this.peerID + " received the 'have' message from " + senderID + " for the piece " + index + ".";
		//_logHelper.info(message);  //TODO: Change based on Akshat's code
		return message;
		
	}
	
	//Receiving "interested" message
	//Whenever a peer receives an 'interested' message
	public String logInterested(int senderID) 
	{
		String message = getSystemTime() + ": Peer " + this.peerID + " received the 'interetsed' message from " + senderID + ".";
		//_logHelper.info(message);  //TODO: Change based on Akshat's code
		return message;
		
	}
	
    //Receiving "not interested" message
	//Whenever a peer receives an 'not interested' message
	public String logNOTInterested(int senderID) 
	{
		String message = getSystemTime() + ": Peer " + this.peerID + " received the 'not interetsed' message from " + senderID + ".";
		//_logHelper.info(message);  //TODO: Change based on Akshat's code
		return message;
		
	}

    //Downloading
    //Whenever a peer finishes downloading a piece.
	public String logDownload(int senderID, int index, int totalNumber) 
	{
		String message = getSystemTime() + ": Peer " + this.peerID + " has downloaded the piece " + index + " from " + senderID + ". Now the number of pieces it has is " + totalNumber + ".";
		//_logHelper.info(message);  //TODO: Change based on Akshat's code
		return message;
	}
	
    //Completion
    //Whenever a peer downloads the complete file
    public String logComplete()  
	{
		String message = getSystemTime() + ": Peer " + this.peerID + " has downloaded the complete file.";
		//_logHelper.info(message);  //TODO: Change based on Akshat's code
		return message;
	}

	//Formatting the system time as YYYY/MM/DD_HH:mm:ss
	public String getSystemTime () 
	{
		//Credit goes to: https://stackoverflow.com/questions/5175728/how-to-get-the-current-date-time-in-java
		String timeStamp = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss").format(Calendar.getInstance().getTime()); 
		return timeStamp;
	}

}
