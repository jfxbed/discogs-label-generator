import java.io.File;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashSet;

public class DiscogsLabelGenerator {
    public static String path = null;
    public static String name = "";
    public static String line1 = "Established 2024";
    public static String line2 = "";
    public static String line3 = "";
    public static String filterdate = "";


    public static void main(String[] args) {
        System.out.println("#############DiscogsLabelGenerator###############");
        for (int i = 0; i < args.length ; i++){
            if (i == 0){
                path = args[i];
            } else if (args[i].equals("-name")){
                name = args[i+1];
                if (name.substring(name.length() - 1).equals("s")){
                    name+="'";
                } else {
                    name+="'s";
                }
                System.out.println("Name Flag Accepted");
            } else if (args[i].equals("-line1")){
                line1 = args[i+1];
                System.out.println("Line1 Flag Accepted");
            } else if (args[i].equals("-line2")){
                line2 = args[i+1];
                System.out.println("Line2 Flag Accepted");
            } else if (args[i].equals("-line3")){
                line3 = args[i+1];
                System.out.println("Line3 Flag Accepted");
            } else if (args[i].equals("-filterdate")){
                filterdate = args[i+1];
                System.out.println("filterdate Flag Accepted");
            }
        }

        if (path == null ){
            System.out.println("ERROR: No filepath provided \nUsage DiscogsLabelGenerator <CSV Path>");
            System.exit(0);
        }

        CSVReader read = new CSVReader(path);
        HashSet<Record> collection = read.getRecords(filterdate);
        HashSet<String> lablelLists = new HashSet<String>();
        StringBuilder current = new StringBuilder();
        int count = 0;
        for (Record r : collection){
            LabelGenerator g = new LabelGenerator(r);
            current.append(g.getLabel(name, line1, line2, line3));
            System.out.printf("Generated Label No. %d \n", count);
            count++;
            if ((count % 50) == 0) {
                lablelLists.add(current.toString());
                current = new StringBuilder();
            }
        }

        count = 0;
        System.out.println("Sending Labels to Be Generated...");
        for (String labels : lablelLists) {
            count++;
            try {
            // adjust print density (8dpmm), label width (4 inches), label height (6 inches), and label index (0) as necessary
            var uri = URI.create("http://api.labelary.com/v1/printers/8dpmm/labels/4x6/");
            var request = HttpRequest.newBuilder(uri)
                .header("Accept", "application/pdf") // omit this line to get PNG images back
                .POST(BodyPublishers.ofString(labels))
                .build();
            var client = HttpClient.newHttpClient();
            var response = client.send(request, BodyHandlers.ofByteArray());
            var body = response.body();

            if (response.statusCode() == 200) {
                System.out.printf("Label PDF %d Generated\n", count);
                var file = new File(String.format("output/labels%d.pdf", count )); // change file name for PNG images
                Files.write(file.toPath(), body);
            } else {
                var errorMessage = new String(body, StandardCharsets.UTF_8);
                System.out.println(errorMessage);
            }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("All Labels Generated");
    }
}