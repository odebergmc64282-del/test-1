#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")"
mkdir -p out
javac -encoding UTF-8 -d out src/StringToolkit.java src/TextAnalyzerDemo.java tests/StringToolkitTest.java
java -cp out StringToolkitTest
printf '\n运行交互演示（Ctrl+D 结束）:\n'
java -cp out TextAnalyzerDemo
