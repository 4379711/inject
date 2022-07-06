import com.sun.jna.platform.win32.WinDef;
import site.yalong.MyUser32;

/**
 * @author YaLong
 * @date 2022/5/10 13:42
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
//        for (int i = 0; i < 101; i++) {
//            sendString("//aaa乌 漆@麻!^&黑!abcd" + i);
//            keyPressed(13);
//            keyReleased(13);
//        }


        WinDef.HWND hwnd = MyUser32.USER32.FindWindow("test_send_window", null);
        System.out.println(hwnd);
        MyUser32.sendMsg(hwnd, "aaaa乌漆嘛黑!@#$");

    }
}
