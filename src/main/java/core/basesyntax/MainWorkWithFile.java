package core.basesyntax;

import java.io.File;
import java.io.IOException;

public class MainWorkWithFile {
    public static void main(String[] args) {

        WorkWithFile workWithFile = new WorkWithFile();
        File reportFile = new File("report.csv");
        try {
            reportFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file", e);
        }
        workWithFile.getStatistic("apple.csv",reportFile.getName());
//  -----------------------------------------------------------------
        WorkWithBytes workWithBytes = new WorkWithBytes();
        byte[] myData = {1,5,3,2,4,1,45,56,23,63,34,127};
        File byteData = new File("byteData");
        try {
            byteData.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file",e);
        }
        workWithBytes.writeBytesToFile(byteData.getName(),myData);
    }

}
