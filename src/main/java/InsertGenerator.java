import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class InsertGenerator {

    private static final String FILENAME = "script2.txt";
    private static final int NUM_RECORDS = 5000;

    public static void main(String[] args) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))) {
            for (int i = NUM_RECORDS; i <= 2 * NUM_RECORDS; i++) {
                bw.write(getRecord(i));
            }
            System.out.println("Records generated and written to " + FILENAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getRecord(int index) {
        StringBuilder sb = new StringBuilder();
        sb.append("insert ").append(index).append("\n");
        sb.append("P3112\n");
        sb.append("4\n");
        sb.append("5\n");
        sb.append("40\n");
        sb.append("0\n");
        sb.append("full_time_education\n");
        sb.append("3\n");
        sb.append("Aboba\n");
        sb.append("123456\n");
        sb.append("RED\n");
        sb.append("RUSSIA\n");
        sb.append("1\n");
        sb.append("2\n");
        sb.append("3\n");
        return sb.toString();
    }

    private static String getRandomNumber() {
        Random random = new Random();
        int num = 100000 + random.nextInt(900000);
        return Integer.toString(num);
    }

    private static String getRandomColor() {
        String[] colors = {"RED", "GREEN", "BLUE", "YELLOW", "ORANGE"};
        Random random = new Random();
        int index = random.nextInt(colors.length);
        return colors[index];
    }
}
