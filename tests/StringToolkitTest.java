import java.util.Map;
import java.util.Objects;

/**
 * 零依赖测试器：执行 main 后输出通过/总数，失败则退出非零状态。
 * 进阶项目建议替换为 JUnit 5。
 */
public class StringToolkitTest {
    private static int count = 0;

    private static void check(Object expected, Object actual, String name) {
        count++;
        if (!Objects.equals(expected, actual)) {
            throw new AssertionError(name + "：期望=" + expected + "，实际=" + actual);
        }
    }

    private static void checkThrows(Runnable code, Class<? extends Throwable> type, String name) {
        count++;
        try {
            code.run();
        } catch (Throwable err) {
            if (type.isInstance(err)) return;
            throw new AssertionError(name + "：预期 " + type.getName() + "，实际 " + err, err);
        }
        throw new AssertionError(name + "：预期抛异常，但没有抛出");
    }

    public static void main(String[] args) {
        String s = "wosno21... ";
        StringToolkit.Stats stats = StringToolkit.analyze(s);
        check(5, stats.letters(), "英文统计");
        check(2, stats.digits(), "数字统计");
        check(1, stats.whitespace(), "空白统计");
        check(3, stats.others(), "标点统计");
        check(11, stats.totalCodePoints(), "分类总数");
        check(11, s.replace("[^0-9]", "").length(), "replace 按字面匹配");
        check(2, s.replaceAll("[^0-9]", "").length(), "replaceAll 用正则匹配");

        check("123456", StringToolkit.extractDigits("abc123Java456"), "提取数字");
        check("654321", StringToolkit.reverseDigits("abc123Java456"), "倒序数字");
        check("", StringToolkit.extractDigits(""), "空字符串");
        check(4, StringToolkit.splitPreservingEmpty("a,b,,", ",").length, "保留尾部空段");
        check(2, "a,b,,".split(",").length, "默认丢弃尾部空段");
        check(3, StringToolkit.splitPreservingEmpty("a.b.c", ".").length, "按字面句号分隔");

        String unicode = "中A٣😀 "; // 阿拉伯数字 ٣、Emoji 😀
        StringToolkit.Stats u = StringToolkit.analyze(unicode);
        check(2, u.letters(), "Unicode 字母");
        check(1, u.digits(), "Unicode 数字");
        check(1, u.whitespace(), "Unicode 空白");
        check(1, u.others(), "Emoji 属于其他");
        check(5, u.totalCodePoints(), "Unicode 码点数");
        check(6, unicode.length(), "UTF-16 代码单元数");
        check("٣", StringToolkit.extractDigits(unicode), "提取非 ASCII 数字");
        check(2, StringToolkit.codePointFrequency("😀A😀").get("😀"), "Emoji 频率");
        check(1, StringToolkit.codePointFrequency("😀A😀").get("A"), "字母频率");
        checkThrows(() -> StringToolkit.analyze(null), NullPointerException.class, "null 参数防御");
        checkThrows(() -> StringToolkit.splitPreservingEmpty("x", ""), IllegalArgumentException.class, "空分隔符防御");

        System.out.printf("全部通过：%d / %d 项测试%n", count, count);
    }
}
