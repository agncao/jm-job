package com.jm.job.core.common.enums;


/**
 * 布尔值
 */
public enum Bool {
    No(0),
    Yes(1),
    ;
    int val;

    Bool(int val) {
        this.val = val;
    }

    public int val() {
        return val;
    }

    public  static Bool valueOf(int val) {
        switch (val) {
            case 0:
                return No;
            default:
                return Yes;
        }
    }

}
