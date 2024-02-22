package core.basesyntax;

public class ReportFromFile {
    public int[] reportedFromFile(StringBuilder stringBuilder) {
        int[] amount = new int[3];
        String[] strings = stringBuilder.toString().split(" ");
        for (String string : strings) {
            String[] separateAmount = string.split(",");
            if (separateAmount[0].equals("supply")) {
                amount[0] += Integer.parseInt(separateAmount[1]);
            } else if (separateAmount[0].equals("buy")) {
                amount[1] += Integer.parseInt(separateAmount[1]);
            }
        }
        amount[2] = amount[0] - amount[1];
        return amount;
    }
}
