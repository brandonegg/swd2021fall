import java.util.HashMap;

public class CodeLibrary {

    private HashMap<Character, String> codeMap;

    public CodeLibrary() {
        codeMap = new HashMap<>();
        codeMap.put('A', ".-");
        codeMap.put('B', "-...");
        codeMap.put('C', "-.-.");
        codeMap.put('D', "-..");
        codeMap.put('E', ".");
        codeMap.put('F', "..-.");
        codeMap.put('G', "--.");
        codeMap.put('H', "....");
        codeMap.put('J', ".---");
        codeMap.put('K', "-.-");
        codeMap.put('L', ".-..");
        codeMap.put('M', "--");
        codeMap.put('N', "-.");
        codeMap.put('O', "---");
        codeMap.put('P', ".--.");
        codeMap.put('Q', "--.-");
        codeMap.put('S', "...");
        codeMap.put('T', "-");
        codeMap.put('U', "..-");
        codeMap.put('V', "...-");
        codeMap.put('W', ".--");
        codeMap.put('X', "-..-");
        codeMap.put('Y', "-.--");
        codeMap.put('Z', "--..");
    }

}
