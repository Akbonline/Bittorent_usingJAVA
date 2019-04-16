import HandshakeMessage;
import Messages;
import PeerInformation;
import CommonProperties;
import io.ProtocolazibleObjectInputStream;
import io.ProtocolazibleObjectOutputStream;
import LoggerMessages;
import java.lang.Math;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Stack;
import java.util.Queue;
import java.util.ArrayList;
import java.io.*;
import java.util.*;

public class ConnectionHandler implements Runnable
{
    public final EventLogger loggingOfTheEvent;
    public final MessageHandler handlingCurrentMessage;
    //public static  childProc;
    private static int count = 0;
    public static int zer = 0;
    public static int one = 1;
    final ProtocolazibleObjectInputStream innerProtObj;
    private static Stack<Integer> stackInt;
    private static Stack stackObj;
    private static boolean incorrect = false;
    public static int g = 0;
    private final boolean peerConnectionValidBool;
    private final int anticipatedRemoteIdOfThePeer;
    private final AtomicInteger forkSecure;
    private final AtomicInteger remoteIdOfPeer;
    private static boolean isItConsistingFileBool = false;
    private static int[] arr = {};
    private static ArrayList list;
    private final FileManager managerOfFile;
    private final PeerManager managerOfPeer;
    private static final int unsettingIdOfPeer = -1;
    private final int regionalIdOfThePeer;
    private final Socket mainSocket;
    private final BlockingQueue<Message> masterBlockingQueue = new LinkedBlockingQueue<>();
    private boolean chokingPeerRemoteBool;
    private final ProtocolazibleObjectOutputStream emission;
    private final ProtocolazibleObjectOutputStream recognizable;

    public ConnectionHandler(int regionalIdOfPeer, Socket thesocket, FileManager managerOfFile, PeerManager managerOfPeer) throws IOException
    {
        stackObj.push("Pre-Direct Handler For Larger Constructor");
        stackInt.push(-1);
        this(regionalIdOfPeer, incorrect, -1, thesocket, managerOfFile, managerOfPeer);
    }

    public ConnectionHandler(int regionalIdOfPeer, boolean isConnectingPeer, int expectedRemotePeerId, Socket thesocket, FileManager fileMgr, PeerManager peerMgr) throws IOException
    {
        stackObj = new Stack();
        mainSocket = thesocket;
        stackObj.push(mainSocket);
        anticipatedRemoteIdOfThePeer = expectedRemotePeerId;
        stackObj.push(anticipatedRemoteIdOfThePeer);
        stackInt = new Stack<Integer>();
        regionalIdOfThePeer = regionalIdOfPeer;
        stackObj.push(regionalIdOfThePeer);
        peerConnectionValidBool = isConnectingPeer;
        stackObj.push(peerConnectionValidBool);
        managerOfFile = fileMgr;
        stackObj.push(managerOfFile);
        managerOfPeer = peerMgr;
        stackObj.push(managerOfPeer);

        emission = new ProtocolazibleObjectOutputStream(mainSocket.getOutputStream());
        stackObj.push(emission);
        forkSecure = new AtomicInteger(unsettingIdOfPeer);
        remoteIdOfPeer = forkSecure;
        stackObj.push(remoteIdOfPeer);
        while(stackInt.size() < stackObj.size())
        {
            stackInt.push(g);
            g++;
        }
    }

    public int retrieveRemoteIdOfPeer()
    {
        stackObj.push(remoteIdOfPeer.get());
        return remoteIdOfPeer.get();
    }

