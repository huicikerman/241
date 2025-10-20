import java.io.File;

import java.util.Scanner;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Garapen {
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String BLUE = "\033[4;34m";
    public static final String YELLOW = "\033[0;33m";
    public static final String RESET = "\u001B[0m";

    public static final Scanner IN_SCANNER = new Scanner(System.in);

    public static final Pattern PATTERN = Pattern.compile("<(\\w+)>(.*?)</\\1>|([^<]+)");

    public static String menu() {
        System.out.println();
        System.out.println("Aukeratu irakurri nahi duzun gaia:");
        System.out.println("----------------------------------");
        System.out.println();
        System.out.println("    " + GREEN + "1" + RESET + " - Softwarearen Garapena");
        System.out.println("    " + GREEN + "2" + RESET + " - Garapen Metodologiak");
        System.out.println("    " + GREEN + "3" + RESET + " - Metodologia Arinak");
        System.out.println();
        System.out.println("    " + GREEN + "0" + RESET + " - (Amaitu Programa)");
        System.out.println();

        while (true) {
            System.out.print("Zure aukera: ");

            if (!IN_SCANNER.hasNextInt()) {
                System.out.println();
                System.err.println(RED + "Sarrera baliogabea, berriro saiatu." + RESET);

                IN_SCANNER.next();

                continue;
            }

            System.out.println();

            switch (IN_SCANNER.nextInt()) {
                case 1: return "softwarearen_garapena.txt";
                case 2: return "garapen_metodologiak.txt";
                case 3: return "softwarearen_garapena.txt";
                case 0: return null;

                default: System.err.println(RED + "Sarrera baliogabea, berriro saiatu." + RESET);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String gaia = menu();

        if (gaia == null) return;

        String basePath = new File("src/gaiak").getAbsolutePath();
        File file = new File(basePath, gaia);

        try (Scanner fileScanner = new Scanner(file)) {
            System.out.println();
            System.out.println(YELLOW + "-".repeat(gaia.length()) + RESET);
            System.out.println(YELLOW + gaia + RESET);
            System.out.println(YELLOW + "-".repeat(gaia.length()) + RESET);
            System.out.println();

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                Matcher matcher = PATTERN.matcher(line);

                while (matcher.find()) {
                    String tag = matcher.group(1);
                    String text = matcher.group(2);

                    String plain = matcher.group(3);

                    if (plain != null) {
                        System.out.print(plain);
                    } else if ("blue".equals(tag)) {
                        System.out.print(BLUE + text + RESET);
                    } else if ("green".equals(tag)) {
                        System.out.print(GREEN + text + RESET);
                    } else {
                        System.out.print(text);
                    }
                }

                System.out.println();
            }

            System.out.println();
        }
    }
}
