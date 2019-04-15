import java.util.*;
import java.io.*;
class PeerInformation{
    public final List capsule= new ArrayList<>();
    public BitSet pktsreceived;
    public PeerInformation(int ID){
        capsule.add(0,ID);       //Peer ID   :   String
        capsule.add(1,"127.0.0.1");       //Peer IP Address   :   String
        capsule.add(2,"0");       //Peer Port Number  :   String
        capsule.add(3,false);       //has some Data or File :   Boolean
        capsule.add(4,0);       //Corresponding Peer ID :   Atomic Integer
        capsule.add(5,false);       //Connection Requested or not   :   AtomicBoolean
    }
    public PeerInformation(String ID,String IP,String Port,boolean notEmpty){
        capsule.add(1,ID);       //Peer ID   :   String
        capsule.add(2,IP);       //Peer IP Address   :   String
        capsule.add(3,Port);       //Peer Port Number  :   String
        capsule.add(4,notEmpty);       //has some Data or File :   Boolean
        capsule.add(5,0);       //Corresponding Peer ID :   Atomic Integer
        capsule.add(6,false);       //Connection Requested or not   :   AtomicBoolean
        pktsreceived=new BitSet();  //BitSet type of packets received
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
        if (obj == null) {
            return false;
        }
        if (obj instanceof PeerInformation) {
            return (((PeerInformation) obj).capsule.get(0).equals (capsule.get(0)));
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