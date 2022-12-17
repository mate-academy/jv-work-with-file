package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String newReport = getReport(fromFileName);
        writerToFile(newReport, toFileName);
    }

    private Map<String, Integer> readFile(String fileName) {
        Map<String, Integer> result = new HashMap<String, Integer>();
        result.put(SUPPLY, 0);
        result.put(BUY, 0);
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String elementsInFile = reader.readLine();
            while (elementsInFile != null) {
                String[] data = elementsInFile.split("\\W+");
                result.put(data[OPERATION_INDEX],
                        result.get(data[OPERATION_INDEX]) + Integer.parseInt(data[AMOUNT_INDEX]));
                elementsInFile = reader.readLine();
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
    }

    public String getReport(String fromFileName) {
        Map<String, Integer> map = readFile(fromFileName);
        int supply = map.get(SUPPLY);
        int buy = map.get(BUY);
        int result = supply - buy;
        StringBuilder report = new StringBuilder("supply,")
                .append(supply)
                .append(System.lineSeparator())
                .append("buy,")
                .append(buy)
                .append(System.lineSeparator())
                .append("result,")
                .append(result);
        return report.toString();
    }

    private void writerToFile(String result, String toFileName) {
        try (FileWriter writer = new FileWriter(toFileName)) {
            writer.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
