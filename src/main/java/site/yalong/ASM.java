package site.yalong;

/**
 * 汇编码转机器码
 *
 * @author yaLong
 */
public class ASM {
    private final transient StringBuffer _ASM_CODE = new StringBuffer();

    public byte[] getHexByteASMCode() {
        String hexString = _ASM_CODE.toString();
        //转换16进制byte字符串为byte[]
        int len = hexString.length() >> 1;
        byte[] buffer = new byte[len];
        for (int i = 0; i < len * 2; i++) {
            int shift = i % 2 == 1 ? 0 : 4;
            buffer[i >> 1] |= Character.digit((char) hexString.getBytes()[i], 16) << shift;
        }
        return buffer;
    }

    public void clearASMCode() {
        _ASM_CODE.delete(0, _ASM_CODE.length());
    }


    /**
     * 将指定整形数值转为16进制
     */
    static public String getHexToString(int value) {
        String hexString = Integer.toHexString(value & 0xff);
        return hexString.length() < 2 ? ("0" + hexString).intern() : hexString;
    }

    /**
     * 获得指定位数的16进制数据
     */
    static private String getIntToHex(int value, int number) {
        int size = number > 0 ? number / 2 : 0;
        StringBuilder buffer = new StringBuilder();
        try {
            for (int i = 0; i < size; i++) {
                buffer.append(ASM.getHexToString((value >> (i * 8)) & 0xff));
            }
            return (buffer.toString()).toUpperCase();
        } catch (Exception ex) {
            return "";
        }
    }

    public void ADD_EAX(int address) {
        _ASM_CODE.append("05");
        _ASM_CODE.append(ASM.getIntToHex(address, 8));
    }

    public void ADD_EAX_DWORD_PTR(int address) {
        _ASM_CODE.append("0305");
        _ASM_CODE.append(ASM.getIntToHex(address, 8));
    }

    public void ADD_EAX_EDX() {
        _ASM_CODE.append("03C2");
    }

    public void ADD_EBP_DWORD_PTR(int address) {
        _ASM_CODE.append("032D");
        _ASM_CODE.append(ASM.getIntToHex(address, 8));
    }

    public void ADD_EBX(int address) {
        _ASM_CODE.append("83C3");
        _ASM_CODE.append(ASM.getIntToHex(address, 8));
    }

    public void ADD_EBX_DWORD_PTR(int address) {
        _ASM_CODE.append("031D");
        _ASM_CODE.append(ASM.getIntToHex(address, 8));
    }

    public void ADD_EBX_EAX() {
        _ASM_CODE.append("03D8");
    }

    public void ADD_ECX(int address) {
        _ASM_CODE.append("83C1");
        _ASM_CODE.append(ASM.getIntToHex(address, 8));
    }

    public void ADD_EDX(int address) {
        _ASM_CODE.append("83C2");
        _ASM_CODE.append(ASM.getIntToHex(address, 8));
    }

    public void ADD_ESI(int address) {
        _ASM_CODE.append("83C6");
        _ASM_CODE.append(ASM.getIntToHex(address, 8));
    }

    public void ADD_ESP(int address) {
        _ASM_CODE.append("83C4");
        _ASM_CODE.append(ASM.getIntToHex(address, 8));
    }

    public void SBB_EAX(int address) {
        _ASM_CODE.append("1D");
        _ASM_CODE.append(ASM.getIntToHex(address, 8));
    }

    public void SBB_EAX_EAX() {
        _ASM_CODE.append("1BC0");
    }

    public void SBB_EBX_EBX() {
        _ASM_CODE.append("1BDB");
    }

    public void CWD() {
        _ASM_CODE.append("99");
    }

    public void CALL_DWORD_PTR(int address) {
        _ASM_CODE.append("FF15");
        _ASM_CODE.append(ASM.getIntToHex(address, 8));
    }

    public void CALL_DWORD_PTR_EAX() {
        _ASM_CODE.append("FF10");
    }

