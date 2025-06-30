package core.basesyntax;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try {
            java.nio.file.Path inputPath = java.nio.file.Path.of(fromFileName);
            java.util.List<String> lines = java.nio.file.Files.readAllLines(inputPath);

            int totalSupply = 0;
            int totalBuy = 0;

            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String operationType = parts[0];
                    int amount = Integer.parseInt(parts[1]);

                    if ("supply".equals(operationType)) {
                        totalSupply += amount;
                    } else if ("buy".equals(operationType)) {
                        totalBuy += amount;
                    }
                }
            }

            int result = totalSupply - totalBuy;

            String report = "supply," + totalSupply + System.lineSeparator() +
                    "buy," + totalBuy + System.lineSeparator() +
                    "result," + result;

            java.nio.file.Path outputPath = java.nio.file.Path.of(toFileName);
            java.nio.file.Files.writeString(outputPath, report);

        } catch (java.io.IOException e) {
            throw new RuntimeException("Error processing files", e);
        }
    }
}
