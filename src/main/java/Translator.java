import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class Translator {
    private static Translator instance;
    private String key = "trnsl.1.1.20190331T211139Z.3b53466eb36697ee.621abba34589e6e66b5799a9500ebf9cfe32efce";
    private String[] langs = {"en","ru"};   //поддерживаемые языки


    private Translator(){}
    public static Translator getInstance(){
        if(instance == null){
            instance = new Translator();
        }
        return instance;
    }

    public String translate(String text) throws IOException{
        String lang = detectLang(text);
        int lang_i = (lang.equals("en")) ? 1 : 0;
        String request = String.format("https://translate.yandex.net/api/v1.5/tr.json/translate?key=%s&text=%s&lang=%s",key,text,langs[lang_i]);
        URL url = new URL(request);
        Scanner in = new Scanner((InputStream)url.getContent());
        JSONObject object = new JSONObject(in.nextLine());
        JSONArray arr = object.getJSONArray("text");
        return arr.getString(0);
    }

    public String detectLang(String text) throws IOException {
        String request = String.format("https://translate.yandex.net/api/v1.5/tr.json/detect?key=%s&text=%s&hint=%s,%s",key,text,langs[0],langs[1]);
        URL url = new URL(request);
        Scanner in = new Scanner((InputStream)url.getContent());
        JSONObject object = new JSONObject(in.nextLine());
        return object.getString("lang");
    }

}
