package com.example.hjw_front.vo;


import androidx.annotation.Nullable;

public class SosContractVO {
    private String id;
    private String uid;
    private String contract;
    private String name;

    public SosContractVO() {
    }

    public SosContractVO(String id, String uid, String contract, String name) {
        this.id = id;
        this.uid = uid;
        this.contract = contract;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        if(obj instanceof SosContractVO) {
            SosContractVO sosContractVO = (SosContractVO) obj;
            return this.contract.equals(sosContractVO.contract) && this.name.equals(sosContractVO.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31 * contract.length() + 17 * name.length();
    }

    @Override
    public String toString() {
        return "SosContractVO{" +
                "id='" + id + '\'' +
                ", uid='" + uid + '\'' +
                ", contract='" + contract + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
