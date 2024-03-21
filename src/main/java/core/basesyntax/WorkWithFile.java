package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String readedFileData = readFile(fromFileName);
        String report = new ReportCSVSupplier(readedFileData).createSummeryReport();
        writeToFile(toFileName, report);
    }

    private String readFile(String fileName) {
        StringBuilder fileData = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String lastLine = bufferedReader.readLine();
            while (lastLine != null) {
                fileData.append(lastLine).append(System.lineSeparator());
                lastLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read a file", e);
        }
        return fileData.toString();
    }

    private void writeToFile(String fileName, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to the file", e);
        }
    }
}
