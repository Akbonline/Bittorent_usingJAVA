import edu.ufl.cise.cnt5106c.LoggerMessages;
import java.util.*;
import java.util.ArrayList;

import java.math.BigInteger;

public class PartsRequested
{
    public BigInteger timeOut;
    public ArrayList<Integer> partsNeeded;
    public BitSet partsNeededBit;
    public ArrayList<Integer> zeros;
    public Stack<Integer> localStack;
    public int randomIDVal;
    //unchokes preferred neighbors via 'unchoke' messages & a receipt of a requestedPart
    public PartsRequested(int quantityOfParts, long intervalForUnchoking)
    {
      timeOut = BigInteger.valueOf(intervalForUnchoking*2);
      partsNeeded = new ArrayList<Integer>(quantityOfParts);
      partsNeededBit = new BitSet(quantityOfParts);
      zeros = new ArrayList<Integer>(quantityOfParts);
      for(int i=0; i<quantityOfParts; i++)
        zeros.set(i,0);
    }

    public ArrayList<Integer> convertBitSetToArrayList(BitSet bs)
    {
      int[] arr = new int[bs.size() / 32];
      for (int i = 0; i < arr.length; i++)
        for (int j = 0; j < 32; j++)
          if (bs.get(i * 32 + j))
            arr[i] |= 1 << j;
      ArrayList<Integer> list = new ArrayList<Integer>(arr.length);
      for(int i = 0; i < arr.length; i++)
        list.set(i , arr[i]);
      return list;
    }

    /*
    Parts between peers are randomly chosen. A peer Y sends a request message to a peer Z, which gives the part.
    Continues until
      - Z is completely out of pieces that are of interest to Y
      - or Z chokes Y.
    */
    synchronized int retreiveRequestedPartID(BitSet cset)
    {
       boolean answer = retreiveRequestedPartIDMain(cset);
       if(!answer)
          return -1;
       return randomIDVal;
    }

    //postcondition: returns RequestPartID as an integer
    //if there are no requested parts in existent (either have been requested or none needed to do)
    //return -1 for above case
    //If be the case that a peer has a complete file, then, instead of comparing download rates, it finds which neighbors that have an interest in the data in an random fashion.
    synchronized boolean retreiveRequestedPartIDMain(BitSet b)
    {
      //first, make bit of cset 0 if partsNeeded has 1 at this index
      Timer timer = new Timer();
      b.andNot(partsNeededBit);
      ArrayList<Integer> cset = convertBitSetToArrayList(b);
      /*
      for(int i=0; i<partsNeeded.size(); i++)
      {
        if(partsNeeded.get(i) == 1)
           cset.set(i,0);
      }
      */
      //if cset is empty, return -1 for no ID to be found
      if(cset.equals(zeros) || b.isEmpty())
      {
        timer.cancel();
        return false;
      }
      //change random set index from partsNeeded
      randomIDVal = chooseRandomElemFromGivenSetList(cset, b);
      partsNeeded.set( randomIDVal, 1);
      partsNeededBit.set(randomIDVal);
      //Now, we use our timeOut Time to be able to request this part

      // creating timer task, timer
      //timer = new Timer();
      // scheduling the task
      long timeOutVal = timeOut.longValue();
      timer.schedule(new timerTask(), timeOutVal);
      return true;
    }

    public class timerTask extends TimerTask
    {
      private final Object lock = new Object();
	    public void run()
	    {
		    synchronized (lock)
		    {
			    LogHelper.getLogger().debug(revertToZeroes(randomIDVal) + "" + randomIDVal + "\n");
                //clears the part
		    }
	    }
	}

    synchronized String revertToZeroes(int valID)//revert values to zero
    {
      ArrayList<Integer> a = convertBitSetToArrayList(partsNeededBit);
      for(int i=0; i<a.size(); i++)
      {
        a.set(i,0);
      }
      partsNeededBit.clear(valID);
      String s = "The Req Parts Have Been Reset For part Of ID \t";
      return s;
    }

    //postcondition: Make an ordered representation of the set elem & then randomly choose one
    public int chooseRandomElemFromGivenSetList(ArrayList<Integer> b, BitSet bs)
    {
        if(!b.equals(zeros) || !bs.isEmpty())
        {
            return b.get (  (int)( Math.random()*(b.size()-1) )  );
        }
        else
        {
          String str = "No element is existent in this set of elements. Problem encountered.";
          //LogHelper.getLogger().debug(str);
          throw new RuntimeException (str);
        }
    }

}
