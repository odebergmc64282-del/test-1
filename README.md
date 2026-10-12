# Java 教学仓库增强包 V1（独立增补）

原课程：[Don't Fear OOP — A Zero-Base Java Course with 18 Hands-On Projects](https://github.com/7G-one/Don-t-Fear-OOP-A-Zero-Base-Java-Course-with-18-Hands-On-Projects)

本增强包不复制、不修改原仓库代码；旨在补全教学路径、加深字符串知识、提供独立可运行代码与自动检查，便于作为外部增补材料或后续提交补丁。

## 这一版提供什么

- `docs/课程改造路线.md`：原课程的问题清单及 V1~V4 迭代方案。
- `docs/第10课_字符串进阶.md`：String、StringBuilder、Character、正则表达式、Unicode、拆分陷阱。
- `docs/第17课_图书系统改造建议.md`：数据存储与工程组织的升级方向。
- `src/StringToolkit.java`：按 Unicode 码点统计/抽取/统计词频的可复用工具类。
- `src/TextAnalyzerDemo.java`：交互式练习案例。
- `tests/StringToolkitTest.java`：不依赖第三方库的自动测试，运行时会打印通过数量。
- `exercises/第10课挑战题.md`：分基础、进阶、挑战三级题。
- `run.bat` / `run.sh`：Windows / macOS / Linux 运行脚本。

## 如何运行（JDK 17 或更高）

在此目录下：

Windows PowerShell：
```powershell
.\run.bat
```
macOS/Linux：
```bash
bash run.sh
```

或者手动运行：
```bash
mkdir out
javac -encoding UTF-8 -d out src/StringToolkit.java src/TextAnalyzerDemo.java tests/StringToolkitTest.java
java -cp out StringToolkitTest
java -cp out TextAnalyzerDemo
```

`TextAnalyzerDemo` 支持在控制台输入字符串后输出统计结果。所有源码不使用第三方依赖。

## 与原项目如何配合

先完成原项目 `05_数组.java`、`06_方法.java`，再学习本包 `docs/第10课_字符串进阶.md`。完成挑战题后继续原项目 `07`、`08`、`11`、`12`，最后用《第17课_图书系统改造建议》重新实现终章。

## 重要说明

1. 原仓库 README 提及 JDK 8+，但源码中有 Java 11+ API：第13课 `Files.writeString/Files.readString`，第17课 `String.repeat`。学习时建议统一使用 JDK 17+；如坚持 JDK 8，需要逐项改写相关调用。
2. 原项目第17课以逗号拼接图书字段、读取时 `split(",")`，无法安全处理含逗号、换行或引号的书名。这是演示格式，不应直接用于真实数据存储。
3. 本包仅完成了 **V1 的重点模块**，不是对原仓库所有 18 课完成逐行修订，也尚未配置 Maven/JUnit/Spring Boot。
4. 原仓库根目录未列出 LICENSE 文件；本包没有再分发其源文件。若计划公开发布混合版本，先核对上游许可并征得必要授权。
