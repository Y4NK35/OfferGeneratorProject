package offerGenerator.component.mailer;

import offerGenerator.component.ConfirmationTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class SignUpMailer {
    private JavaMailSender mailSender;
    private SignUpMailTextFactory signUpMailTextFactory;
    @Autowired
    SignUpMailer (JavaMailSender mailSender, SignUpMailTextFactory signUpMailTextFactory){
      this.mailSender = mailSender;
      this.signUpMailTextFactory =signUpMailTextFactory;
    }
  public void sendConfirmationLink(String username, String email, String token){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(signUpMailTextFactory.getConfirmationMailSubject());
        message.setText(signUpMailTextFactory.getConfirmationMailText(username, token));
        mailSender.send(message);
  }
}
