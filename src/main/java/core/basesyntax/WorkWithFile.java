package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fromFileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                switch (parts[0]) {
                    case "supply":
                        supply += Integer.parseInt(parts[1]);
                        break;
                    case "buy":
                        buy += Integer.parseInt(parts[1]);
                        break;
                    default:
                        throw new RuntimeException("Something went terribly "
                                + "wrong in switch-case block");
                }
            }

            int result = supply - buy;

            StringBuilder report = new StringBuilder();
            report.append("supply,").append(supply).append(System.lineSeparator());
            report.append("buy,").append(buy).append(System.lineSeparator());
            report.append("result,").append(result).append(System.lineSeparator());

            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(toFileName))) {
                writer.write(report.toString());
                System.out.println(report.toString());
            } catch (IOException e) {
                throw new RuntimeException("Can't write to file", e);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }
}
