package com.example.hjw_front;

import android.support.annotation.Nullable;

class Member {
    private String sos_num;
    private String sos_name;

    public Member(String num, String name) {
        sos_num = num;
        sos_name = name;
    }

    public String getSos_num() {
        return sos_num;
    }

    public void setSos_num(String sos_num) {
        this.sos_num = sos_num;
    }

    public String getSos_name() {
        return sos_name;
    }

    public void setSos_name(String sos_name) {
        this.sos_name = sos_name;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj instanceof Member) {
            Member member = (Member) obj;
            return this.sos_num.equals(member.sos_num) && this.sos_name.equals(member.sos_name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31 * sos_num.length() + 17 * sos_name.length();
    }
}
