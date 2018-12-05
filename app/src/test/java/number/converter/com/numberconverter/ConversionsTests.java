package number.converter.com.numberconverter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConversionsTests {

    @Test
    public void testFromDecimal(){
        String[] cons = Conversions.fromDecimal("7985");
        assertEquals("17461", cons[0]);
        assertEquals("1F31", cons[1]);
        assertEquals("1111100110001", cons[2]);

        String[] cons2 = Conversions.fromDecimal("9999999");
        assertEquals("46113177", cons2[0]);
        assertEquals("98967F", cons2[1]);
        assertEquals("100110001001011001111111", cons2[2]);

        String[] cons3 = Conversions.fromDecimal("00");
        assertEquals("0", cons3[0]);
        assertEquals("0", cons3[1]);
        assertEquals("0", cons3[2]);

        String[] cons4 = Conversions.fromDecimal("001111990");
        assertEquals("4173666", cons4[0]);
        assertEquals("10F7B6", cons4[1]);
        assertEquals("100001111011110110110", cons4[2]);

        String[] cons5 = Conversions.fromDecimal("99999922277384");
        assertEquals("2657141366046010", cons5[0]);
        assertEquals("5AF30BD84C08", cons5[1]);
        assertEquals("10110101111001100001011110110000100110000001000", cons5[2]);

    }

    @Test
    public void testFromOctal(){
        String[] cons = Conversions.fromOctal("1177254");
        assertEquals("327340", cons[0]);
        assertEquals("4FEAC", cons[1]);
        assertEquals("1001111111010101100", cons[2]);

        String[] cons2 = Conversions.fromOctal("62361");
        assertEquals("25841", cons2[0]);
        assertEquals("64F1", cons2[1]);
        assertEquals("110010011110001", cons2[2]);

        String[] cons3 = Conversions.fromOctal("001234");
        assertEquals("668", cons3[0]);
        assertEquals("29C", cons3[1]);
        assertEquals("1010011100", cons3[2]);

        String[] cons4 = Conversions.fromOctal("00");
        assertEquals("0", cons4[0]);
        assertEquals("0", cons4[1]);
        assertEquals("0", cons4[2]);

        String[] cons5 = Conversions.fromOctal("777777");
        assertEquals("262143", cons5[0]);
        assertEquals("3FFFF", cons5[1]);
        assertEquals("111111111111111111", cons5[2]);

        String[] cons6 = Conversions.fromOctal("115362");
        assertEquals("39666", cons6[0]);
        assertEquals("9AF2", cons6[1]);
        assertEquals("1001101011110010", cons6[2]);
    }

    @Test
    public void testFromHex(){
        String[] cons = Conversions.fromHex("9034feb");
        assertEquals("151212011", cons[0]);
        assertEquals("1100647753", cons[1]);
        assertEquals("1001000000110100111111101011", cons[2]);

        String[] cons2 = Conversions.fromHex("deadbeef");
        assertEquals("3735928559", cons2[0]);
        assertEquals("33653337357", cons2[1]);
        assertEquals("11011110101011011011111011101111", cons2[2]);

        String[] cons3 = Conversions.fromHex("f7ffee32");
        assertEquals("4160745010", cons3[0]);
        assertEquals("36777767062", cons3[1]);
        assertEquals("11110111111111111110111000110010", cons3[2]);

        String[] cons4 = Conversions.fromHex("badcafe");
        assertEquals("195939070", cons4[0]);
        assertEquals("1353345376", cons4[1]);
        assertEquals("1011101011011100101011111110", cons4[2]);

        String[] cons5 = Conversions.fromHex("FFAE990");
        assertEquals("268102032", cons5[0]);
        assertEquals("1776564620", cons5[1]);
        assertEquals("1111111110101110100110010000", cons5[2]);
    }

    @Test
    public void testFromBinary(){
        String[] cons = Conversions.fromBinary("1111111110101110100110010000");
        assertEquals("268102032", cons[0]);
        assertEquals("1776564620", cons[1]);
        assertEquals("FFAE990", cons[2]);

        String[] cons2 = Conversions.fromBinary("111000111001010111001");
        assertEquals("1864377", cons2[0]);
        assertEquals("7071271", cons2[1]);
        assertEquals("1C72B9", cons2[2]);

        String[] cons3 = Conversions.fromBinary("00011010010110010010111001");
        assertEquals("6907065", cons3[0]);
        assertEquals("32262271", cons3[1]);
        assertEquals("6964B9", cons3[2]);

        String[] cons4 = Conversions.fromBinary("00");
        assertEquals("0", cons4[0]);
        assertEquals("0", cons4[1]);
        assertEquals("0", cons4[2]);

        String[] cons5 = Conversions.fromBinary("11111100111000111");
        assertEquals("129479", cons5[0]);
        assertEquals("374707", cons5[1]);
        assertEquals("1F9C7", cons5[2]);

        String[] cons6 = Conversions.fromBinary("11001");
        assertEquals("25", cons6[0]);
        assertEquals("31", cons6[1]);
        assertEquals("19", cons6[2]);
    }
}
