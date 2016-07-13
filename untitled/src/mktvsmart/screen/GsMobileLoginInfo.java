package mktvsmart.screen;

public class GsMobileLoginInfo {
    final private static int G_MS_BROADCAST_INFO_SN_LEN = 8;
    final private static int G_MS_MAX_CHIP_LEN = 8;
    final private static int G_MS_MAX_MODEL_STRING_LENGTH = 32;
    final private static int G_MS_RESERVED1_LEN = 3;
    final private static int G_MS_RESERVED3_LEN = 20;
    private int client_type;
    private int is_current_stb_connected_full;
    private int mConnectStatus;
    private int mIpLoginMark;
    private long mLastFoundTime;
    private int mSat2ipEnable;
    private int mSatEnable;
    private String magic_code;
    private String model_name;
    private int platform_id;
    private byte[] reserved_1;
    private byte[] reserved_3;
    private byte[] reserver_2;
    private int send_data_type;
    private byte[] stb_cpu_chip_id;
    private int stb_customer_id;
    private byte[] stb_flash_id;
    private int stb_ip_address;
    private String stb_ip_address_disp;
    private int stb_model_id;
    private byte[] stb_sn;
    private String stb_sn_disp;
    private int sw_sub_version;
    private int sw_version;
    private String upnpIp;
    private int upnpPort;

    public GsMobileLoginInfo()
    {
        super();
        this.mLastFoundTime = 0L;
        this.upnpPort = 20000;
    }

    public GsMobileLoginInfo(byte[] a)
    {
        super();
        this.mLastFoundTime = 0L;
        this.stb_sn = new byte[8];
        this.stb_cpu_chip_id = new byte[8];
        this.stb_flash_id = new byte[8];
        this.reserved_1 = new byte[3];
        this.reserved_3 = new byte[20];
        this.magic_code = new String(a, 0, 12);
        System.arraycopy((Object)a, 12, (Object)this.stb_sn, 0, 8);
        this.stb_sn_disp = this.SerialNumberToDisp(this.stb_sn);
        int i = 0;
        while(true)
        {
            if (i < 32)
            {
                int i0 = a[i + 20];
                if (i0 != 0)
                {
                    i = i + 1;
                    continue;
                }
            }
            this.model_name = new String(a, 20, i);
            int i1 = 20 + 32;
            System.arraycopy((Object)a, i1, (Object)this.stb_cpu_chip_id, 0, 8);
            int i2 = i1 + 8;
            System.arraycopy((Object)a, i2, (Object)this.stb_flash_id, 0, 8);
            int i3 = i2 + 8;
            int i4 = a[71];
            StringBuilder a0 = new StringBuilder(String.valueOf(i4 & 255)).append(".");
            int i5 = a[70];
            StringBuilder a1 = a0.append(i5 & 255).append(".");
            int i6 = a[69];
            StringBuilder a2 = a1.append(i6 & 255).append(".");
            int i7 = a[i3];
            this.stb_ip_address_disp = a2.append(i7 & 255).toString();
            int i8 = i3 + 4;
            int i9 = i8 + 1;
            int i10 = a[i8];
            this.platform_id = i10;
            int i11 = i9 + 1;
            int i12 = a[i9];
            int i13 = i11 + 1;
            int i14 = (i12 & 255) << 8;
            int i15 = a[i11];
            this.sw_version = i14 | i15 & 255;
            int i16 = i13 + 1;
            int i17 = a[i13];
            this.stb_customer_id = i17 & 255;
            int i18 = i16 + 1;
            int i19 = a[i16];
            this.stb_model_id = i19;
            System.arraycopy((Object)a, i18, (Object)this.reserved_1, 0, 3);
            int i20 = i18 + 3;
            int i21 = a[83];
            int i22 = (i21 & 255) << 24 & -16777216;
            int i23 = a[82];
            int i24 = i22 | (i23 & 255) << 16 & 16711680;
            int i25 = a[81];
            int i26 = i24 | (i25 & 255) << 8 & 65280;
            int i27 = a[i20];
            this.sw_sub_version = i26 | i27 & 255;
            int i28 = i20 + 4;
            int i29 = a[i28];
            this.is_current_stb_connected_full = i29 & 1;
            int i30 = a[i28];
            this.client_type = (i30 & 2) >> 1;
            int i31 = a[i28];
            this.mSatEnable = (i31 & 4) >> 2;
            int i32 = a[i28];
            this.mSat2ipEnable = (i32 & 24) >> 3;
            int i33 = a[i28];
            this.send_data_type = (i33 & 64) >> 6;
            System.arraycopy((Object)a, i28 + 4, (Object)this.reserved_3, 0, 20);
            return;
        }
    }

