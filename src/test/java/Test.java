import com.sun.jna.platform.win32.WinNT;
import site.yalong.MyKernel;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * @author YaLong
 * @date 2022/5/10 13:42
 */
public class Test {
    public static void main(String[] args) {
        String exe = "游戏找CALL练习实例one.exe";
        int pid = MyKernel.findPidByName(exe);
        WinNT.HANDLE processHandle = MyKernel.getProcessHandle(pid);


    }
}
