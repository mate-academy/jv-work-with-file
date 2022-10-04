package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String content = readFileToString(fromFileName);
        content = process(content);
        writeStringToFile(toFileName, content);
    }

    private String readFileToString(String fileName) {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String content = "";
        try {
            String value = bufferedReader.readLine();
            while (value != null) {
                content += value + " ";
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return content;
    }

    private void writeStringToFile(String toFileName, String content) {
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(toFileName));
            bufferedWriter.write(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private String process(String content) {
        String[] splitContent = content.split(" ");
        String[][] contentData = new String[splitContent.length][2];
        for (int i = 0; i < contentData.length; i++) {
            String[] lineData = splitContent[i].split(",");
            contentData[i] = lineData;
        }

        int supplyResult = 0;
        int buyResult = 0;
        for (int i = 0; i < contentData.length; i++) {
            if (contentData[i][0].equals("supply")) {
                supplyResult += Integer.parseInt(contentData[i][1]);
            } else {
                buyResult += Integer.parseInt(contentData[i][1]);
            }
        }

        StringBuilder report = new StringBuilder();
        report.append("supply,").append(supplyResult).append("\r\n");
        report.append("buy,").append(buyResult).append("\r\n");
        report.append("result,").append(supplyResult - buyResult);
        return report.toString();
    }
}
