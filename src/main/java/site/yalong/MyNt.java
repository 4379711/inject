package site.yalong;

import com.sun.jna.Library;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinNT;

/**
 * 针对64位进程,需要用nt进行内存操作
 *
 * @author YaLong
 */
public interface MyNt extends Library {
    MyNt INSTANCE = Native.load("ntdll", MyNt.class);

    /**
     * 提升进程权限
     *
     * @param privilege     具体权限
     * @param enable        如果为True 就是打开相应权限，如果为False 则是关闭相应权限
     * @param currentThread 如果为True 则仅提升当前线程权限，否则提升整个进程的权限
     * @param tkpPrevious   输出原来相应权限的状态（打开 | 关闭）
     *                      权限名和对应值如下
     *                      1.SeCreateTokenPrivilege 0x2
     *                      2.SeAssignPrimaryTokenPrivilege 0x3
     *                      3.SeLockMemoryPrivilege 0x4
     *                      4.SeIncreaseQuotaPrivilege 0x5
     *                      5.SeUnsolicitedInputPrivilege 0x0
     *                      6.SeMachineAccountPrivilege 0x6
     *                      7.SeTcbPrivilege 0x7
     *                      8.SeSecurityPrivilege 0x8
     *                      9.SeTakeOwnershipPrivilege 0x9
     *                      10.SeLoadDriverPrivilege 0xa
     *                      11.SeSystemProfilePrivilege 0xb
     *                      12.SeSystemtimePrivilege 0xc
     *                      13.SeProfileSingleProcessPrivilege 0xd
     *                      14.SeIncreaseBasePriorityPrivilege 0xe
     *                      15.SeCreatePagefilePrivilege 0xf
     *                      16.SeCreatePermanentPrivilege 0x10
     *                      17.SeBackupPrivilege 0x11
     *                      18.SeRestorePrivilege 0x12
     *                      19.SeShutdownPrivilege 0x13
     *                      20.SeDebugPrivilege 0x14
     *                      21.SeAuditPrivilege 0x15
     *                      22.SeSystemEnvironmentPrivilege 0x16
     *                      23.SeChangeNotifyPrivilege 0x17
     *                      24.SeRemoteShutdownPrivilege 0x18
     *                      25.SeUndockPrivilege 0x19
     *                      26.SeSyncAgentPrivilege 0x1a
     *                      27.SeEnableDelegationPrivilege 0x1b
     *                      28.SeManageVolumePrivilege 0x1c
     *                      29.SeImpersonatePrivilege 0x1d
     *                      30.SeCreateGlobalPrivilege 0x1e
     *                      31.SeTrustedCredManAccessPrivilege 0x1f
     *                      32.SeRelabelPrivilege 0x20
     *                      33.SeIncreaseWorkingSetPrivilege 0x21
     *                      34.SeTimeZonePrivilege 0x22
     *                      35.SeCreateSymbolicLinkPrivilege 0x23
     */
    int RtlAdjustPrivilege(long privilege, boolean enable, boolean currentThread, Pointer tkpPrevious);

    /**
     * 提升debug权限
     */
    default void debugPrivilege() {
        Pointer tkpPrevious = new Memory(4);
        RtlAdjustPrivilege(0x14, true, false, tkpPrevious);
    }

    /**
     * @param processHandle     进程句柄
     * @param baseAddress       内存地址
     * @param buffer            内存地址
     * @param size              读取的字节数
     * @param numberOfBytesRead 可选参数，写0即可
     */
    int ZwWow64ReadVirtualMemory64(WinNT.HANDLE processHandle, long baseAddress, Pointer buffer, long size, long numberOfBytesRead);

    int ZwWow64WriteVirtualMemory64(WinNT.HANDLE processHandle, long baseAddress, Pointer buffer, long size, long numberOfBytesRead);

    int NtWow64ReadVirtualMemory64(WinNT.HANDLE processHandle, long baseAddress, Pointer buffer, long size, long numberOfBytesRead);

    int NtWow64WriteVirtualMemory64(WinNT.HANDLE processHandle, long baseAddress, Pointer buffer, long size, long numberOfBytesRead);

    int NtWow64QueryInformationProcess64(WinNT.HANDLE processHandle, int processInformationClass, Pointer processInformation, int processInformationLength, Pointer returnLength);

}
