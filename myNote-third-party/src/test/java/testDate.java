import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhishubin
 * @date 2023/12/27 17:55
 * @description
 */
public class testDate {

    public static void main(String[] args) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String currentDate = dateFormat.format(new Date());
        System.out.println(currentDate);
    }
}
