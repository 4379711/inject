package site.yalong;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.ptr.IntByReference;

/**
 * @author yaLong
 */
public class MyUser32 {
    private static final User32 USER32 = User32.INSTANCE;

    /**
     * 找进程的pid
     *
     * @param lpClassName  窗口类名
     * @param lpWindowName 窗口名
     * @return 进程的pid
     */
    public static int findPid(String lpClassName, String lpWindowName) {
        WinDef.HWND hwnd = USER32.FindWindow(lpClassName, lpWindowName);
        if (hwnd == null) {
            throw new RuntimeException("未找到窗口");
        }
        IntByReference pidRef = new IntByReference();
        USER32.GetWindowThreadProcessId(hwnd, pidRef);
        return pidRef.getValue();
    }
}