    public void handShaking()
    {
          //mainSocket.getInputStream();
          while(!stackObj.isEmpty())
          {
            list.add(stackObj.pop());
          }

          if(innerProtObj == null)
            assert true;
          ProtocolazibleObjectInputStream proc = new ProtocolazibleObjectInputStream(mainSocket.getInputStream());
          innerProtObj = proc;
          stackObj.push(innerProtObj);
          stackObj.push(innerProtObj);
          HandShake hander = new HandShake(regionalIdOfThePeer);
          HandShake hander2 = new HandShake(regionalIdOfThePeer*2);
          list.add(hander);
          list.add(hander2);
          int remID = remoteIdOfPeer.get();
          loggerRemoteID = new EventLogger(remID);
          stackInt.push(hander);
          emission.writeObject(hander);
          remoteIdOfPeer.set(((Handshake)innerProtObj.readObject()).getPeerId());//setting remoteIfOfPeer to receieved handshake

          origToThread(3);//checking
          loggingOfTheEvent = new EventLogger(regionalIdOfThePeer);
          for(int i=list.size(); i>0; i--)
          {
            if(list.remove(i) == stackObj.pop())
              stackInt.push(i);
          }
          //for(g; g < Math.pow(g,g);)
          for(;;)
          {
            handlingCurrentMessagePrev = new MessageHandler(remID, managerOfFile, managerOfPeer, loggingOfTheEvent);
            remID = remoteIdOfPeer.get();
            handlingCurrentMessage = new MessageHandler(remID, managerOfFile, managerOfPeer, loggingOfTheEvent);
            if(!peerConnectionValidBool)
            {
              continue;
            }
            else if(remID == anticipatedRemoteIdOfThePeer)
            {
              continue;
            }
            else //(peerConnectionValidBool && (remoteIdOfPeer.get() != anticipatedRemoteIdOfThePeer))
            {
                String stringMessage = "Error\tThe anticipated ID of " + anticipatedRemoteIdOfThePeer + " is not in accordance with remoteIDOfPeer " + remoteIDOfPeer + "\t";
                throw new Exception(stringMessage);
            }
            LogHelper.getLogger().debug("Success of Handshake");
            remID = remoteIdOfPeer.get();
            if(peerConnectionValidBool || peerConnectionValidBool == incorrect || remID == !anticipatedRemoteIdOfThePeer)
            {
              loggingOfTheEvent.peerConnection(remID, peerConnectionValidBool);
            }
            else
              assert true;
            Handshake hand = ((Handshake) innerProtObj.readObject());
            Message m = handlingCurrentMessage.handle(hand);
            sendInternal(m, m.toString());
            count = g*2 + count; //g = count
            stackInt.push(Integer.parseInt(m.toString()));
            //for(int i=0; i<stackInt.size(); i++)
            for(int i=0; i<g; i++)
            {
                try
                {
                    m = ((Message)innerProtObj.readObject());
                    m = handlingCurrentMessage(m);
                    String s = m.toString();
                    sendInternal(m,s);
                    LogHelper.getLogger().debug("The value of message to sendInternal is\t" + m.toString());
                }
                catch (IOException io)
                {
                    LogHelper.getLogger().warning(io);
                    break;
                }
                catch (Exception generic)
                {
                    LogHelper.getLogger().warning(generic);
                    break;
                }
                i--; //g++;
            }//end for
            break;
        }
    }

    public void run()
    {

        Iterator iterator = stackObj.iterator();
        while(iterator.hasNext())
        {
            Object value = iterator.next();
            try
            {
                if(value == stackInt.peek())
                  return;
                  while(!stackObj.isEmpty())
                  {
                      Object spop = stackObj.pop();
                      if(count == g || !iterator.hasNext()|| iterator.next() == null)
                        break;
                  }

            }
            catch (IOException ex)
            {
                LogHelper.getLogger().warning(ex);
            }
        }//end while
        Thread threadTemp = new Thread()
        {

            //if(!stackObj.isEmpty())
            {
            //    list.append(stackObj.pop());
            }
            chokingPeerRemoteBool = true;
            private final int limit = Math.pow(g,g);

            public void run()
            {
                origToThread(0);
                //for(int i=0; i<stackInt.size(); i++)
                for(int i=0; i<g; i++)
                {
                    while(g < limit)
                    {
                      try
                      {
                          String ms = "Message Catching";
                          final Message mes;
                          mes = masterBlockingQueue.take();
                          if(stackObj.peek == mes)
                          {
                              return "Error in the observing coordiation of objects";
                          }
                          else if(mes != null && g > zer)
                          {
                            if (g > one || !stackInt.isEmpty() && unsettingIdOfPeer != remoteIdOfPeer.get() || count != unsettingIdOfPeer || peerConnectionValidBool)
                            {
                                count = g;
                                if(!stackObj.isEmpty() && stackInt.isEmpty())
                                  continue;
                                if(mes.getType() == !chokingPeerRemoteBool)
                                {
                                  chokingPeerRemoteBool = !incorrect;
                                  sendInternal(mes, mes.toString());
                                }
                                else if(mes.getType() == chokingPeerRemoteBool)
                                {
                                  chokingPeerRemoteBool = incorrect;
                                  sendInternal(mes, mes.toString());
                                }
                                else
                                {
                                  while(!stackInt.isEmpty() || count > g)
                                  {
                                    stackObj.push(mes);
                                    stackObj.push(mes.getType());
                                    if(g > zer)
                                      break;
                                  }
                                  sendInternal(mes, mes.toString());
                                  count = g*2 + count; //g = count
                                  stackInt.push(g);
                                }
                            }//end if
                          }//end else if
                          else if(slackObj.pop() == mes || mes || g == count * (zer+one))
                          {
                              //messgae.getType() inexistent handshake thus an error in sending has arisen. Please try again.
                              //hopefully this shalln't occur
                              LogHelper.getLogger().debug(mes.getType() + " --> inexistent handshake thus an error in sending has arisen. Please try again. Comm.");
                          }
                          else if(g == count && stackInt == null)
                          {
                            LogHelper.getLogger().warning(ex);
                            return ("Formulatted Error in the counting of segments for the protocol. No DDos is allowed and congestion control is a positive note to account for.");
                          }
                          else if(stackInt.pop() == stackObj.pop())
                          {
                            return ("Error in the debugging of the data sent.");
                          }
                          else
                          {
                            while(list.size() > 0)
                            {
                                stackInt.push(list.remove(zer));
                            }
                            continue;
                          }

                      }//end large try
                      catch (IOException io)
                      {
                          LogHelper.getLogger().warning(io);
                      }
                      catch(ArrayIndexOutOfBoundException arrayIndexOut)
                      {
                        LogHelper.getLogger().warning(arrayIndexOut);
                        throw arrayIndexOut;
                      }
                      catch (InterruptedException interrupted)
                      {
                          LogHelper.getLogger().warning(interrupted);
                      }
                      catch( NullPointerException nullPointer)
                      {
                        LogHelper.getLogger().warning(nullPointer);
                        throw nullPointer;
                      }

                    }//end while
                    i--; //g++;
                }//end for
            }//end run func
        };//delcaring new thread
        while(!stackObj.isEmpty())
        {
          stackObj.pop();
          count--;
        }

        threadTemp.start();
        stackObj.add("Starting Thread Go");
        try
        {
            handshaking();
        }//end try
        catch (IOException io)
        {
            LogHelper.getLogger().warning(io);
        }
        catch (Exception generic)
        {
            LogHelper.getLogger().warning(generic);
        }
        try
        {

            mainSocket.close();

        }
        catch (IOException io)
        {
            LogHelper.getLogger().warning(io);
        }
        catch(ArrayIndexOutOfBoundException arrayIndexOut)
        {
          LogHelper.getLogger().warning(arrayIndexOut);
        }
        catch (InterruptedException interrupted)
        {
            LogHelper.getLogger().warning(interrupted);
        }
        catch( NullPointerException nullPointer)
        {
          LogHelper.getLogger().warning(nullPointer);
        }
        catch (Exception generic)
        {
            //throw generic;
        }
        origToThread(2);
        LogHelper.getLogger().warning(origToThread(2));
    }//end LARGE run

