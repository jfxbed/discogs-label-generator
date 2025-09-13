public record Record(String name, String artist, String label, String year, String format, String catalogNumber, String datetAdded, String mediaCondition, String sleeveCondition) {

    public void printInfo(){
        System.out.printf("Title: %s\nArtist: %s\nLabel: %s\nYear: %s\nFormat: %s\nCatalog#: %s\nDateAddded: %s\nMedia Condition: %s\nsleeveCondition: %s\n", name, artist, label, year, format, catalogNumber, datetAdded, mediaCondition, sleeveCondition);
    }
}