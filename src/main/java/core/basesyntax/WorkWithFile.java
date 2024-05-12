package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String[] reportFromFile = getReportFromFile(fromFileName);
        WorkWithFile report = new WorkWithFile();
        report.writeReportToFile(reportFromFile, toFileName);
    }

    private String[] getReportFromFile(String fileName) {
        int supply = 0;
        int buy = 0;
        File fileRead = new File(fileName);
        try (BufferedReader bf = new BufferedReader(new FileReader(fileRead))) {
            String bufferValue = bf.readLine();
            while (bufferValue != null) {
                int startIndex = bufferValue.indexOf(",") + 1;
                if (bufferValue.contains("supply")) {
                    supply += Integer.parseInt(bufferValue.substring(startIndex));
                } else {
                    buy += Integer.parseInt(bufferValue.substring(startIndex));
                }
                bufferValue = bf.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file" + fileName, e);
        }
        return new String[]{"supply," + supply, "buy," + buy, "result," + (supply - buy)};
    }

    private void writeReportToFile(String[] report, String toFile) {
        File fileWrite = new File(toFile);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileWrite))) {
            for (int i = 0; i < report.length; i++) {
                bw.write(report[i] + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + toFile, e);
        }
    }
}
