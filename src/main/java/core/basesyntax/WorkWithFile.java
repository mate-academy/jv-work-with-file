package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
            BufferedReader reader = new BufferedReader(new FileReader(fromFile));
            StringBuilder statistic = new StringBuilder();
            String line = reader.readLine();

            while (line != null) {
                statistic.append(line).append(System.lineSeparator());
                line = reader.readLine();
            }

            String[] statistics = statistic.toString().split(System.lineSeparator());
            String[] splitedStatistics = new String[statistics.length * 2];

            for (int i = 0; i < statistics.length; i++) {
                String[] parts = statistics[i].split(",");
                splitedStatistics[i * 2] = parts[0];
                splitedStatistics[i * 2 + 1] = parts[1];
            }

            String[] result = new String[6];
            result[0] = "supply";
            result[2] = "buy";
            result[4] = "result";

            for (int i = 0; i < splitedStatistics.length; i += 2) {
                switch (splitedStatistics[i]) {
                    case "supply":
                        result[1] = String.valueOf(Integer.parseInt(splitedStatistics[i + 1])
                                + ((result[1] == null) ? 0 : Integer.parseInt(result[1])));
                        break;
                    case "buy":
                        result[3] = String.valueOf(Integer.parseInt(splitedStatistics[i + 1])
                                + ((result[3] == null) ? 0 : Integer.parseInt(result[3])));
                        break;
                    default:
                        break;
                }
            }

            result[5] = String.valueOf(Integer.parseInt(result[1]) - Integer.parseInt(result[3]));

            for (int i = 0; i < 6; i += 2) {
                writer.write(result[i] + "," + result[i + 1] + System.lineSeparator());
            }

        } catch (IOException e) {
            throw new RuntimeException("Something went wrong(", e);
        }
    }
}
