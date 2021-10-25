package core.basesyntax;

public class ArrayService {

    public static String[][] splitArrayLines(String[] oneDimArr, String symbol) {
        String[][] twoDimArr = new String[oneDimArr.length][2];
        for (int i = 0; i < twoDimArr.length; i++) {
            twoDimArr[i][0] = oneDimArr[i].split(symbol)[0];
            twoDimArr[i][1] = oneDimArr[i].split(symbol)[1];
        }
        return twoDimArr;
    }
}
