public enum CommonProperties {

    NumberOfPreferredNeighbors,
    UnchokingInterval,
    OptimisticUnchokingInterval,
    FileName,
    FileSize,
    PieceSize;
    
    public static Properties read (Reader inputFile) throws Exception {

        final Properties conf = new Properties () {
            @Override
            public synchronized void load(Reader inputFile)
                    throws IOException {
                BufferedReader input = new BufferedReader(inputFile);
                int index = 0;
                String line;
                while((line = input.readLine()) != null){
                    line = line.trim();
                    if ((line.length() <= 0) || (line.startsWith (pcapsule.get(1)))) {
                        continue;
                    }
                    String[] statements = line.split("\\s+");
                    if (statements.length != 2) {
                        throw new IOException (new ParseException (line, i));
                    }
                    setProperty(statements[0].trim(), statements[1].trim());
                    index++;
                }
            }
        };

        conf.load (inputFile);

        // Check the config file contains all the needed properties
        for (CommonProperties prop : CommonProperties.values()) {
            if (!conf.containsKey(prop.toString())) {
                throw new Exception ("config file does not contain property " + prop);
            }
        }

        return conf;
    }
}
