import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Main {
    private static String BOT_NAME = "My Own Personal Bot";
    private static String BOT_TOKEN = "753469648:AAH4mJ7cOSfVga8QRBpPC8-gzdDtWb5sGzA" /* your bot's token here */;

    public static void main(String[] args){

        try{
            ApiContextInitializer.init();
            TelegramBotsApi botsApi = new TelegramBotsApi();
            Bot bot = new Bot(BOT_TOKEN, BOT_NAME);
            botsApi.registerBot(bot);
        }catch(TelegramApiException e){
            e.printStackTrace();
        }

    }

}
