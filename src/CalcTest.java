import org.junit.Assert;
import org.junit.Test;

/**
 * Created by НОЦ Робототехники on 09.07.2017.
 */
public class CalcTest {
    public Formula calcTestForSumm;
    public Formula calcTestForSub;
    public Formula calcTestForMulti;
    public Formula calcTestForDiv;
    public Formula testParent;


    @Test
    public void summTest () {
        calcTestForSumm = new Formula("2 + 3 ");
        Assert.assertEquals("Error summ","5",calcTestForSumm.Estimate());
    }
    @Test
    public  void subTest () {
        calcTestForSub = new Formula("2 - 3 ");
        Assert.assertEquals("Error sub","-1",calcTestForSub.Estimate());
    }
    @Test
    public  void multiTest () {
        calcTestForMulti = new Formula("2 * 3 ");
        Assert.assertEquals("Error multi","6",calcTestForMulti.Estimate());

    }
    @Test
    public void divTest () {
        calcTestForDiv = new Formula("2 / 3 ");
        Assert.assertEquals("Error div","0",calcTestForDiv.Estimate());
    }
    @Test
    public void parentheses (){
        testParent = new Formula("( 8 - 3 ) / 2 ");
        Assert.assertEquals("Error ","2",testParent.Estimate());
    }

}
