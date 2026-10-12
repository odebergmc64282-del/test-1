import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 第10课的补充工具类：Unicode 码点级分类和字符串处理。
 * JDK 17+；无外部依赖。
 */
public final class StringToolkit {
    private StringToolkit() {
        // 工具类禁止实例化
    }

    /** 统计的是 Unicode 码点，而不是 UTF-16 char 数量。 */
    public static Stats analyze(String input) {
        Objects.requireNonNull(input, "input 不能为 null");
        int letters = 0;
        int digits = 0;
        int whitespace = 0;
        int others = 0;

        for (int offset = 0; offset < input.length();) {
            int codePoint = input.codePointAt(offset);
            if (Character.isLetter(codePoint)) {
                letters++;
            } else if (Character.isDigit(codePoint)) {
                digits++;
            } else if (Character.isWhitespace(codePoint)) {
                whitespace++;
            } else {
                others++;
            }
            offset += Character.charCount(codePoint);
        }
        return new Stats(letters, digits, whitespace, others);
    }

    /** 提取 Unicode 十进制数字，原样保留其写法（例如阿拉伯数字）。 */
    public static String extractDigits(String input) {
        Objects.requireNonNull(input, "input 不能为 null");
        StringBuilder result = new StringBuilder();
        input.codePoints()
                .filter(Character::isDigit)
                .forEach(result::appendCodePoint);
        return result.toString();
    }

    /** 先提取数字，再逆序；StringBuilder.reverse 会正确保持代理对。 */
    public static String reverseDigits(String input) {
        return new StringBuilder(extractDigits(input)).reverse().toString();
    }

    /** 字符频率：LinkedHashMap 保留首次出现顺序。 */
    public static Map<String, Integer> codePointFrequency(String input) {
        Objects.requireNonNull(input, "input 不能为 null");
        Map<String, Integer> frequencies = new LinkedHashMap<>();
        input.codePoints().forEach(cp -> {
            String c = new String(Character.toChars(cp));
            frequencies.merge(c, 1, Integer::sum);
        });
        return frequencies;
    }

    /** 简单分隔文本时保留尾部空值；注意这仍不是通用 CSV 解析器。 */
    public static String[] splitPreservingEmpty(String input, String literalDelimiter) {
        Objects.requireNonNull(input, "input 不能为 null");
        Objects.requireNonNull(literalDelimiter, "delimiter 不能为 null");
        if (literalDelimiter.isEmpty()) {
            throw new IllegalArgumentException("delimiter 不能为空");
        }
        return input.split(java.util.regex.Pattern.quote(literalDelimiter), -1);
    }

    public static final class Stats {
        private final int letters;
        private final int digits;
        private final int whitespace;
        private final int others;

        private Stats(int letters, int digits, int whitespace, int others) {
            this.letters = letters;
            this.digits = digits;
            this.whitespace = whitespace;
            this.others = others;
        }
        public int letters() { return letters; }
        public int digits() { return digits; }
        public int whitespace() { return whitespace; }
        public int others() { return others; }
        public int totalCodePoints() { return letters + digits + whitespace + others; }

        @Override
        public String toString() {
            return String.format("字母=%d, 数字=%d, 空白=%d, 其他=%d, 总码点=%d",
                    letters, digits, whitespace, others, totalCodePoints());
        }
    }
}
