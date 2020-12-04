package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int buy = 0;
        int supply = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String value = bufferedReader.readLine();
            while (value != null) {
                String [] strings = value.split(",");
                if (strings[0].equals("buy")) {
                    buy = buy + Integer.parseInt(strings[1]);
                } else {
                    supply = supply + Integer.parseInt(strings[1]);
                }
                System.out.println(buy + " " + supply);
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cant read file");
        }

        try {
            File fileToWrite = new File(toFileName);
            fileToWrite.createNewFile();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileToWrite));
            bufferedWriter.write("supply," + Integer.toString(supply) + System.lineSeparator()
                    + "buy," + Integer.toString(buy) + System.lineSeparator()
                    + "result," + Integer.toString(supply - buy));
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Cant write file");
        }
    }
}
