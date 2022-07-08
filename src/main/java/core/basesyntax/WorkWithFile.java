package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE = 0;
    private static final int AMMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        File fileresult = new File(toFileName);
        int buy = 0;
        int supply = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String data = reader.readLine();
            while (data != null) {
                String[] splittedLine = data.split(",");
                if (splittedLine[OPERATION_TYPE].equals("buy")) {
                    buy = buy + Integer.parseInt(splittedLine[AMMOUNT]);
                }
                if (splittedLine[OPERATION_TYPE].equals("supply")) {
                    supply = supply + Integer.parseInt(splittedLine[AMMOUNT]);
                }
                data = reader.readLine();
            }
            int result = supply - buy;
            String[] report = new String[]
                    {"supply," + supply, "buy," + buy, "result," + result};
            for (String lines : report) {
                BufferedWriter bufferedWriter = new BufferedWriter(
                        new FileWriter(fileresult, true));
                bufferedWriter.write(lines);
                bufferedWriter.newLine();
                bufferedWriter.close();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cant read and write");
        }
    }
}

