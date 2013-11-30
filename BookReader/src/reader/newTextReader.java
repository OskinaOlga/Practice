package reader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class NewTextReader {

    private static char[] endSymbols = {'.','!','?'};
    private static char[] pauseSymbols = {',',';',':'};
    private static StringBuilder lastWord = new StringBuilder();

    private static Integer[] pauses;
    private static Integer[] sentences;

    public static void tokenizer(String text, Language language) {
        ArrayList<Integer> listSentences = new ArrayList<Integer>();
        ArrayList<Integer> listPauses = new ArrayList<Integer>();
        listPauses.add(0);
        listSentences.add(0);
        int start = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < text.length();i++){
            if (!Character.isLetter(text.charAt(i))){
                if (start != i-1 && sb.length()!= 0){
                    lastWord.setLength(0);
                    lastWord.append(sb.substring(start));
                }
                start = i+1;

                if(сheckEndSymbol(text.charAt(i)))
                    if(сheckEnd(text, i, lastWord.toString(), language)){
                        listSentences.add(i);
                        listPauses.add(i);
                    }
                if(сheckPauseSymbol(text.charAt(i)))
                    if(сheckPause(text, i)){
                        listPauses.add(i);
                    }
            }
            sb.append(text.charAt(i));

        }
        pauses = listPauses.toArray(new Integer[listPauses.size()]);
        sentences = listSentences.toArray(new Integer[listSentences.size()]);
    }

    public static Integer[] getPauses(){
        return pauses;
    }
    public static Integer[] getSentences(){
        return sentences;
    }

    private static boolean сheckEndSymbol(char ch){
        for (char endSymbol : endSymbols) {
            if (ch == endSymbol) {
                return true;
            }
        }
        return false;
    }

    private static boolean сheckPauseSymbol(char ch){
        for (char pauseSymbol : pauseSymbols) {
            if (ch == pauseSymbol) {
                return true;
            }
        }
        return false;
    }

    private static boolean сheckPause(String text, int i){
        Pattern p = Pattern.compile("[,|:|;|!|.|\\?]\\s*[а-я]*[А-Я]*\\s*[,|:|;|!|.|\\?]");
        Matcher m = p.matcher(text.substring(i-20, i+1));
        if(m.find())
            if(m.group(m.groupCount()).contains(lastWord)) return false;

        return true;
    }

    private static boolean сheckEnd(String text, int i, String lastWord, Language language)   {

            for (i++; i < text.length();i++){
                if(text.charAt(i) == ' ')  continue;
                if(text.charAt(i) == '.')  return false;
                if(text.charAt(i) == '\n')  return true;
                if (Character.isUpperCase(text.charAt(i))){
                    if(lastWord.length()==1 && Character.isUpperCase(lastWord.charAt(0)))
                        return false;
                    if(language.isName(lastWord)) return false;
                    else return true;
            }
        }
        return true;
    }

}