    public void CALL_DWORD_PTR_EBX() {
        _ASM_CODE.append("FF13");
    }

    public void CALL_EAX() {
        _ASM_CODE.append("FFD0");
    }

    public void CALL_EBP() {
        _ASM_CODE.append("FFD5");
    }

    public void CALL_EBX() {
        _ASM_CODE.append("FFD3");
    }

    public void CALL_ECX() {
        _ASM_CODE.append("FFD1");
    }

    public void CALL_EDI() {
        _ASM_CODE.append("FFD7");
    }

    public void CALL_EDX() {
        _ASM_CODE.append("FFD2");
    }

    public void CALL_ESI() {
        _ASM_CODE.append("FFD2");
    }

    public void CALL_ESP() {
        _ASM_CODE.append("FFD4");
    }

    public void CMP_DWORD_PTR_EAX(int address) {
        _ASM_CODE.append("3905");
        _ASM_CODE.append(ASM.getIntToHex(address, 8));
    }

    public void CMP_EAX(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("83F8");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("3D");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void CMP_EAX_DWORD_PTR(int address) {
        _ASM_CODE.append("3B05");
        _ASM_CODE.append(ASM.getIntToHex(address, 8));
    }

    public void CMP_EAX_EDX() {
        _ASM_CODE.append("3BC2");
    }

    public void DEC_EAX() {
        _ASM_CODE.append("48");
    }

    public void DEC_EBX() {
        _ASM_CODE.append("4B");
    }

    public void DEC_ECX() {
        _ASM_CODE.append("49");
    }

    public void DEC_EDX() {
        _ASM_CODE.append("4A");
    }

    public void EM() {
        _ASM_CODE.append("FFFF");
    }

    public void XOR_EAX_EAX() {
        _ASM_CODE.append("33C0");
    }

    public void XOR_EDX_EDX() {
        _ASM_CODE.append("33D2");
    }

    public void IDIV_EAX() {
        _ASM_CODE.append("F7F8");
    }

    public void IDIV_EBX() {
        _ASM_CODE.append("F7FB");
    }

    public void IDIV_ECX() {
        _ASM_CODE.append("F7F9");
    }

    public void IDIV_EDX() {
        _ASM_CODE.append("F7FA");
    }

    public void IMUL_EAX(int address) {
        _ASM_CODE.append("6BC0");
        _ASM_CODE.append(ASM.getIntToHex(address, 2));
    }

    public void IMUL_EAX_EBX() {
        _ASM_CODE.append("0FAFC3");
    }

    public void IMUL_EAX_EDX() {
        _ASM_CODE.append("0FAFC2");
    }

    public void IMULB_EAX(int address) {
        _ASM_CODE.append("69C0");
        _ASM_CODE.append(ASM.getIntToHex(address, 8));
    }

    public void IN_AL_DX() {
        _ASM_CODE.append("EC");
    }

    public void INC_DWORD_PTR_EAX() {
        _ASM_CODE.append("FF00");
    }

    public void INC_DWORD_PTR_EBX() {
        _ASM_CODE.append("FF03");
    }

    public void INC_DWORD_PTR_ECX() {
        _ASM_CODE.append("FF01");
    }

    public void INC_DWORD_PTR_EDX() {
        _ASM_CODE.append("FF02");
    }

    public void INC_EAX() {
        _ASM_CODE.append("40");
    }

    public void INC_EBX() {
        _ASM_CODE.append("43");
    }

    public void INC_ECX() {
        _ASM_CODE.append("41");
    }

    public void INC_EDI() {
        _ASM_CODE.append("47");
    }

    public void INC_EDX() {
        _ASM_CODE.append("42");
    }

    public void INC_ESI() {
        _ASM_CODE.append("46");
    }

    public void JMP_EAX() {
        _ASM_CODE.append("FFE0");
    }

    public void LEA_EAX_DWORD_PTR_EAX_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8D40");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8D80");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void LEA_EAX_DWORD_PTR_EBP_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8D4424");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8D8424");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void LEA_EAX_DWORD_PTR_EBX_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8D43");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8D83");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void LEA_EAX_DWORD_PTR_ECX_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8D41");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8D81");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void LEA_EAX_DWORD_PTR_EDI_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8D47");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8D87");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void LEA_EAX_DWORD_PTR_EDX_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8D42");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8D82");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void LEA_EAX_DWORD_PTR_ESI_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8D46");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8D86");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void LEA_EAX_DWORD_PTR_ESP_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8D40");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8D80");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void LEA_EBX_DWORD_PTR_EAX_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8D58");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8D98");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void LEA_EBX_DWORD_PTR_EBP_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8D5D");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8D9D");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void LEA_EBX_DWORD_PTR_EBX_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8D5B");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8D9B");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void LEA_EBX_DWORD_PTR_ECX_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8D59");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8D99");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void LEA_EBX_DWORD_PTR_EDI_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8D5F");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8D9F");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void LEA_EBX_DWORD_PTR_EDX_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8D5A");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8D9A");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void LEA_EBX_DWORD_PTR_ESI_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8D5E");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8D9E");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void LEA_EBX_DWORD_PTR_ESP_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8D5C24");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8D9C24");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void LEA_ECX_DWORD_PTR_EAX_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8D48");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8D88");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void LEA_ECX_DWORD_PTR_EBP_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8D4D");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8D8D");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void LEA_ECX_DWORD_PTR_EBX_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8D4B");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8D8B");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void LEA_ECX_DWORD_PTR_ECX_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8D49");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8D89");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void LEA_ECX_DWORD_PTR_EDI_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8D4F");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8D8F");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void LEA_ECX_DWORD_PTR_EDX_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8D4A");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8D8A");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void LEA_ECX_DWORD_PTR_ESI_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8D4E");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8D8E");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void LEA_ECX_DWORD_PTR_ESP_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8D4C24");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8D8C24");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void LEA_EDX_DWORD_PTR_EAX_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8D50");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8D90");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void LEA_EDX_DWORD_PTR_EBP_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8D55");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8D95");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void LEA_EDX_DWORD_PTR_EBX_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8D53");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8D93");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void LEA_EDX_DWORD_PTR_ECX_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8D51");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8D91");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void LEA_EDX_DWORD_PTR_EDI_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8D57");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8D97");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void LEA_EDX_DWORD_PTR_EDX_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8D52");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8D92");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void LEA_EDX_DWORD_PTR_ESI_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8D56");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8D96");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void LEA_EDX_DWORD_PTR_ESP_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8D5424");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8D9424");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void LEAVE() {
        _ASM_CODE.append("C9");
    }

    public void MOV_DWORD_PTR_EAX(int address) {
        _ASM_CODE.append("A3");
        _ASM_CODE.append(ASM.getIntToHex(address, 8));
    }

    public void MOV_EAX(int address) {
        _ASM_CODE.append("B8");
        _ASM_CODE.append(ASM.getIntToHex(address, 8));
    }

    public void MOV_EAX_DWORD_PTR(int address) {
        _ASM_CODE.append("A1");
        _ASM_CODE.append(ASM.getIntToHex(address, 8));
    }

    public void MOV_EAX_DWORD_PTR_EAX() {
        _ASM_CODE.append("8B00");
    }

    public void MOV_EAX_DWORD_PTR_EAX_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8B40");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8B80");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void MOV_EAX_DWORD_PTR_EBP() {
        _ASM_CODE.append("8B4500");
    }

    public void MOV_EAX_DWORD_PTR_EBP_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8B45");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8B85");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void MOV_EAX_DWORD_PTR_EBX() {
        _ASM_CODE.append("8B03");
    }

    public void MOV_EAX_DWORD_PTR_EBX_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8B43");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8B83");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void MOV_EAX_DWORD_PTR_ECX() {
        _ASM_CODE.append("8B01");
    }

    public void MOV_EAX_DWORD_PTR_ECX_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8B41");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8B81");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void MOV_EAX_DWORD_PTR_EDI() {
        _ASM_CODE.append("8B07");
    }

    public void MOV_EAX_DWORD_PTR_EDI_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8B47");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8B87");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void MOV_EAX_DWORD_PTR_EDX() {
        _ASM_CODE.append("8B02");
    }

    public void MOV_EAX_DWORD_PTR_EDX_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8B42");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8B82");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void MOV_EAX_DWORD_PTR_ESI() {
        _ASM_CODE.append("8B06");
    }

    public void MOV_EAX_DWORD_PTR_ESI_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8B46");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8B86");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void MOV_EAX_DWORD_PTR_ESP() {
        _ASM_CODE.append("8B0424");
    }

    public void MOV_EAX_DWORD_PTR_ESP_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8B4424");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8B8424");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void MOV_EAX_EBP() {
        _ASM_CODE.append("8BC5");
    }

    public void MOV_EAX_EBX() {
        _ASM_CODE.append("8BC3");
    }

    public void MOV_EAX_ECX() {
        _ASM_CODE.append("8BC1");
    }

    public void MOV_EAX_EDI() {
        _ASM_CODE.append("8BC7");
    }

    public void MOV_EAX_EDX() {
        _ASM_CODE.append("8BC2");
    }

    public void MOV_EAX_ESI() {
        _ASM_CODE.append("8BC6");
    }

    public void MOV_EAX_ESP() {
        _ASM_CODE.append("8BC4");
    }

    public void MOV_EBP(int address) {
        _ASM_CODE.append("BD");
        _ASM_CODE.append(ASM.getIntToHex(address, 8));
    }

    public void MOV_EBP_DWORD_PTR(int address) {
        _ASM_CODE.append("8B2D");
        _ASM_CODE.append(ASM.getIntToHex(address, 8));
    }

    public void MOV_EBP_EAX() {
        _ASM_CODE.append("8BE8");
    }

    public void MOV_EBP_EBX() {
        _ASM_CODE.append("8BEB");
    }

    public void MOV_EBP_ECX() {
        _ASM_CODE.append("8BE9");
    }

    public void MOV_EBP_EDI() {
        _ASM_CODE.append("8BDF");
    }

    public void MOV_EBP_EDX() {
        _ASM_CODE.append("8BEA");
    }

    public void MOV_EBP_ESI() {
        _ASM_CODE.append("8BEE");
    }

    public void MOV_EBP_ESP() {
        _ASM_CODE.append("8BEC");
    }

    public void MOV_EBX(int address) {
        _ASM_CODE.append("BB");
        _ASM_CODE.append(ASM.getIntToHex(address, 8));
    }

    public void MOV_EBX_DWORD_PTR(int address) {
        _ASM_CODE.append("8B1D");
        _ASM_CODE.append(ASM.getIntToHex(address, 8));
    }

    public void MOV_EBX_DWORD_PTR_EAX() {
        _ASM_CODE.append("8B18");
    }

    public void MOV_EBX_DWORD_PTR_EAX_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8B58");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8B98");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void MOV_EBX_DWORD_PTR_EBP() {
        _ASM_CODE.append("8B5D00");
    }

    public void MOV_EBX_DWORD_PTR_EBP_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8B5D");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8B9D");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void MOV_EBX_DWORD_PTR_EBX() {
        _ASM_CODE.append("8B1B");
    }

    public void MOV_EBX_DWORD_PTR_EBX_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8B5B");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8B9B");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void MOV_EBX_DWORD_PTR_ECX() {
        _ASM_CODE.append("8B19");
    }

    public void MOV_EBX_DWORD_PTR_ECX_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8B59");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8B99");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void MOV_EBX_DWORD_PTR_EDI() {
        _ASM_CODE.append("8B1F");
    }

    public void MOV_EBX_DWORD_PTR_EDI_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8B5F");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8B9F");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void MOV_EBX_DWORD_PTR_EDX() {
        _ASM_CODE.append("8B1A");
    }

    public void MOV_EBX_DWORD_PTR_EDX_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8B5A");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8B9A");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void MOV_EBX_DWORD_PTR_ESI() {
        _ASM_CODE.append("8B1E");
    }

    public void MOV_EBX_DWORD_PTR_ESI_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8B5E");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8B9E");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void MOV_EBX_DWORD_PTR_ESP() {
        _ASM_CODE.append("8B1C24");
    }

    public void MOV_EBX_DWORD_PTR_ESP_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8B5C24");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8B9C24");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void MOV_EBX_EAX() {
        _ASM_CODE.append("8BD8");
    }

    public void MOV_EBX_EBP() {
        _ASM_CODE.append("8BDD");
    }

    public void MOV_EBX_ECX() {
        _ASM_CODE.append("8BD9");
    }

    public void MOV_EBX_EDI() {
        _ASM_CODE.append("8BDF");
    }

    public void MOV_EBX_EDX() {
        _ASM_CODE.append("8BDA");
    }

    public void MOV_EBX_ESI() {
        _ASM_CODE.append("8BDE");
    }

    public void MOV_EBX_ESP() {
        _ASM_CODE.append("8BDC");
    }

    public void MOV_ECX(int address) {
        _ASM_CODE.append("B9");
        _ASM_CODE.append(ASM.getIntToHex(address, 8));
    }

    public void MOV_ECX_DWORD_PTR(int address) {
        _ASM_CODE.append("8B0D");
        _ASM_CODE.append(ASM.getIntToHex(address, 8));
    }

    public void MOV_ECX_DWORD_PTR_EAX() {
        _ASM_CODE.append("8B08");
    }

    public void MOV_ECX_DWORD_PTR_EAX_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8B48");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8B88");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void MOV_ECX_DWORD_PTR_EBP() {
        _ASM_CODE.append("8B4D00");
    }

    public void MOV_ECX_DWORD_PTR_EBP_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8B4D");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8B8D");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void MOV_ECX_DWORD_PTR_EBX() {
        _ASM_CODE.append("8B0B");
    }

    public void MOV_ECX_DWORD_PTR_EBX_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8B4B");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8B8B");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void MOV_ECX_DWORD_PTR_ECX() {
        _ASM_CODE.append("8B09");
    }

    public void MOV_ECX_DWORD_PTR_ECX_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8B49");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8B89");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void MOV_ECX_DWORD_PTR_EDI() {
        _ASM_CODE.append("8B0F");
    }

    public void MOV_ECX_DWORD_PTR_EDI_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8B4F");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8B8F");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void MOV_ECX_DWORD_PTR_EDX() {
        _ASM_CODE.append("8B0A");
    }

    public void MOV_ECX_DWORD_PTR_EDX_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8B4A");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8B8A");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void MOV_ECX_DWORD_PTR_ESI() {
        _ASM_CODE.append("8B0E");
    }

    public void MOV_ECX_DWORD_PTR_ESI_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8B4E");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8B8E");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void MOV_ECX_DWORD_PTR_ESP() {
        _ASM_CODE.append("8B0C24");
    }

    public void MOV_ECX_DWORD_PTR_ESP_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8B4C24");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8B8C24");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void MOV_ECX_EAX() {
        _ASM_CODE.append("8BC8");
    }

    public void MOV_ECX_EBP() {
        _ASM_CODE.append("8BCD");
    }

    public void MOV_ECX_EBX() {
        _ASM_CODE.append("8BCB");
    }

    public void MOV_ECX_EDI() {
        _ASM_CODE.append("8BCF");
    }

    public void MOV_ECX_EDX() {
        _ASM_CODE.append("8BCA");
    }

    public void MOV_ECX_ESI() {
        _ASM_CODE.append("8BCE");
    }

    public void MOV_ECX_ESP() {
        _ASM_CODE.append("8BCC");
    }

    public void MOV_EDI(int address) {
        _ASM_CODE.append("BF");
        _ASM_CODE.append(ASM.getIntToHex(address, 8));
    }

    public void MOV_EDI_EAX() {
        _ASM_CODE.append("8BF8");
    }

    public void MOV_EDI_EBP() {
        _ASM_CODE.append("8BFD");
    }

    public void MOV_EDI_EBX() {
        _ASM_CODE.append("8BFB");
    }

    public void MOV_EDI_ECX() {
        _ASM_CODE.append("8BF9");
    }

    public void MOV_EDI_EDX() {
        _ASM_CODE.append("8BFA");
    }

    public void MOV_EDI_ESI() {
        _ASM_CODE.append("8BFE");
    }

    public void MOV_EDI_ESP() {
        _ASM_CODE.append("8BFC");
    }

    public void MOV_EDX(int address) {
        _ASM_CODE.append("BA");
        _ASM_CODE.append(ASM.getIntToHex(address, 8));
    }

    public void MOV_EDX_DWORD_PTR(int address) {
        _ASM_CODE.append("8B15");
        _ASM_CODE.append(ASM.getIntToHex(address, 8));
    }

    public void MOV_EDX_DWORD_PTR_EAX() {
        _ASM_CODE.append("8B10");
    }

    public void MOV_EDX_DWORD_PTR_EAX_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8B50");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8B90");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void MOV_EDX_DWORD_PTR_EBP() {
        _ASM_CODE.append("8B5500");
    }

    public void MOV_EDX_DWORD_PTR_EBP_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8B55");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8B95");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void MOV_EDX_DWORD_PTR_EBX() {
        _ASM_CODE.append("8B13");
    }

    public void MOV_EDX_DWORD_PTR_EBX_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8B53");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8B93");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void MOV_EDX_DWORD_PTR_ECX() {
        _ASM_CODE.append("8B11");
    }

    public void MOV_EDX_DWORD_PTR_ECX_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8B51");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8B91");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void MOV_EDX_DWORD_PTR_EDI() {
        _ASM_CODE.append("8B17");
    }

    public void MOV_EDX_DWORD_PTR_EDI_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8B57");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8B97");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void MOV_EDX_DWORD_PTR_EDX() {
        _ASM_CODE.append("8B12");
    }

    public void MOV_EDX_DWORD_PTR_EDX_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8B52");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8B92");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void MOV_EDX_DWORD_PTR_ESI() {
        _ASM_CODE.append("8B16");
    }

    public void MOV_EDX_DWORD_PTR_ESI_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8B56");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8B96");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void MOV_EDX_DWORD_PTR_ESP() {
        _ASM_CODE.append("8B1424");
    }

    public void MOV_EDX_DWORD_PTR_ESP_ADD(int address) {
        if (address <= 0xff) {
            _ASM_CODE.append("8B5424");
            _ASM_CODE.append(ASM.getIntToHex(address, 2));
        } else {
            _ASM_CODE.append("8B9424");
            _ASM_CODE.append(ASM.getIntToHex(address, 8));
        }
    }

    public void MOV_EDX_EAX() {
        _ASM_CODE.append("8BD0");
    }

    public void MOV_EDX_EBP() {
        _ASM_CODE.append("8BD5");
    }

    public void MOV_EDX_EBX() {
        _ASM_CODE.append("8BD3");
    }

    public void MOV_EDX_ECX() {
        _ASM_CODE.append("8BD1");
    }

    public void MOV_EDX_EDI() {
        _ASM_CODE.append("8BD7");
    }

    public void MOV_EDX_ESI() {
        _ASM_CODE.append("8BD6");
    }

    public void MOV_EDX_ESP() {
        _ASM_CODE.append("8BD4");
    }

    public void MOV_ESI(int address) {
        _ASM_CODE.append("BE");
        _ASM_CODE.append(ASM.getIntToHex(address, 8));
    }

    public void MOV_ESI_DWORD_PTR(int address) {
        _ASM_CODE.append("8B35");
        _ASM_CODE.append(ASM.getIntToHex(address, 8));
    }

    public void MOV_ESI_EAX() {
        _ASM_CODE.append("8BF0");
    }

    public void MOV_ESI_EBP() {
        _ASM_CODE.append("8BF5");
    }

    public void MOV_ESI_EBX() {
        _ASM_CODE.append("8BF3");
    }

    public void MOV_ESI_ECX() {
        _ASM_CODE.append("8BF1");
    }

    public void MOV_ESI_EDI() {
        _ASM_CODE.append("8BF7");
    }

    public void MOV_ESI_EDX() {
        _ASM_CODE.append("8BF2");
    }

    public void MOV_ESI_ESP() {
        _ASM_CODE.append("8BF4");
    }

    public void MOV_ESP(int address) {
        _ASM_CODE.append("BC");
        _ASM_CODE.append(ASM.getIntToHex(address, 8));
    }

    public void MOV_ESP_DWORD_PTR(int address) {
        _ASM_CODE.append("8B25");
        _ASM_CODE.append(ASM.getIntToHex(address, 8));
    }

    public void MOV_ESP_EAX() {
        _ASM_CODE.append("8BE0");
    }

    public void MOV_ESP_EBP() {
        _ASM_CODE.append("8BE5");
    }

    public void MOV_ESP_EBX() {
        _ASM_CODE.append("8BE3");
    }

    public void MOV_ESP_ECX() {
        _ASM_CODE.append("8BE1");
    }

    public void MOV_ESP_EDI() {
        _ASM_CODE.append("8BE7");
    }

    public void MOV_ESP_EDX() {
        _ASM_CODE.append("8BE2");
    }

    public void MOV_ESP_ESI() {
        _ASM_CODE.append("8BE6");
    }

    public void NOP() {
        _ASM_CODE.append("90");
    }

    public void POP_EAX() {
        _ASM_CODE.append("58");
    }

    public void POP_EBP() {
        _ASM_CODE.append("5D");
    }

    public void POP_EBX() {
        _ASM_CODE.append("5B");
    }

    public void POP_ECX() {
        _ASM_CODE.append("59");
    }

    public void POP_EDI() {
        _ASM_CODE.append("5F");
    }

    public void POP_EDX() {
        _ASM_CODE.append("5A");
    }

    public void POP_ESI() {
        _ASM_CODE.append("5E");
    }

    public void POP_ESP() {
        _ASM_CODE.append("5C");
    }

    public void POPAD() {
        _ASM_CODE.append("61");
    }

    public void PUSH(int address) {
        _ASM_CODE.append("68");
        _ASM_CODE.append(ASM.getIntToHex(address, 8));
    }

    public void PUSH_DWORD_PTR(int address) {
        _ASM_CODE.append("FF35");
        _ASM_CODE.append(ASM.getIntToHex(address, 8));
    }

    public void PUSH_EAX() {
        _ASM_CODE.append("50");
    }

    public void PUSH_EBP() {
        _ASM_CODE.append("55");
    }

    public void PUSH_EBX() {
        _ASM_CODE.append("53");
    }

    public void PUSH_ECX() {
        _ASM_CODE.append("51");
    }

    public void PUSH_EDI() {
        _ASM_CODE.append("57");
    }

    public void PUSH_EDX() {
        _ASM_CODE.append("52");
    }

    public void PUSH_ESI() {
        _ASM_CODE.append("56");
    }

    public void PUSH_ESP() {
        _ASM_CODE.append("54");
    }

    public void PUSHAD() {
        _ASM_CODE.append("60");
    }

    public void RET() {
        _ASM_CODE.append("C3");
    }

    public void RET_4() {
        _ASM_CODE.append("C20400");
    }

    public void RET_10() {
        _ASM_CODE.append("C21000");
    }

    public void RET_ADDRESS(int address) {
        _ASM_CODE.append(ASM.getIntToHex(address, 4));
    }

}
