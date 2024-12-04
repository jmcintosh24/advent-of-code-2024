import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {
    public static void main(String[] args) throws IOException {
        // Read input
        File file = new File("day3/input.txt");
        FileReader fr = new FileReader(file);

        char[] input = new char[(int)file.length()];
        fr.read(input);

        // Actual Problem
        Pattern mulPattern = Pattern.compile("mul\\([0-9]+,[0-9]+\\)");
        Pattern doPattern = Pattern.compile("do\\(\\)");
        Pattern dontPattern = Pattern.compile("don't\\(\\)");

        boolean enabled = true;

        Matcher matcher;
        StringBuilder temp = new StringBuilder();

        int sum1 = 0;

        for(int i = 0; i < input.length; i++) {
            temp.append(input[i]);

            if(doPattern.matcher(temp.toString()).find()) {
                enabled = true;
                temp.delete(0, temp.length());
            }

            if(dontPattern.matcher(temp.toString()).find()) {
                enabled = false;
                temp.delete(0, temp.length());
            }

            if(enabled) {
                matcher = mulPattern.matcher(temp.toString());

                if(matcher.find()) {
                    sum1 += multiply(matcher.group());
                    temp.delete(0, temp.length());
                }
            }

        }

        System.out.println("Part 2: " + sum1);
    }

    private static int multiply(String s) {
        int num1 = Integer.parseInt(s.substring(s.indexOf('(') + 1, s.indexOf(',')));
        int num2 = Integer.parseInt(s.substring(s.indexOf(',') + 1, s.indexOf(')')));

        return num1 * num2;
    }
}