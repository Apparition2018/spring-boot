package com.ljh;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonFactoryBuilder;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
import org.junit.jupiter.api.Test;

/**
 * JsonFactory
 * 线程安全的
 *
 * @author ljh
 * created on 2021/7/18 0:20
 */
public class JsonFactoryTest {

    @Test
    public void testBuilder() {
        JsonFactory jsonFactory = new JsonFactoryBuilder()
                /* JsonFactory.Feature */
                // 字段名调用 intern()，默认 true，需要开启 CANONICALIZE_FIELD_NAMES
                .enable(JsonFactory.Feature.INTERN_FIELD_NAMES)
                // 规范化字段名，默认 true
                // 规范化借助 ByteQuadsCanonicalizer 去处理，会根据 Hash 值来计算每个属性名存放的位置
                .enable(JsonFactory.Feature.CANONICALIZE_FIELD_NAMES)
                // 当 ByteQuadsCanonicalizer 处理 Hash 碰撞达到一个阈值时，是否快速失败，默认 true
                // 若触发了阈值，这基本可以确定 是Dos（denial-of-service）攻击，制造了非常多的相同 Hash 值的 key，这在正常情况下几乎是没有发生的
                .enable(JsonFactory.Feature.FAIL_ON_SYMBOL_HASH_OVERFLOW)
                // 是否使用 BufferRecycler、ThreadLocal、SoftReference 来有效的重用底层的输入/输出缓冲区，默认 true
                .enable(JsonFactory.Feature.USE_THREAD_LOCAL_FOR_BUFFER_RECYCLING)
                /* JsonWriteFeature */
                // 字段名是否使用双引号，默认 true
                .enable(JsonWriteFeature.QUOTE_FIELD_NAMES)
                // NaN 等写成字符串，默认 true
                // "NaN" "-Infinity" "Infinity"
                .enable(JsonWriteFeature.WRITE_NAN_AS_STRINGS)
                // 数字携程字符串，默认 false
                .enable(JsonWriteFeature.WRITE_NUMBERS_AS_STRINGS)
                // 取消对非 ASCII 字符的转码，默认 false
                .enable(JsonWriteFeature.ESCAPE_NON_ASCII)
                /* JsonReadFeature */
                // 允许包含未转移控制字符，默认 false
                // 值小于32的 ASCII 字符
                .enable(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS)
                // 允许 \ 转移任何字符，默认 false
                .enable(JsonReadFeature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER)
                // 允许开头为0的数字（如：007），默认 false
                .enable(JsonReadFeature.ALLOW_LEADING_ZEROS_FOR_NUMBERS)
                // 允许.开头的数字（如：.1），默认 false
                .enable(JsonReadFeature.ALLOW_LEADING_DECIMAL_POINT_FOR_NUMBERS)
                // 允许识别 NaN 作为合法的浮点数，默认 false
                .enable(JsonReadFeature.ALLOW_NON_NUMERIC_NUMBERS)
                // 允许缺少值，默认 false
                // "{\"fruits\" : [\"apple\",,\"banana\",,]}"
                .enable(JsonReadFeature.ALLOW_MISSING_VALUES)
                // 允许最后带,，默认 false
                .enable(JsonReadFeature.ALLOW_TRAILING_COMMA)
                .build();
    }
}
