@echo off
setlocal
cd /d "%~dp0"
if not exist out mkdir out
javac -encoding UTF-8 -d out src\StringToolkit.java src\TextAnalyzerDemo.java tests\StringToolkitTest.java
if errorlevel 1 exit /b 1
java -cp out StringToolkitTest
if errorlevel 1 exit /b 1
echo.
echo 启动交互演示（Ctrl+Z 然后 Enter 结束）:
java -cp out TextAnalyzerDemo
