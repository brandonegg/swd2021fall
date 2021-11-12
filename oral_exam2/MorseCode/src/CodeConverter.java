public class CodeConverter {

    MorseLibrary codeLibrary;

    public CodeConverter(MorseLibrary libraryObj) {
        codeLibrary = libraryObj;
    }

    public String convertEnglishSentence(String englishSentence) {
        StringBuilder strBldr = new StringBuilder();

        for (String englishWord : englishSentence.split(" ")) {
            for (Character englishLetter : englishWord.toCharArray()) {
                strBldr.append(codeLibrary.convertEnglishCharacter(englishLetter) + " ");
            }
            strBldr.append("  ");
        }
        strBldr.delete(strBldr.length()-3, strBldr.length());

        return strBldr.toString();
    }

    public String convertMorseSentence(String morseSentence) {
        StringBuilder strBldr = new StringBuilder();

        for (String morseWord : morseSentence.split("   ")) {
            for (String morseCharacter : morseWord.split(" ")) {
                strBldr.append(codeLibrary.converMorseCharacter(morseCharacter));
            }
            strBldr.append(" ");
        }
        strBldr.delete(strBldr.length()-1, strBldr.length());

        return strBldr.toString();
    }

}
