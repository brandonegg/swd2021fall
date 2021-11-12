import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class testCodeConverter {

    @Test
    public void testConvertEnglish() {
        CodeConverter codeConverter = new CodeConverter(new MorseLibrary());

        assertEquals(".... . .-.. .-.. ---   .-- --- .-. .-.. -..", codeConverter.convertEnglishSentence("Hello World"));
        assertEquals(".- .-.. .--. .... .- -... . -", codeConverter.convertEnglishSentence("alphabet"));
        assertEquals("-..- -.-- .-.. --- .--. .... --- -. .   .--. .-.. .- -.-- . .-. ...   .- .-. .   -.-. --- --- .-..", codeConverter.convertEnglishSentence("xylophone players are cool"));
        assertEquals("", codeConverter.convertEnglishSentence(""));
    }

    @Test
    public void testConvertMorse() {
        CodeConverter codeConverter = new CodeConverter(new MorseLibrary());

        assertEquals("HELLO WORLD", codeConverter.convertMorseSentence(".... . .-.. .-.. ---   .-- --- .-. .-.. -.."));
        assertEquals("ALPHABET", codeConverter.convertMorseSentence(".- .-.. .--. .... .- -... . -"));
        assertEquals("XYLOPHONE PLAYERS ARE COOL", codeConverter.convertMorseSentence("-..- -.-- .-.. --- .--. .... --- -. .   .--. .-.. .- -.-- . .-. ...   .- .-. .   -.-. --- --- .-.."));
        assertEquals("", codeConverter.convertMorseSentence(""));
    }

}
