package core.basesyntax;


import java.io.*;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder answer = new StringBuilder();
        int supply = 0;
        int buy = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                for (int i = 0; i < data.length; i += 2) {
                    String type = data[i].trim();
                    String numberStr = data[i + 1].replaceAll("[^0-9]", "");
                    int number = Integer.parseInt(numberStr);
                    if (type.equals("supply")) {
                        supply += number;
                    } else if (type.equals("buy")) {
                        buy += number;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int result = supply - buy;
        answer.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(result);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(answer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
