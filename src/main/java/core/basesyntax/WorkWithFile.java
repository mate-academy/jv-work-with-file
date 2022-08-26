package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        int result = 0;
        File myFileFrom = new File(fromFileName);
        File myFileTo = new File(toFileName);
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(myFileFrom);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Error file read ", e);
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        try {
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] name = value.split(",", 0);
                if (name[0].equals("supply")) {
                    supply += Integer.parseInt(name[1]);
                }
                if (name[0].equals("buy")) {
                    buy += Integer.parseInt(name[1]);
                }
                value = bufferedReader.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException("Error read line", e);
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                throw new RuntimeException("Error bufferedReader close", e);
            }

        }
        result = supply - buy;
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(myFileTo));
            bufferedWriter.write("supply," + supply + SEPARATOR);
            bufferedWriter.write("buy," + buy + SEPARATOR);
            bufferedWriter.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + myFileTo, e);
        } finally {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                throw new RuntimeException("Can't close bufferedWriter " + myFileTo, e);
            }
        }
    }
}
