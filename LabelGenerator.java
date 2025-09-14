import java.net.http.*;

public class LabelGenerator {
    private Record record;
    
    public LabelGenerator(Record r){
        record = r;
    }

    public String getLabel(String name, String line1, String line2, String line3){
        return String.format("^XA^PW800^LL650^LH0,0^CF0,60^FO50,50^GC100,10^FS^FO75,75^GC50,10^FS^FO93,93^GC10,10^FS^FO220,50^FD%s Record Collection^FS^CF0,30^FO220,115^FD%s^FS^FO220,155^FD%s^FS^FO220,195^FD%s^FS^FO50,230^GB700,3,3^FS^CF0,30^FO50,260^FDArtist:^FS^FO250,260^FD%s^FS^FO50,300^FDAlbum:^FS^FO250,300^FD%s^FS^FO50,340^FDYear:^FS^FO250,340^FD%s^FS^FO50,380^FDFormat:^FS^FO250,380^FD%s^FS^FO50,420^FDCatalogNo:^FS^FO250,420^FD%s^FS^FO50,460^FDDateAdded:^FS^FO250,460^FD%s^FS^FO50,500^FDMediaCondition:^FS^FO250,500^FD%s^FS^FO50,540^FDSleeveCondition:^FS^FO250,540^FD%s^FS^FO50,580^GB700,3,3^FS^CF0,30^FO50,610^FDNotes:^FS^FO250,610^FD^FS^XZ", name, line1, line2, line3, record.artist(), record.name(), record.year(), record.format(), record.catalogNumber(),  record.datetAdded(), record.mediaCondition(), record.sleeveCondition());
    }
}
