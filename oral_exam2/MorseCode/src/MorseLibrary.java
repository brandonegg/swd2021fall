import java.util.HashMap;
import java.util.NoSuchElementException;

public class MorseLibrary {

    private final HashMap<Character, String> codeMap;

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
        addCode('S', "...");
        addCode('T', "-");
        addCode('U', "..-");
        addCode('V', "...-");
        addCode('W', ".--");
        addCode('X', "-..-");
        addCode('Y', "-.--");
        addCode('Z', "--..");
    }

    public String convertEnglishCharacter(Character letter) {
        if (codeMap.containsKey(Character.toUpperCase(letter))) {
            return codeMap.get(Character.toUpperCase(letter));
        } else {
            throw new NoSuchElementException("'"+letter.toString()+"' does not have a morse code equivalent!");
        }
    }

    public Character converMorseCharacter(String morseLetter) {
        for (Character key : codeMap.keySet()) {
            if (codeMap.get(key).equals(morseLetter)) {
                return key;
            }
        }

        throw new NoSuchElementException("'" + morseLetter + "' is not a valid morse code character");
    }

    public void addCode(Character letter, String morseLetter) {
        codeMap.put(letter, morseLetter);
    }


}
