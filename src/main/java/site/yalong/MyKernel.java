package site.yalong;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.*;

import static com.sun.jna.platform.win32.WinNT.PROCESS_ALL_ACCESS;

/**
 * @author yaLong
 */
public class MyKernel {
    public static final Kernel32 KERNEL = Kernel32.INSTANCE;

    public static void getError() {
        throw new Win32Exception(KERNEL.GetLastError());
    }

    /**
     * 读取指针的地址
     *
     * @param pointer 要读取的内存数据的指针
     * @return 10进制表示的地址
     */
    public static int getPointerAddress(Pointer pointer) {
        return (int) Pointer.nativeValue(pointer);
    }

    /**
     * @param pid 进程id
     * @return 返回进程句柄
     */
    public static WinNT.HANDLE getProcessHandle(int pid) {
        WinNT.HANDLE processHandle = KERNEL.OpenProcess(PROCESS_ALL_ACCESS, false, pid);
        if (processHandle == null) {
            getError();
        }
        return processHandle;
    }

    /**
     * @param processHandle 进程句柄
     * @param lpBaseAddress 内存地址
     * @param bytes         要写入的内存数据    字节数组
     */
    public static void writeMemory(WinNT.HANDLE processHandle, int lpBaseAddress, byte[] bytes) {
        //要写入的内存地址
        Pointer lpBaseAddressPointer = new Pointer(lpBaseAddress);
        //要写入的内存数据
        Pointer buf = new Memory(bytes.length);
        buf.write(0, bytes, 0, bytes.length);
        //写入内存
        boolean result = KERNEL.WriteProcessMemory(processHandle, lpBaseAddressPointer, buf, bytes.length, null);
        if (!result) {
            getError();
        }
    }

    /**
     * @param processHandle 进程句柄
     * @param bytes         要写入的内存数据    字节数组
     * @return 返回写入的内存地址指针
     */
    public static Pointer writeMemory(WinNT.HANDLE processHandle, byte[] bytes) {
        int codeLength = bytes.length;
        Pointer virtualAllocEx = KERNEL.VirtualAllocEx(processHandle, null, new BaseTSD.SIZE_T(codeLength), WinNT.MEM_COMMIT, WinNT.PAGE_EXECUTE_READWRITE);
        if (virtualAllocEx == null) {
            getError();
        }
        Pointer buf = new Memory(codeLength);
        buf.write(0, bytes, 0, codeLength);
        boolean writeMemory = KERNEL.WriteProcessMemory(processHandle, virtualAllocEx, buf, codeLength, null);
        if (!writeMemory) {
            getError();
        }
        return virtualAllocEx;

    }

    /**
     * @param processHandle 进程句柄
     * @param lpBaseAddress 内存地址
     * @param size          要读取的内存字节数
     * @return 返回读取的内存数据的指针, 通过pointer.getString(0, " utf - 8 ")获取字符串数据
     */
    public static Pointer readMemory(WinNT.HANDLE processHandle, int lpBaseAddress, int size) {
        //要读取的内存地址的指针
        Pointer lpBaseAddressPointer = new Pointer(lpBaseAddress);
        //指针,存储读取到的内存数据到java内存,必须用Memory,不能用Pointer
        Pointer buf = new Memory(size);
        boolean result = KERNEL.ReadProcessMemory(processHandle, lpBaseAddressPointer, buf, size, null);
        if (!result) {
            getError();
        }
        return buf;
    }

    /**
     * @param exeName 要查找的进程名称
     * @return 返回进程pid
     */
    public static int findPidByName(String exeName) {
        WinNT.HANDLE handle = KERNEL.CreateToolhelp32Snapshot(Tlhelp32.TH32CS_SNAPPROCESS, Tlhelp32.TH32CS_SNAPALL);
        Tlhelp32.PROCESSENTRY32 p = new Tlhelp32.PROCESSENTRY32();
        int pid = 0;
        while (KERNEL.Process32Next(handle, p)) {
            String s = new String(p.szExeFile);
            if (s.contains(exeName)) {
                pid = p.th32ProcessID.intValue();
                break;
            }
        }
        Kernel32Util.closeHandle(handle);
        if (pid == 0) {
            getError();
        }
        return pid;
    }

    /**
     * @param processHandle 进程句柄
     * @param shellCode     要执行的shellCode
     */
    public static void doInject(WinNT.HANDLE processHandle, byte[] shellCode) {
        Pointer virtualAllocEx = writeMemory(processHandle, shellCode);
        WinNT.HANDLE remoteHandle = KERNEL.CreateRemoteThread(processHandle, null, 0, virtualAllocEx, null, 0, null);
        if (remoteHandle == null) {
            getError();
        }
        KERNEL.WaitForSingleObject(remoteHandle, 5000);
        Kernel32Util.closeHandle(remoteHandle);
        KERNEL.VirtualFreeEx(processHandle, virtualAllocEx, new BaseTSD.SIZE_T(0), WinNT.MEM_RELEASE);
    }

}
