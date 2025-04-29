package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        List<String> result = new ArrayList<>();
        File file = new File(fromFileName);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if ("supply".equals(parts[0].trim())) {
                    int liczba = Integer.parseInt(parts[1]);
                    supply += liczba;
                } else if ("buy".equals(parts[0].trim())) {
                    int liczba = Integer.parseInt(parts[1]);
                    buy += liczba;
                }
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supply + System.lineSeparator());
            writer.write("buy," + buy + System.lineSeparator());
            writer.write("result," + (supply - buy));
        }
    }
}
