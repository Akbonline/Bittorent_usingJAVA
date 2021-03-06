package edu.ufl.cise.cnt5106c.log.modified;

import edu.ufl.cise.cnt5106c.conf.PeerInformation;
import java.io.*;
import java.util.*;



public class AssistLog{
    private Logger SessionID;
    public static List PeerIdToString(Collection<Integer> pID,Collection<PeerInformation> peers){       //Converts PeerInformation Object and Peer IDs
        StringBuilder peer1AsString = new StringBuilder("");        //PeerID
        StringBuilder peer2AsString = new StringBuilder("");        //Peer Information Object
        int check=1;                            //Flag to check if its the first peer connected
        List<String> peersAsString=new ArrayList();     //Stores both the above informations
        for(PeerInformation peer: peers){
            if(check==1){
                check = 0;
            }
            else{
                peer1AsString.append(", ");      
            }
            peer1AsString.append(peer.getPeerId());     //Converts the Peer ID as Integer
        }
        check=1;
        for(Integer ID:pID){
            if(check==1){
                check = 0;              //Sets false after visiting once
            }
            else{
                peer2AsString.append(", ");    //Adding a seperator between PeerIDs
            }
            peer2AsString.append(ID.intValue());
        }
        peersAsString.add(peer1AsString.toString());    //storing converted Peer ID in peersAsString
        peersAsString.add(peer2AsString.toString());    //storing converted PeerObject in peersAsString
        return peersAsString;
    }
    public static void configure(int ID)
            throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Properties properties = new Properties();
        properties.load(AssistLog.class.getResourceAsStream(PATH));
        Handler handler = new FileHandler ("log_peer_" + ID + ".log");
        Formatter formatter = (Formatter) Class.forName(properties.getProperty("java.util.logging.FileHandler.formatter")).newInstance();
        handler.setFormatter(formatter);
        handler.setLevel(Level.parse(properties.getProperty("java.util.logging.FileHandler.level")));
        newlog.SessionID.addHandler(handler);
    }

    AssistLog(Logger id){               //Constructor to initialize the PeedId
        SessionID=id;           //Session build up  
    }
    public static AssistLog getLogger () {      //Fetching the PeerInformation and the resourceAsStream
        return newlog;
    }
    static String PATH= "./logger.properties";  //Gotta change this later on    <----------------
    static AssistLog newlog=new AssistLog(Logger.getLogger("CNT5106C"));   
    static {
        InputStream in = null;
        try{
            in = AssistLog.class.getResourceAsStream(PATH);
            LogManager.getLogManager().readConfiguration(in);
        }
        catch (IOException e) {

            System.exit(1);
        }
        finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {}
            }
        }
    }
    public synchronized void info (String input) {  //log entry for fetching info
        SessionID.log (Level.INFO, input);
    }
    
    
}
