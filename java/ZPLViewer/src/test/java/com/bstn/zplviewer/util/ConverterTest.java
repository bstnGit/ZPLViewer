package com.bstn.zplviewer.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class ConverterTest {
    @Test
    public void testParametersStringToList() {
        // Test when the parameter has fewer elements than expectedSize
        String parameter2 = "red,green";
        int expectedSize2 = 5;
        String delimiter2 = ",";
        List<String> result2 = Converter.parametersStringToList(parameter2, expectedSize2, delimiter2);
        List<String> expected2 = Arrays.asList("red", "green", "default", "default", "default");
        assertEquals(expected2, result2);

        // Test when the parameter is empty
        String parameter3 = ",,";
        int expectedSize3 = 3;
        String delimiter3 = ",";
        List<String> result3 = Converter.parametersStringToList(parameter3, expectedSize3, delimiter3);
        List<String> expected3 = Arrays.asList("default", "default", "default");
        assertEquals(expected3, result3);
    }
}
