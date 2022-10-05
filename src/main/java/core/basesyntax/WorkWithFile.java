package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static String SYSTEM_LINE_SEPARATOR = System.getProperty("line.separator");
    private static String ROW_SEPARATOR = ",";
    private static int CONTENT_DATA_ROW_VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String content = readFileToString(fromFileName);
        content = generatReport(content);
        writeStringToFile(toFileName, content);
    }

    private String readFileToString(String fileName) {
        String content = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                content += value + SYSTEM_LINE_SEPARATOR;
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error of reading the file with name: " + fileName, e);
        }
        return content;
    }

    private void writeStringToFile(String toFileName, String content) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Error of writting the file with name: " + toFileName, e);
        }
    }

    private String generatReport(String content) {
        String[] splitContent = content.split(System.lineSeparator());
        String[][] contentData = new String[splitContent.length][2];
        for (int i = 0; i < contentData.length; i++) {
            String[] lineData = splitContent[i].split(ROW_SEPARATOR);
            contentData[i] = lineData;
        }

        int supplyResult = 0;
        int buyResult = 0;
        for (int i = 0; i < contentData.length; i++) {
            if (contentData[i][0].equals("supply")) {
                supplyResult += Integer.parseInt(contentData[i][CONTENT_DATA_ROW_VALUE_INDEX]);
            } else {
                buyResult += Integer.parseInt(contentData[i][CONTENT_DATA_ROW_VALUE_INDEX]);
            }
        }

        StringBuilder report = new StringBuilder();
        report.append("supply,").append(supplyResult).append(SYSTEM_LINE_SEPARATOR);
        report.append("buy,").append(buyResult).append(SYSTEM_LINE_SEPARATOR);
        report.append("result,").append(supplyResult - buyResult);
        return report.toString();
    }
}
