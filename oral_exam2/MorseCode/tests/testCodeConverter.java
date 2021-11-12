import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class for main code converter class
 * @see CodeConverter
 */
public class testCodeConverter {

    /**
     * Tests the convertEnglishSentence() method
     * within CodeConverter
     * @see CodeConverter
     */
    @Test
    public void testConvertEnglish() {
        CodeConverter codeConverter = new CodeConverter(new MorseLibrary());

        assertEquals(".... . .-.. .-.. ---   .-- --- .-. .-.. -..", codeConverter.convertEnglishSentence("Hello World"));
        assertEquals(".- .-.. .--. .... .- -... . -", codeConverter.convertEnglishSentence("alphabet"));
        assertEquals("-..- -.-- .-.. --- .--. .... --- -. .   .--. .-.. .- -.-- . .-. ...   .- .-. .   -.-. --- --- .-..", codeConverter.convertEnglishSentence("xylophone players are cool"));
        assertEquals("", codeConverter.convertEnglishSentence(""));
    }

    /**
     * Tests the convertMorseSentence() method
     * within CodeConverter
     * @see CodeConverter
     */
    @Test
    public void testConvertMorse() {
        CodeConverter codeConverter = new CodeConverter(new MorseLibrary());

        assertEquals("HELLO WORLD", codeConverter.convertMorseSentence(".... . .-.. .-.. ---   .-- --- .-. .-.. -.."));
        assertEquals("ALPHABET", codeConverter.convertMorseSentence(".- .-.. .--. .... .- -... . -"));
        assertEquals("XYLOPHONE PLAYERS ARE COOL", codeConverter.convertMorseSentence("-..- -.-- .-.. --- .--. .... --- -. .   .--. .-.. .- -.-- . .-. ...   .- .-. .   -.-. --- --- .-.."));
        assertEquals("", codeConverter.convertMorseSentence(""));
    }

}
