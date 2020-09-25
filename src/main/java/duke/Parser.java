package duke;

public class Parser {

    protected static String parseCommand(String line) {
        if (line.equals("bye") || line.equals("list")) {
            return line;
        }

        if (line.contains("done") || line.contains("deadline") || line.contains("event") ||
                line.contains(("todo")) || line.contains(("delete")) || line.contains("find")) {
            return line;
        }

        return showWrongCommand();
    }

    protected static String showWrongCommand() {
       return ("I'm sorry, but I don't know what that means :-C");
    }

    protected static boolean isOnlyDigits(String line) {
        int length = line.length();
        for (int i = 0; i < length; i++) {
            if (line.charAt(i) >= '0' && line.charAt(i) <= '9') {
                continue;
            }
            else {
                return false;
            }
        }
        return true;
    }

    protected static boolean isValidYear(String line) {
        boolean isValidLength = line.length() == 4;

        return isValidLength && isOnlyDigits(line);
    }

    protected static boolean isValidMonth(String line) {
        boolean isValidLength = line.length() == 2 || line.length() == 1;

        return isValidLength && isOnlyDigits(line);
    }

    protected static boolean isValidDay(String line) {
        boolean isValidLength = line.length() == 2 || line.length() == 1;

        return isValidLength && isOnlyDigits(line);
    }

    protected static boolean isValidTime(String line) {
        boolean isValidLength = line.length() == 4;

        return isValidLength && isOnlyDigits(line);
    }

    protected static boolean isValidDate(String line) {
        String[] words = line.split(" ");
        String[] date = words[1].split("-");
        return isValidYear(date[0]) && isValidMonth(date[1]) && isValidDay(date[2]);
    }

    protected static boolean isValidDateAndTime(String line) {
        String[] words = line.split(" ");
        if (words.length < 3) { // Check if time exist in command
            return false;
        }
        String date = " " + words[1];
        return isValidDate(date) && isValidTime(words[2]);
    }

    protected static String getYear(String line) {
        String[] words = line.split(" ");
        String[] date = words[1].split("-");
        return date[0];
    }

    protected static String getMonth(String line) {
        String[] words = line.split(" ");
        String[] date = words[1].split("-");
        switch (date[1]) {
        case "1": case "01":
            return "Jan";
        case "2": case "02":
            return "Feb";
        case "3": case "03":
            return "Mar";
        case "4": case "04":
            return "Apr";
        case "5": case "05":
            return "May";
        case "6": case "06":
            return "Jun";
        case "7": case "07":
            return "Jul";
        case "8": case "08":
            return "Aug";
        case "9": case "09":
            return "Sep";
        case "10":
            return "Oct";
        case "11":
            return "Nov";
        case "12":
            return "Dec";
        default:
            break;
        }
        return date[1];
    }

    protected static String getDay(String line) {
        String[] words = line.split(" ");
        String[] date = words[1].split("-");
        return date[2];
    }

    protected static String getTime(String line) {
        String[] words = line.split(" ");
        return words[2];
    }
}
