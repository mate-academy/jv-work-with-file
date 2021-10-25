package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WorkWithFile {
    private static final String SUPPLY = "supply,";
    private static final String BUY = "buy,";
    private static final String RESULT = "result,";

    public void getStatistic(String fromFileName, String toFileName) {
        ArrayList<String> spreadSheet = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                spreadSheet.add(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
        spreadSheet.remove(null);
        writeStatistic(toFileName, createReport(spreadSheet));
    }

    private String createReport(ArrayList<String> spreadSheet) {
        int buy = 0;
        int supply = 0;
        for (String target : spreadSheet) {
            if (target.contains("buy")) {
                buy += Integer.valueOf(target.replaceAll("[^0-9]", ""));
            } else if (target.contains("supply")) {
                supply += Integer.valueOf(target.replaceAll("[^0-9]", ""));
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(SUPPLY).append(supply).append(System.lineSeparator())
                .append(BUY).append(buy).append(System.lineSeparator())
                .append(RESULT).append(supply - buy);
        return sb.toString();
    }

    private void writeStatistic(String toFileName, String report) {
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create new file", e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
