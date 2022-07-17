package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE = 0;
    private static final int AMOUNT = 1;
    private static final int SUPPLY_AMOUNT = 0;
    private static final int BUY_AMOUNT = 1;
    private static final int RESULT_AMOUNT = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        int [] report = createReport(fromFileName);
        writeToFile(report,toFileName);
    }

    private int[] createReport(String fromFileName) {
        File file = new File(fromFileName);
        int buy = 0;
        int supply = 0;
        int result = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String data = reader.readLine();
            while (data != null) {
                String[] splittedLine = data.split(",");
                if (splittedLine[OPERATION_TYPE].equals("buy")) {
                    buy = buy + Integer.parseInt(splittedLine[AMOUNT]);
                }
                if (splittedLine[OPERATION_TYPE].equals("supply")) {
                    supply = supply + Integer.parseInt(splittedLine[AMOUNT]);
                }
                data = reader.readLine();
                result = supply - buy;
            }
            return new int[]{supply, buy, result};
        } catch (IOException e) {
            throw new RuntimeException("Cant read file ");
        }
    }

    private void writeToFile(int [] report,String toFileName) {
        File fileResult = new File(toFileName);
        String[] writtenReport = new String[]
                {"supply," + report[SUPPLY_AMOUNT], "buy,"
                        + report[BUY_AMOUNT], "result,"
                        + report[RESULT_AMOUNT]};
        for (String lines : writtenReport) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(
                    new FileWriter(fileResult, true))) {
                bufferedWriter.write(lines);
                bufferedWriter.newLine();
            } catch (IOException e) {
                throw new RuntimeException("Cant read file ");
            }

        }

    }
}



