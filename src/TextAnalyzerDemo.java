import java.util.Scanner;

/** 结合 Scanner / String / StringBuilder / Character 的示例。 */
public class TextAnalyzerDemo {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("请输入一行文本：");
            if (!scanner.hasNextLine()) {
                System.out.println("未输入内容");
                return;
            }
            String input = scanner.nextLine();
            StringToolkit.Stats stats = StringToolkit.analyze(input);
            System.out.println("统计：" + stats);
            System.out.println("提取数字：" + StringToolkit.extractDigits(input));
            System.out.println("倒序数字：" + StringToolkit.reverseDigits(input));
            System.out.println("各字符频率：" + StringToolkit.codePointFrequency(input));
            System.out.printf("String.length()=%d, Unicode码点数=%d%n",
                    input.length(), stats.totalCodePoints());
        }
    }
}
