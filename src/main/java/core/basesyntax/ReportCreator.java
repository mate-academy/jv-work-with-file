package core.basesyntax;

public class ReportCreator {
    public String reportCreator(String[] strings) {
        int supply = 0;
        int buy = 0;
        int result = 0;
        for (String string : strings) {
            String[] arr = string.split(",");

            if (arr[0].equals("supply")) {
                supply += Integer.parseInt(arr[1]);
            }
            if (arr[0].equals("buy")) {
                buy += Integer.parseInt(arr[1]);
            }
        }
        result = supply - buy;
        return new String("supply,"
                + Integer. toString(supply)
                + "\n"
                + "buy,"
                + buy
                + "\n"
                + "result,"
                + result);
    }
}
