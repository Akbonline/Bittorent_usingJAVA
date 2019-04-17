import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;
import java.util.Iterator;

public class startRemotePeers {

	public static void main(String[] args) {
		String peerInfoFile = PeerInformation._confPeerInfo;
        FileReader in = null;
        try {
            in = new FileReader (peerInfoFile);
            PeerInformation peerinfomation = new PeerInformation();
            peerinfomation.fetch(in);
            String filePath = System.getProperty("user.dir");
            Collection<PeerInformation> peers = peerinfomation.PeerList;
            for (Iterator<PeerInformation> iterator = peers.iterator(); iterator.hasNext();) {
            	//DEBUG
            	System.out.println("Start remote peer " + iterator.next().capsule.get(0) +  " at " + iterator.next().capsule.get(1));
                Runtime.getRuntime().exec ("ssh " + iterator.next().capsule.get(1) + " cd " + filePath + "; java peerProcess " + iterator.next().capsule.get(0));
            }
            System.out.println ("Remote peers have been started.");
        }
        catch (IOException e) {
            //LogHelper.getLogger().severe (e);
        }
        catch (ParseException e){
        	//LogHelper.getLogger().severe (e); 
        }
        
        try {
        	in.close();
        }
        catch (Exception e) {
        	//LogHelper.getLogger().severe (e);
        }
        
	}

}

