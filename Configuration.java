import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;

public class Configuration {
	private final String _File = "Common.cfg";
	//properties    //TODO: May need to change the type of variables later
	private String NumberOfPreferredNeighbors; 
    private String UnchokingInterval;
    private String OptimisticUnchokingInterval;
    private String FileName;
    private String FileSize;
    private String PieceSize;
    
    //Constructor    //TODO: Need to be changed based on how we call this class in other files
    public Configuration() {
    	
    }
    
    //Reading the configuration file (Common.cfg) and setting the properties  //TODO: Needs to be re-structured more
    public void setProperties(Reader reader) throws Exception{
    	BufferedReader in = new BufferedReader(reader);
        int i = 0;
        for (String line; (line = in.readLine()) != null; i++) {
            line = line.trim();
            if (line.length() <= 0) {
                continue;
            }
            String[] tokens = line.split("\\s+");
            if (tokens.length != 2) {
                throw new IOException (new ParseException (line, i));
            }
            //Setting the properties
            if(tokens[0].trim().equals("NumberOfPreferredNeighbors")){
            	this.setNumberOfPreferredNeighbors(tokens[1].trim());
            }
            else if(tokens[0].trim().equals("UnchokingInterval")){
            	this.setUnchokingInterval(tokens[1].trim());
            }         
            else if(tokens[0].trim().equals("OptimisticUnchokingInterval")){
            	this.setOptimisticUnchokingInterval(tokens[1].trim());
            }
            else if(tokens[0].trim().equals("FileName")){
            	this.setFileName(tokens[1].trim());
            }
            else if(tokens[0].trim().equals("FileSize")){
            	this.setFileSize(tokens[1].trim());
            }
            else if(tokens[0].trim().equals("PieceSize")){
            	this.setPieceSize(tokens[1].trim());
            }
            else {
            	throw new Exception ("There is something wrong with setting the properties.");
            }
            
            
        }
    }
    
    
    //Setters and Getters
	public String get_File() {
		return _File;
	}


	public String getNumberOfPreferredNeighbors() {
		return NumberOfPreferredNeighbors;
	}


	public void setNumberOfPreferredNeighbors(String numberOfPreferredNeighbors) {
		NumberOfPreferredNeighbors = numberOfPreferredNeighbors;
	}

	public String getOptimisticUnchokingInterval() {
		return OptimisticUnchokingInterval;
	}


	public void setOptimisticUnchokingInterval(String optimisticUnchokingInterval) {
		OptimisticUnchokingInterval = optimisticUnchokingInterval;
	}


	public String getFileName() {
		return FileName;
	}


	public void setFileName(String fileName) {
		FileName = fileName;
	}


	public String getFileSize() {
		return FileSize;
	}


	public void setFileSize(String fileSize) {
		FileSize = fileSize;
	}


	public String getPieceSize() {
		return PieceSize;
	}


	public void setPieceSize(String pieceSize) {
		PieceSize = pieceSize;
	}


	public String getUnchokingInterval() {
		return UnchokingInterval;
	}


	public void setUnchokingInterval(String unchokingInterval) {
		UnchokingInterval = unchokingInterval;
	}
    
    
}
