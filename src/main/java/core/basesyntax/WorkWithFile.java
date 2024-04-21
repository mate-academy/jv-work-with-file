package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String path = "C:\\Users\\antch\\IdeaProjects\\jv-work-with-file\\" + toFileName;
        Path filePath = FileSystems.getDefault().getPath(path);
        if (!Files.exists(filePath)) {
            String data = readFromFile(fromFileName);
            writeToFile(data, toFileName);
        }
    }

    private String readFromFile(String fileName) {
        int supplyNum = 0;
        int buyNum = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();

            while (value != null) {
                stringBuilder.append(value).append(" ");
                value = bufferedReader.readLine();
            }

            String[] dataArray = stringBuilder.toString().split(" ");

            for (String string : dataArray) {
                String[] arrayForNum = string.split(",");
                if (arrayForNum[0].equals(SUPPLY)) {
                    supplyNum += Integer.parseInt(arrayForNum[1]);
                } else {
                    buyNum += Integer.parseInt(arrayForNum[1]);
                }
            }

            return createReport(supplyNum, buyNum);

        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fileName, e);
        }
    }

    private String createReport(int supply, int buy) {
        int result;
        StringBuilder answer = new StringBuilder();
        String separator = System.lineSeparator();
        result = supply - buy;
        answer.append(SUPPLY).append(COMMA).append(supply).append(separator);
        answer.append(BUY).append(COMMA).append(buy).append(separator);
        answer.append(RESULT).append(COMMA).append(result);

        return answer.toString();
    }

    private void writeToFile(String data, String resultFile) {
        File file = new File(resultFile);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + file, e);
        }
    }
}
