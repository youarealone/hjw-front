package com.example.hjw_front;

import android.support.annotation.Nullable;

class SosContract {
    private String uid;
    private String contract;
    private String name;

    public SosContract() {
    }

    public SosContract(String uid, String contract, String name) {
        this.uid = uid;
        this.contract = contract;
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj instanceof SosContract) {
            SosContract sosContract = (SosContract) obj;
            return this.contract.equals(sosContract.contract) && this.name.equals(sosContract.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31 * contract.length() + 17 * name.length();
    }
}
