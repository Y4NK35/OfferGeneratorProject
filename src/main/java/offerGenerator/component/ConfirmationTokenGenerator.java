package offerGenerator.component;


import org.springframework.stereotype.Component;
import java.util.Random;
@Component
public class ConfirmationTokenGenerator {
        private final static String chars= "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        public final static String getConfirmationToken(int tokenLength){

        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for(int i=0; i< tokenLength; i++){
            stringBuilder.append(chars.charAt(random.nextInt(chars.length())));
        }
        return stringBuilder.toString();
    }
}
