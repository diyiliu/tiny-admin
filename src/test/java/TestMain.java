import com.diyiliu.support.util.PasswordHelper;
import org.junit.Test;

/**
 * Description: TestMain
 * Author: DIYILIU
 * Update: 2019-05-24 10:11
 */
public class TestMain {

    @Test
    public void testPwd(){
        PasswordHelper helper = new PasswordHelper();
        System.out.println(helper.encryptPassword("admin", "123456", "6a75262bcb161d22eae1638f4a75bd14"));
    }
}
