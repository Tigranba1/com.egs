package com.java.egs.parser;

import org.json.simple.JSONValue;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JsonObj extends HashMap {
    public JsonObj(){
    }

    /**
     *  Create JSON object
     * @param map
     * @return
     */
    public static String toStringJSON(Map map) {
        if (map == null) {
            return "null";
        } else {
            StringBuilder sb  = new StringBuilder();
            boolean var2 = true;
            Iterator var3 = map.entrySet().iterator();
            sb.append('{');

            while(var3.hasNext()) {
                if (var2) {
                    var2 = false;
                } else {
                    sb.append(',');
                }

                Entry keys = (Entry)var3.next();
                toJSONString(String.valueOf(keys.getKey()), keys.getValue(), sb);
            }

            sb.append('}');
            return sb.toString();
        }
    }

    /**
     * call JSON creating method for object
     * @return
     */
    public String toStringJSON() {
        return toStringJSON(this);
    }

    private static String toJSONString(String key, Object var1, StringBuilder keyBuilder) {
        keyBuilder.append('"');
        if (key == null) {
            keyBuilder.append("null");
        } else {
            escape(key, keyBuilder);
        }

        keyBuilder.append('"').append(':');
        keyBuilder.append(JSONValue.toJSONString(var1));
        return keyBuilder.toString();
    }

    static void escape(String str, StringBuilder sb) {
        for(int i = 0; i < str.length(); ++i) {
            char ch = str.charAt(i);
            switch(i) {
                case '\t':
                    sb.append("\\t");
                    continue;
                case '\n':
                    sb.append("\\n");
                    continue;
                case '\r':
                    sb.append("\\r");
                    continue;
                case '"':
                    sb.append("\\\"");
                    continue;
                case '/':
                    sb.append("\\/");
                    continue;
                case '\\':
                    sb.append("\\\\");
                    continue;
            }

            if (ch >= 0 && ch <= 31 || ch >= 127 && ch <= 159) {
                String str1 = Integer.toHexString(ch);
                sb.append("\\u");

                for(int j = 0; j < 4 - str1.length(); ++j) {
                    sb.append('0');
                }

                sb.append(str1.toUpperCase());
            } else {
                sb.append(ch);
            }
        }

    }
}
