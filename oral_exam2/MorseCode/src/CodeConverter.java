public class CodeConverter {

    /**
     * Reference to Morse code library object
     */
    MorseLibrary codeLibrary;

    /**
     * Takes a reference morse code library object and initializes
     * it to a local variable.
     * @param libraryObj
     */
    public CodeConverter(MorseLibrary libraryObj) {
        codeLibrary = libraryObj;
    }

    /**
     * Takes an English letters sentence and converts the sentence into Morse code.
     * @param englishSentence English sentence to convert
     * @return  Morse code sentence equivalent
     * @see MorseLibrary
     */
    public String convertEnglishSentence(String englishSentence) {
        StringBuilder strBldr = new StringBuilder();

        if (englishSentence.length()>0) {
            for (String englishWord : englishSentence.split(" ")) {
                for (Character englishLetter : englishWord.toCharArray()) {
                    strBldr.append(codeLibrary.convertEnglishCharacter(englishLetter) + " ");
                }
                strBldr.append("  ");
            }
        }
        if (strBldr.length() > 3) {
            strBldr.delete(strBldr.length() - 3, strBldr.length());
        }

        return strBldr.toString();
    }

    /**
     * Take Morse code sentence and convert into English sentence.
     * @param morseSentence Morse code input string
     * @return  English sentence equivalent
     * @see MorseLibrary
     */
    public String convertMorseSentence(String morseSentence) {
        StringBuilder strBldr = new StringBuilder();

        if (morseSentence.length() > 0) {
            for (String morseWord : morseSentence.split("   ")) {
                for (String morseCharacter : morseWord.split(" ")) {
                    strBldr.append(codeLibrary.converMorseCharacter(morseCharacter));
                }
                strBldr.append(" ");
            }
        }
        if (strBldr.length()>1) {
            strBldr.delete(strBldr.length() - 1, strBldr.length());
        }
        return strBldr.toString();
    }

}