    private String SerialNumberToDisp(byte[] a)
    {
        if (a == null)
        {
            return "";
        }
        Object[] a0 = new Object[2];
        int i = a[2];
        int i0 = i & 255;
        int i1 = a[1];
        int i2 = i0 | (i1 & 255) << 8 & 65280;
        int i3 = a[0];
        a0[0] = Integer.valueOf((i2 | (i3 & 255) << 16 & 16711680) & 16777215);
        int i4 = a[5];
        int i5 = i4 & 255;
        int i6 = a[4];
        int i7 = i5 | (i6 & 255) << 8 & 65280;
        int i8 = a[3];
        a0[1] = Integer.valueOf((i7 | (i8 & 255) << 16 & 16711680) & 16777215);
        return String.format("%06d%06d", a0);
    }

    public int getClient_type()
    {
        return this.client_type;
    }

    public int getIs_current_stb_connected_full()
    {
        return this.is_current_stb_connected_full;
    }

    public long getLastFoundTime()
    {
        return this.mLastFoundTime;
    }

    public String getMagic_code()
    {
        return this.magic_code;
    }

    public String getModel_name()
    {
        return this.model_name;
    }

    public int getPlatform_id()
    {
        return this.platform_id;
    }

    public byte[] getReserved_1()
    {
        return this.reserved_1;
    }

    public byte[] getReserved_3()
    {
        return this.reserved_3;
    }

    public int getSend_data_type()
    {
        return this.send_data_type;
    }

    public byte[] getStb_cpu_chip_id()
    {
        return this.stb_cpu_chip_id;
    }

    public int getStb_customer_id()
    {
        return this.stb_customer_id;
    }

    public byte[] getStb_flash_id()
    {
        return this.stb_flash_id;
    }

    public int getStb_ip_address()
    {
        return this.stb_ip_address;
    }

    public String getStb_ip_address_disp()
    {
        return this.stb_ip_address_disp;
    }

    public int getStb_model_id()
    {
        return this.stb_model_id;
    }

    public byte[] getStb_sn()
    {
        return this.stb_sn;
    }

    public String getStb_sn_disp()
    {
        return this.stb_sn_disp;
    }

    public int getSw_sub_version()
    {
        return this.sw_sub_version;
    }

    public int getSw_version()
    {
        return this.sw_version;
    }

    public String getUpnpIp()
    {
        return this.upnpIp;
    }

    public int getUpnpPort()
    {
        return this.upnpPort;
    }

    public int getmConnectStatus()
    {
        return this.mConnectStatus;
    }

    public int getmIpLoginMark()
    {
        return this.mIpLoginMark;
    }

    public int getmSat2ipEnable()
    {
        return this.mSat2ipEnable;
    }

    public int getmSatEnable()
    {
        return this.mSatEnable;
    }

    public void setClient_type(int i)
    {
        this.client_type = i;
    }

    public void setIs_current_stb_connected_full(int i)
    {
        this.is_current_stb_connected_full = i;
    }

    public void setLastFoundTime(long j)
    {
        this.mLastFoundTime = j;
    }

    public void setMagic_code(String s)
    {
        this.magic_code = s;
    }

    public void setModel_name(String s)
    {
        this.model_name = s;
    }

    public void setPlatform_id(int i)
    {
        this.platform_id = i;
    }

    public void setReserved_1(byte[] a)
    {
        this.reserved_1 = a;
    }

    public void setReserved_3(byte[] a)
    {
        this.reserved_3 = a;
    }

    public void setSend_data_type(int i)
    {
        this.send_data_type = i;
    }

    public void setStb_cpu_chip_id(byte[] a)
    {
        this.stb_cpu_chip_id = a;
    }

    public void setStb_customer_id(int i)
    {
        this.stb_customer_id = i;
    }

    public void setStb_flash_id(byte[] a)
    {
        this.stb_flash_id = a;
    }

    public void setStb_ip_address(int i)
    {
        this.stb_ip_address = i;
    }

    public void setStb_ip_address_disp(String s)
    {
        this.stb_ip_address_disp = s;
    }

    public void setStb_model_id(int i)
    {
        this.stb_model_id = i;
    }

    public void setStb_sn(byte[] a)
    {
        this.stb_sn = a;
    }

    public void setStb_sn_disp(String s)
    {
        this.stb_sn_disp = s;
    }

    public void setSw_sub_version(int i)
    {
        this.sw_sub_version = i;
    }

    public void setSw_version(int i)
    {
        this.sw_version = i;
    }

    public void setUpnpIp(String s)
    {
        this.upnpIp = s;
    }

    public void setUpnpPort(int i)
    {
        this.upnpPort = i;
    }

    public void setmConnectStatus(int i)
    {
        this.mConnectStatus = i;
    }

    public void setmIpLoginMark(int i)
    {
        this.mIpLoginMark = i;
    }

    public void setmSat2ipEnable(int i)
    {
        this.mSat2ipEnable = i;
    }

    public void setmSatEnable(int i)
    {
        this.mSatEnable = i;
    }
}
