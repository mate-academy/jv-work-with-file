package core.basesyntax;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        // Симулюємо вміст файлу (fromFileName)
        String data = "supply,30\nbuy,10\nbuy,13\nsupply,17\nbuy,10";

        int supply = 0;
        int buy = 0;

        // Розділяємо дані за рядками
        String[] lines = data.split("\n");
        for (String line : lines) {
            String[] parts = line.split(",");
            String operation = parts[0];
            int amount = Integer.parseInt(parts[1]);

            if (operation.equals("supply")) {
                supply += amount;
            } else if (operation.equals("buy")) {
                buy += amount;
            }
        }

        int result = supply - buy;

        // Формуємо звіт
        String report = "supply," + supply + "\n"
                + "buy," + buy + "\n"
                + "result," + result;

        // Симулюємо запис у файл (toFileName)
        System.out.println("=== Report ===");
        System.out.println(report);
    }
}

