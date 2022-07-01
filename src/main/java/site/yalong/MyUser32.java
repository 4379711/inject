package site.yalong;

import com.sun.jna.platform.win32.*;
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

    private static WinUser.INPUT buildBaseInput() {
        WinUser.INPUT ip = new WinUser.INPUT();
        ip.type = new WinDef.DWORD(WinUser.INPUT.INPUT_KEYBOARD);
        ip.input.setType("ki");
        ip.input.ki.time = new WinDef.DWORD(Kernel32.INSTANCE.GetTickCount());
        ip.input.ki.dwExtraInfo = new BaseTSD.ULONG_PTR(0);
        return ip;
    }

    /**
     * 键按下
     */
    public static void keyPressed(int keyCode) {
        WinUser.INPUT ip = buildBaseInput();
        ip.input.ki.wScan = new WinDef.WORD(0);
        ip.input.ki.wVk = new WinDef.WORD(keyCode);
        ip.input.ki.dwFlags = new WinDef.DWORD(0);
        User32.INSTANCE.SendInput(new WinDef.DWORD(1), new WinUser.INPUT[]{ip}, ip.size());
    }

    /**
     * 键抬起
     */
    public static void keyReleased(int keyCode) {
        WinUser.INPUT ip = buildBaseInput();
        ip.input.ki.wScan = new WinDef.WORD(0);
        ip.input.ki.wVk = new WinDef.WORD(keyCode);
        ip.input.ki.dwFlags = new WinDef.DWORD(WinUser.KEYBDINPUT.KEYEVENTF_KEYUP);
        User32.INSTANCE.SendInput(new WinDef.DWORD(1), new WinUser.INPUT[]{ip}, ip.size());
    }


    /**
     * 字符串转换unicode
     */
    public static String string2Unicode(String string) {
        StringBuilder unicode = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            unicode.append(String.format("\\u%4s", Integer.toHexString(c)).replaceAll("\\s", "0"));
        }
        return unicode.toString();
    }

    /**
     * unicode转字符串
     */
    public static String unicode2String(String unicode) {
        StringBuilder string = new StringBuilder();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            int data = Integer.parseInt(hex[i], 16);
            string.append((char) data);
        }
        return string.toString();
    }

    public static void sendString(String string) {
        for (char aChar : string.toCharArray()) {
            // 这里不可以直接new一个java数组,必须使用INPUT().toArray
            WinUser.INPUT[] inputs = (WinUser.INPUT[]) new WinUser.INPUT().toArray(2);
            inputs[0].type = new WinDef.DWORD(WinUser.INPUT.INPUT_KEYBOARD);
            inputs[0].input.setType("ki");
            inputs[0].input.ki.time = new WinDef.DWORD(Kernel32.INSTANCE.GetTickCount());
            inputs[0].input.ki.dwExtraInfo = new BaseTSD.ULONG_PTR(0);
            inputs[0].input.ki.wScan = new WinDef.WORD(aChar);
            //注意这里,当输入为KEYEVENTF_UNICODE时,wVk必须为0
            inputs[0].input.ki.wVk = new WinDef.WORD(0);
            inputs[0].input.ki.dwFlags = new WinDef.DWORD(WinUser.KEYBDINPUT.KEYEVENTF_UNICODE);

            inputs[1].type = new WinDef.DWORD(WinUser.INPUT.INPUT_KEYBOARD);
            inputs[1].input.setType("ki");
            inputs[1].input.ki.time = new WinDef.DWORD(Kernel32.INSTANCE.GetTickCount());
            inputs[1].input.ki.dwExtraInfo = new BaseTSD.ULONG_PTR(0);
            inputs[1].input.ki.wScan = new WinDef.WORD(aChar);
            inputs[1].input.ki.wVk = new WinDef.WORD(0);
            inputs[1].input.ki.dwFlags = new WinDef.DWORD(WinUser.KEYBDINPUT.KEYEVENTF_KEYUP | WinUser.KEYBDINPUT.KEYEVENTF_UNICODE);
            User32.INSTANCE.SendInput(new WinDef.DWORD(2), inputs, inputs[0].size());
        }

    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 101; i++) {
            sendString("//aaa乌 漆@麻!^&黑!abcd" + i);
            keyPressed(13);
            keyReleased(13);
        }

    }
}
