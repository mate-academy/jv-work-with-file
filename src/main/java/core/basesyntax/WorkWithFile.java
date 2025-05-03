package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    public static String getStatistic(String fromFileName, String toFileName) {
        String[] words = fromFileName.split(",");
        StringBuilder stringBuilder = new StringBuilder();
        int resultSupply = 0;
        int resultBuy = 0;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals("supply")) {
                resultSupply += Integer.parseInt(words[i + 1]);
            }
            if (words[i].equals("buy")) {
                resultBuy += Integer.parseInt(words[i + 1]);
            }
        }
        int res = resultSupply - resultBuy;
        StringBuilder builder = new StringBuilder();
        toFileName = builder.append("supply").append(",").append(String.valueOf(resultSupply))
                .append(System.lineSeparator())
                .append("buy").append(",").append(String.valueOf(resultBuy))
                .append(System.lineSeparator())
                .append("result").append(",").append(String.valueOf(res)).toString();
        return toFileName;
    }

    public static String readFromFile(String fileName) {
        File file = new File(fileName);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            StringBuilder stringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(",");
                value = bufferedReader.readLine();
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
    }

    public static void writeToFile(String fileName, String result) {
        File file = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write file", e);
        }
    }
}
