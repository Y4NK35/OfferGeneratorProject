import offerGenerator.component.ConfirmationTokenGenerator;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.AssertTrue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TokenGeneratorTest {

    int tokenlength =20;
@Test
public void tokenGeneratorLengthTest(){

        String token = ConfirmationTokenGenerator.getConfirmationToken(tokenlength);
        String token2 = ConfirmationTokenGenerator.getConfirmationToken(tokenlength);
        assertEquals(tokenlength,token.length());
}
@Test
public void tokenGenerator2TokenNotEquals(){
        String token = ConfirmationTokenGenerator.getConfirmationToken(tokenlength);
        String token2 = ConfirmationTokenGenerator.getConfirmationToken(tokenlength);
        assertNotEquals(token,token2);
    }


}
