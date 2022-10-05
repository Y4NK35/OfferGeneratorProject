package offerGenerator.component.mailer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:email.properties")
public class SignUpMailTextFactory {
    @Value("${email.confirmation.subject}")
    private String subject;
    @Value("${email.confirmation.text}")
    private String text;
    @Value("${email.confirmation.link}")
    private String link;
    @Value("${email.confirmation.hello}")
    private String hello;
    @Value("${email.confirmation.ending}")
    private String ending;

    public String getConfirmationMailSubject(){
        return subject;
    }
    public String getConfirmationMailText(String username,String token){
        return hello+username+text+link+token+ending;
    }
}
