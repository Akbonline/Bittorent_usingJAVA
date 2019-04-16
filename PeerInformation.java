import java.util.*;
import java.io.*;
import java.text.*;
class PeerInformation{
    public final List capsule= new ArrayList<>();
    public final Collection<PeerInformation> PeerList= new LinkedList();    //PeerInformation type list
    public final List pcapsule=new ArrayList<>();   //Contains Configuration file and Comment Character
    public BitSet pktsreceived;
    public final List prop=new ArrayList();
    public PeerInformation(int ID){
        capsule.add(0,ID);       //Peer ID   :   String
        capsule.add(1,"127.0.0.1");       //Peer IP Address   :   String
        capsule.add(2,"0");       //Peer Port Number  :   String
        capsule.add(3,false);       //has some Data or File :   Boolean
        capsule.add(4,0);       //Corresponding Peer ID :   Atomic Integer
        capsule.add(5,false);       //Connection Requested or not   :   AtomicBoolean
        pcapsule.add(0,"PeerInfo.cfg"); //Adding the configuration file name
        pcapsule.add(1,"#");    //Comment Character
    }
    public PeerInformation(String ID,String IP,String Port,boolean notEmpty){
        capsule.add(0,ID);       //Peer ID   :   String
        capsule.add(1,IP);       //Peer IP Address   :   String
        capsule.add(2,Port);       //Peer Port Number  :   String
        capsule.add(3,notEmpty);       //has some Data or File :   Boolean
        capsule.add(4,0);       //Corresponding Peer ID :   Atomic Integer
        capsule.add(5,false);       //Connection Requested or not   :   AtomicBoolean
        pktsreceived=new BitSet();  //BitSet type of packets received
        pcapsule.add(0,"PeerInfo.cfg"); //Adding the configuration file name
        pcapsule.add(1,"#");    //Comment Character
    }
    public Object getPeerInfo(int i){       //Returns the Peerinformation requested
        return capsule.get(i);      //return type=Object
    }
    public void connectionAvailable(Boolean value){
        capsule.add(6,value);           //Manually setting the connectionAvailable flag
    }
    public int setcount(int bkey){          //Finding out the Set values in an Binary number
        if(bkey==0){
            return 0;
        }
        else{
            return(bkey+1)+setcount(bkey>>1);
        }
    }

    public void fetch(Reader inputFile){    // Input Buffered Reader
        BufferedReader input=new BufferedReader(inputFile);
        String line;
        int index=0;
        boolean notEmpty=false;
        while((line = input.readLine()) != null){       //Until there's data in the input buffer
            if(line.length()<=0){
                if(line.startsWith((String)(pcapsule.get(1)))){     //if the current line starts with # (Comment)
                    continue;
                }
            }
            else{
                String statement[]=line.split("\\s+");      //If not keep spliting 
                if(statement.length!=4){    
                throw new ParseException(line,index);   //Error handling for invalid inputs
                }
            
            if(statement[3].trim().compareTo("1")==0){      //If there's still some data in the input buffer
                notEmpty=true;
            }
            
            PeerList.add(new PeerInformation(statement[0].trim(),statement[1].trim(),statement[2].trim(),notEmpty));//Create a new PeerInfo candidate
            index++;
            }
        }
    }
    
    @Override
    public int hashCode() {             //It has to be the same in the receiving side
        int key=(Integer)(this.capsule.get(0));     // Get the key
        Integer bkey=Integer.parseInt(Integer.toBinaryString(key));       // Convert into binary        
        int n=setcount(bkey);       //counts the number of 1's in the binary
        key=key*n + Objects.hashCode(this.capsule.get(0)); //multiplies that with the hashcode of the pId
        return key;
    }
    @Override
    public boolean equals (Object obj) {
        if (obj == null) {          //If comparison value is empty
            return false;
        }
        if (obj instanceof PeerInformation) {
            return (((PeerInformation) obj).capsule.get(0).equals (capsule.get(0))); //Peer Candidate compare
        }
        return false;
    }
    public static Collection<Integer> SetIdAs (Collection<PeerInformation> peers) {
        Set<Integer> ids = new HashSet<>();
        for (PeerInformation peer : peers) {
            ids.add((Integer) peer.getPeerInfo(1));
        }
        return ids;
    }
}