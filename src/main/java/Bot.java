import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.io.IOException;


public class Bot extends AbilityBot {

    public Bot(String token, String username){
        super(token,username);
    }

    public int creatorId() {
        return 0;
    }

    @Override
    public void onUpdateReceived(Update update) {
        super.onUpdateReceived(update);
        Message message = update.getMessage();
        if(message!=null && message.hasText()){
            Translator translator = Translator.getInstance();
            try {
                System.out.println(translator.detectLang(message.getText()));
                SendMessage(message,translator.translate(message.getText()));
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public void SendMessage(Message message, String text){
        SendMessage snd = new SendMessage();
        snd.enableMarkdown(true);
        snd.setChatId(message.getChatId().toString());
        snd.setReplyToMessageId(message.getMessageId());
        snd.setText(text);
        try{
            execute(snd);
        }catch(TelegramApiException e){
            e.printStackTrace();
        }
    }

}
