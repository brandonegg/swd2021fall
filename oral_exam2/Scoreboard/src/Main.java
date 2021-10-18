import sports.Football;

public class Main {

    public static void main(String[] args) {

        Football.SCORING_METHOD scoreType = Football.SCORING_METHOD.TOUCHDOWN;

        System.out.println(scoreType.getValue());
    }
}