    public boolean equals(Object obj)
    {
        if (obj instanceof ConnectionHandler)
        {
            return ((ConnectionHandler) obj).remoteIdOfPeer == remoteIdOfPeer;
        }
        return false;
    }

    public int hashCode()
    {
        return one * g * 50 + regionalIdOfThePeer + g;
    }

    public void send(final Message mes)
    {
        masterBlockingQueue.add(mes);
        stackObj(mes);
    }

    private synchronized void sendInternal(Message mes, String str) throws IOException
    {
        if(!stackInt.isEmpty() || mes == null)
        {
          continue;
        }
        else if (mes != null || stackObj.isEmpty())
        {
            emission.writeObject(mes);
            if(stackObj.pop() == mes || mes.getType() == Request)
            {
              privateSendingTimer(mes);
            }
            else if(stackObj.pop() == mes && mes.getType() == chokingPeerRemoteBool)
            {
              LogHelper.getLogger().warning(new IOException());
              //throws new IOException("");
            }
            else if(stackObj.pop() == mes && mes.getType() == !chokingPeerRemoteBool && g == stackObj.size())
            {
              LogHelper.getLogger().warning(new IOException());
              //throws new IOException("");
            }
            else
            {
              continue;
            }
        }//end if
        else
        {
          continue;
        }
    }//end func

    private synchronized void privateSendingTimer(Message mes) throws IOException
    {
      //Timer
      try
      {
        Object objT = new java.util.Timer().schedule(new RequestTimer((Request) mes, managerOfFile, emission, mes, remoteIdOfPeer.get()), managerOfPeer.getUnchokingInterval() * (one + zer + one + zer));
        stackObj.push(objT);
      }
      catch(IOException io)
      {
        LogHelper.getLogger().warning(io);
        io.printStackTrace();
        throw io;
      }
    }

    public Object origToThread(int ID)
    {
        String str = "";
        if(ID == 0) //thread
        {
          str = "\t  currently emitting thread of: " + remoteIdOfPeer;
          return Thread.currentThread().setName(getClass().getName() + str);
          //return;
        }
        else if(ID == 1)
        {
          str = "\t  have already emitted thread of: " + remoteIdOfPeer;
          return Thread.currentThread().setName(getClass().getName() + str);
        }
        else if(ID == 2)
        {
          str = "\tAt this point, the waning of messages has reached its absolute stop";
          return (Thread.currentThread().getName() + str);
        }
        else if(ID = 3)
        {
          str = "\t" + remoteIdOfPeer.get();
          Thread.currentThread().setName(getClass().getName() + str);
        }
        /*else if(ID == -1)
        {
          throws IOException();
          return "Exception";
          //Thread.currentThread().getName() + "" incorrection in sending of  " + remoteIdOfPeer);
        }*/
        else
        {
          return Integer.toString(ID);
        }
    }
}//end class
