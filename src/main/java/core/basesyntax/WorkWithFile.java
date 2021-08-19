package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String readString = readFile(fromFileName);
        String report = createReport(readString);
        writeFile(toFileName, report);
    }

    private String readFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        File readFile = new File(fromFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(readFile))) {
            String localString = bufferedReader.readLine();
            while (!(localString == null)) {
                stringBuilder.append(localString).append(ForConstants.LINE_SEPARATOR);
                localString = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("File wasn't read!", e);
        }
        return stringBuilder.toString();
    }

    private String createReport(String readString) {
        int supply = 0;
        int buy = 0;
        String[] stringsForReport = readString.split(ForConstants.LINE_SEPARATOR);
        for (int i = ForConstants.ZERO; i < stringsForReport.length; i++) {
            String[] oneStingFroReport = stringsForReport[i].split(ForConstants.CSV_FILE_SEPARATOR);
            if (oneStingFroReport[ForConstants.ZERO].equals(ForConstants.SUPPLY)) {
                supply += Integer.parseInt(oneStingFroReport[ForConstants.ONE]);
            } else if (oneStingFroReport[ForConstants.ZERO].equals(ForConstants.BUY)) {
                buy += Integer.parseInt(oneStingFroReport[ForConstants.ONE]);
            }
        }
        return drawReport(supply, buy);
    }

    private void writeFile(String toFileName, String report) {
        File writeFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(writeFile))) {
            bufferedWriter. write(report);
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException("File wasn't write!", e);
        }
    }

    private String drawReport(int supply, int buy) {
        return ForConstants.SUPPLY + "," + supply + System.lineSeparator()
                + ForConstants.BUY + "," + buy + System.lineSeparator()
                + "result," + (supply - buy);
    }
}
