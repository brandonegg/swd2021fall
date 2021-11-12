import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * Library object for storing and converting individual
 * English letters and Morse letter to their code equivalent value
 * @see HashMap
 */
public class MorseLibrary {

    /**
     * Stores morse code conversions, key values represent
     * English letters and associated values represent equivalent
     * morse code character string.
     */
    private final HashMap<Character, String> codeMap;

    /**
     * Constructs the code library stored in the codeMap HashMap.
     * Taken from official Morse code conversion sheet
     */
    public MorseLibrary() {
        codeMap = new HashMap<>();
        addCode('A', ".-");
        addCode('B', "-...");
        addCode('C', "-.-.");
        addCode('D', "-..");
        addCode('E', ".");
        addCode('F', "..-.");
        addCode('G', "--.");
        addCode('H', "....");
        addCode('I', "..");
        addCode('J', ".---");
        addCode('K', "-.-");
        addCode('L', ".-..");
        addCode('M', "--");
        addCode('N', "-.");
        addCode('O', "---");
        addCode('P', ".--.");
        addCode('Q', "--.-");
        addCode('R', ".-.");
        addCode('S', "...");
        addCode('T', "-");
        addCode('U', "..-");
        addCode('V', "...-");
        addCode('W', ".--");
        addCode('X', "-..-");
        addCode('Y', "-.--");
        addCode('Z', "--..");
    }

    /**
     * Takes an english character and returns its morse code equivalent.
     * @param letter    English letter, stored in Character object
     * @return  String storing the morse code letter equivalent
     */
    public String convertEnglishCharacter(Character letter) {
        if (codeMap.containsKey(Character.toUpperCase(letter))) {
            return codeMap.get(Character.toUpperCase(letter));
        } else {
            throw new NoSuchElementException("'"+letter.toString()+"' does not have a morse code equivalent!");
        }
    }

    /**
     * Takes a morse code letter and returns its english letter equivalent.
     * @param morseLetter   Morse code single letter String object
     * @return  English character associated with input morseLetter
     */
    public Character converMorseCharacter(String morseLetter) {
        for (Character key : codeMap.keySet()) {
            if (codeMap.get(key).equals(morseLetter)) {
                return key;
            }
        }

        throw new NoSuchElementException("'" + morseLetter + "' is not a valid morse code character");
    }

    /**
     * Method for adding code to the codeMap HashMap
     * @param letter    English letter
     * @param morseLetter   Associated morse code letter
     */
    public void addCode(Character letter, String morseLetter) {
        codeMap.put(letter, morseLetter);
    }


}
