//package core.basesyntax;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.util.ArrayList;
//import java.util.List;
//
//public class CsvFileReader {
//    private static final String SPLIT_CHARACTER = ",";
//    private ArrayList<String> lines = new ArrayList<String>();
//    private String pathToFile;
//
//    CsvFileReader(String pathToFile) {
//
//    }
//
//    public ArrayList readFile() {
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader(pathToFile));
//            String value = reader.readLine();
//            while (value != null) {
//                lines.add(value);
//                value = reader.readLine();
//            }
//            return lines;
//        } catch (IOException e) {
//            throw new RuntimeException("Can't read file", e);
//        }
//    }
//}
