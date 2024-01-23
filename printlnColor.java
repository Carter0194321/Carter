import java.io.BufferedOutputStream;
import java.io.PrintStream;
    import java.util.List;

public class printlnColor extends PrintStream {

    public printlnColor(BufferedOutputStream out) {
        super(out, true); // 'true' to autoflush the stream
    }

    public void print(String message, int color) {
        String coloredMessage = "\u001B[" + color + "m" + message + "\u001B[0m";
        println(coloredMessage);
    }

    public void print(List<Character> message, List<Integer> color) {
        String coloredMessage = "";

        for (int i = 0; i < message.size(); i++) {
            coloredMessage += "\u001B[" + color.get(i) + "m" + message.get(i) + "\u001B[0m";
        }
        println(coloredMessage);
    }

    public void printBatch(List<List<Character>> messages, List<List<Integer>> colors) {
        for (int i = 0; i < messages.size(); i++) {
            print(messages.get(i), colors.get(i));
        }
    }
}

/*
 Regular Colors (Foreground):

Black: 30
Red: 31
Green: 32
Yellow: 33
Blue: 34
Magenta: 35
Cyan: 36
White: 37

Bright/High-Intensity Colors (Foreground):

Bright Black: 90
Bright Red: 91
Bright Green: 92
Bright Yellow: 93
Bright Blue: 94
Bright Magenta: 95
Bright Cyan: 96
Bright White: 97

Background Colors (add 10 to the foreground color codes):

Black: 40
Red: 41
Green: 42
Yellow: 43
Blue: 44
Magenta: 45
Cyan: 46
White: 47
Bright/High-Intensity Background Colors (add 10 to the bright foreground color codes):

Bright Black: 100
Bright Red: 101
Bright Green: 102
Bright Yellow: 103
Bright Blue: 104
Bright Magenta: 105
Bright Cyan: 106
Bright White: 107
 */