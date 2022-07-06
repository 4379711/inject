import com.sun.jna.Callback;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.User32Util;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MessageComponentTest {

    private static WinDef.HWND wnd;
    private static WinUser.COPYDATASTRUCT copyDataStruct;
    public static String JAVA_WINDOW_NAME = "test_send_window";

    private static final ExecutorService EXECUTOR_SERVICE =
            new ThreadPoolExecutor(1, 1, 10L, TimeUnit.MINUTES,
                    new ArrayBlockingQueue<>(1)
            );

    private class WindowProc implements Callback {
        public WinDef.LRESULT callback(WinDef.HWND hWnd, WinDef.UINT uMsg, WinDef.WPARAM wParam, WinDef.LPARAM lParam) {
            switch (uMsg.intValue()) {
                case WinUser.WM_DESTROY:
                    User32.INSTANCE.PostQuitMessage(0);
                    break;
                case WinUser.WM_COPYDATA:
                    copyDataStruct = new WinUser.COPYDATASTRUCT(new Pointer(lParam.longValue()));
                    Pointer pointer = copyDataStruct.lpData;
                    String message = pointer.getString(0);
                    System.out.println(message);
                    break;
                default:
                    System.out.println("其他类型" + uMsg);
                    break;
            }
            return User32.INSTANCE.DefWindowProc(hWnd, uMsg.intValue(), wParam, lParam);
        }
    }

    private void registerClass() {
        WinUser.WNDCLASSEX wndClassEx = new WinUser.WNDCLASSEX();
        wndClassEx.lpfnWndProc = new WindowProc();
        wndClassEx.cbClsExtra = 0;
        wndClassEx.cbWndExtra = 0;
        wndClassEx.style = 0;
        wndClassEx.lpszClassName = JAVA_WINDOW_NAME;
        wndClassEx.hInstance = null;
        wndClassEx.hCursor = null;
        wndClassEx.hIcon = null;
        wndClassEx.hbrBackground = null;
        wndClassEx.lpszMenuName = null;
        User32.INSTANCE.RegisterClassEx(wndClassEx);
    }

    private void createWindow() {
        wnd = User32Util.createWindowEx(0, JAVA_WINDOW_NAME, JAVA_WINDOW_NAME,
                WinUser.WS_OVERLAPPEDWINDOW, 100, 100, 500, 400, null, null, null, null);
        if (wnd == null) {
            return;
        }
        //   User32.INSTANCE.ShowWindow(wnd, WinUser.SW_SHOWNORMAL);
        User32.INSTANCE.UpdateWindow(wnd);

        WinUser.MSG msg = new WinUser.MSG();
        while (User32.INSTANCE.GetMessage(msg, wnd, 0, 0) != 0) {
            User32.INSTANCE.TranslateMessage(msg);
            User32.INSTANCE.DispatchMessage(msg);
        }
    }

    public void init() {
        try {
            EXECUTOR_SERVICE.submit(new Runnable() {
                @Override
                public void run() {
                    registerClass();
                    createWindow();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new MessageComponentTest().init();
    }
}