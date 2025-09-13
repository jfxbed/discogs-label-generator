import java.io.FileReader;
import java.io.Reader;
import java.util.HashSet;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;

/**
* Class For Reading Data In From A CSV FIle
*
*/
public class CSVReader {
    private String path;

    /**
     * Constuctor for CSV Reader. Passes CSV filepath into filed attribute.
     * 
     * @param path String File Path.
     **/
    public CSVReader(String path){
        this.path = path;
    }

    /**
     * Method to get all of the people in the CSV file. Uses the apache commons
     * CSV format library. If an error is hit while reading the file, will use
     * System.exit() to end program. 
     * 
     * @return A HashSet of Person Objects.
     **/
    public HashSet<Record> getRecords(){
        try (Reader in = new FileReader(path);){
            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setSkipHeaderRecord(true)
                .build();

            HashSet<Record> records = csvFormat.parse(in)
                .stream()
                .map(s -> new Record( ( (s.get(2).length() > 35) ? s.get(2).substring(0,35) + "...": s.get(2) ), ( (s.get(1).length() > 35) ? s.get(1).substring(0,35) + "..." : s.get(1) ), s.get(3), s.get(6), s.get(4), s.get(0).split(",")[0], s.get(9).substring(0,9), s.get(10), s.get(11)))
                .collect(Collectors.toCollection(HashSet::new));  
            return records;
        } catch (Exception e){
            System.out.println(":( Something Went Wrong...");
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

}