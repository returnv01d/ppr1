import com.pracownia.PeselValidator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Assert.*;


public class TestPESELValidator {
    String validPESEL;
    String invalidPESEL;
    @Test
    public void CheckPESELLength(){
        invalidPESEL = "1234567891";
        Assert.assertFalse(PeselValidator.checkPESELLength(invalidPESEL));
        invalidPESEL = "1";
        Assert.assertFalse(PeselValidator.checkPESELLength(invalidPESEL));

        validPESEL = "12345678912";
        Assert.assertTrue(PeselValidator.checkPESELLength(validPESEL));
    }

    @Test
    public void CheckPESELControlDigit(){
        invalidPESEL = "44051401358";
        Assert.assertFalse(PeselValidator.checkControlDigitValidity(invalidPESEL));

        validPESEL = "44041401358";
        Assert.assertTrue(PeselValidator.checkControlDigitValidity(validPESEL));
        validPESEL = "97060502478";
        Assert.assertTrue(PeselValidator.checkControlDigitValidity(validPESEL));
    }

    @Test
    public void CheckPESELNumeric(){
        invalidPESEL = "4405140135a";
        Assert.assertFalse(PeselValidator.isNumeric(invalidPESEL));
        invalidPESEL = "440514 01354";
        Assert.assertFalse(PeselValidator.isNumeric(invalidPESEL));
        invalidPESEL = "440514A01354";
        Assert.assertFalse(PeselValidator.isNumeric(invalidPESEL));
        invalidPESEL = "4405142013-4";
        Assert.assertFalse(PeselValidator.isNumeric(invalidPESEL));

        validPESEL = "098765432112";
        Assert.assertTrue(PeselValidator.isNumeric(validPESEL));
        validPESEL = "012345678910";
        Assert.assertTrue(PeselValidator.isNumeric(validPESEL));
    }
}
