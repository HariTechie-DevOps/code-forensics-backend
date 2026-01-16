package com.forensic.engine.util;

public class CodeParserUtils {
    public static String cleanCode(String rawCode) {
        if (rawCode == null) return "";
        // Remove single line comments and extra whitespace
        return rawCode.replaceAll("//.*", "")
                      .replaceAll("\\s+", " ")
                      .trim();
    }
}
