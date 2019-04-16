package main.java.edu.ufl.cise.cnt5106c.modified;

import java.util.*;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Element;
import javax.tools.FileObject;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileManager.Location;

import java.io.*;
class Receiver{
    private final File file;    //our File at the receiving end
    private final File folder;  // Destination folder to store the file chunks
    int no_of_chunks;
    boolean chunkExists;
    private static final String Path_toFolder="files/chunks/";
    Receiver(int pID,String name){      //Initializing the files
        folder= new File("./peer_"+pID+name);           // Setting up the location in the present working directory to create a new folder to store the fragmented incoming files
        folder.mkdirs();        //making a directory in the location setup 
        file=new File(folder.getParent() + "/../" + name);      //Making a new file
        no_of_chunks=1;
        chunkExists=false;
    } 
    

    
    public void operate(FileOutputStream outStrm,byte[] part){
        outStrm.write(part);
        outStrm.flush();
        outStrm.close();
    }
    public byte[][] chunksToBytes(){        //Converting the incoming chucks
        File[] chunks = folder.listFiles(new FilenameFilter(){           //We make a file array
        
            @Override
            public boolean accept(File dir, String name) {              
                return true;
            }
        });
        File first=new File(folder.getAbsolutePath()+"/"+1);
        if(no_of_chunks==1){
            chunkExists=true;
        }
        while(!chunkExists){
            Filer chunkcontrol=new Filer(){         //Checks for Buffer space
        
            @Override
            public FileObject getResource(Location location, CharSequence pkg, CharSequence relativeName) throws IOException {  //For Getting pkgs
                return extracted();
            }

            private FileObject extracted() {    
                return null;
            }

            @Override
            public JavaFileObject createSourceFile(CharSequence name, Element... originatingElements)
                    throws IOException {
                return null;
            }

            @Override
            public FileObject createResource(Location location, CharSequence pkg, CharSequence relativeName,
                    Element... originatingElements) throws IOException {
                return extracted();
            }
        
            @Override
            public JavaFileObject createClassFile(CharSequence name, Element... originatingElements) throws IOException {
                return null;
            }
        };}
        byte[][] bytes = new byte[chunks.length][getByteArrayFromFile(first).length];    // we make a byte array(2d) to store the (length of the file: length of the chunk)
        for(File file: chunks){                     //For each file part that exists in the directory
            File temp=new File(folder.getAbsoluteFile()+"/"+chunks);    //storing the temporary file object to store the chunk and converting into the byte 
            bytes[Integer.parseInt(file.getName())]= getByteArrayFromFile(file);   //Converting the chunk to corresponding byte
        }
        return bytes;
    }
    public void ByteToChunk(byte[] part,int no_of_chunks){      //Writing the Byte arrays to files in the outputstream
        FileOutputStream outStrm;
        File output = new File(folder.getAbsolutePath()+"/"+no_of_chunks);  //output file location
        
            outStrm= new FileOutputStream(output);      //pushing the bytes to the output stream
            operate(outStrm, part);         //writing, flushing and closing the file stream
         
        
    }
    public byte[] chunkToByte(int no_of_chunks) {
        File file = new File(folder.getAbsolutePath() + "/" + no_of_chunks);     //Converting Part Files to Array of Byte
        return getByteArrayFromFile(file);
    }
    
    private byte[] getByteArrayFromFile(File file){
        FileInputStream inStrm = null;
        
            inStrm = new FileInputStream(file);
            byte[] PacketSize = new byte[(int) file.length()];
            int bytesRead = inStrm.read(PacketSize, 0, (int) file.length());
            inStrm.close();
            assert (bytesRead == PacketSize.length);
            assert (bytesRead == (int) file.length());
            return PacketSize;
            if (inStrm != null) {
                try {
                    inStrm.close();
                }
                catch (IOException ex) {}
            }
       
return null;

}
public void process(File inputFile, int partSize){

    FileInputStream inputStream;
    String newFileName;
    FileOutputStream filePart;
    int fileSize = (int) inputFile.length();
    int nChunks = 0, read = 0, readLength = partSize;
    byte[] byteChunkPart;
    try {
        inputStream = new FileInputStream(inputFile);
        while (fileSize > 0) {
            if (fileSize <= 5) {
                readLength = fileSize;
            }
            byteChunkPart = new byte[readLength];
            read = inputStream.read(byteChunkPart, 0, readLength);
            fileSize -= read;
            assert (read == byteChunkPart.length);
            nChunks++;
            newFileName = inputFile.getParent() + "/parts/" +
                    inputFile.getName() + "/" + Integer.toString(nChunks - 1);
            filePart = new FileOutputStream(new File(newFileName));
            filePart.write(byteChunkPart);
            filePart.flush();
            filePart.close();
            byteChunkPart = null;
            filePart = null;
        }
        inputStream.close();
    } catch (IOException e) {
        AssistLog.getLogger().warning(e);
    }
}

public void splitFile(int partSize){
this.process(file, partSize);

AssistLog.getLogger().debug("Split success");
}

public void mergeFile(int numParts) {
File outputFile = file;
FileOutputStream outStrm;
FileInputStream inStrm;
byte[] PacketSize;
int bytesRead = 0;
List<File> list = new ArrayList<>();
for (int i = 0; i < numParts; i++) {
    list.add(new File(folder.getPath() + "/" + i));
}
try {
    outStrm = new FileOutputStream(outputFile);
    for (File file : list) {
        inStrm = new FileInputStream(file);
        PacketSize = new byte[(int) file.length()];
        bytesRead = inStrm.read(PacketSize, 0, (int) file.length());
        assert (bytesRead == PacketSize.length);
        assert (bytesRead == (int) file.length());
        outStrm.write(PacketSize);
        outStrm.flush();
        PacketSize = null;
        inStrm.close();
        inStrm = null;
    }
    outStrm.close();
    outStrm = null;
} catch (Exception exception) {
    exception.printStackTrace();
}
}
}