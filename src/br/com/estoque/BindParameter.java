package br.com.estoque;

import java.sql.Date;

public class BindParameter {
    public static final int TYPE_STRING = 1;
    public static final int TYPE_DOUBLE = 2;
    public static final int TYPE_DATE = 3;
    public static final int TYPE_INT = 4;

    public String stringValue;
    public Double doubleValue;
    public Date dateValue;
    public int intValue;
    public int type;

    public BindParameter(String value) {
        this.type = BindParameter.TYPE_STRING;
        this.stringValue = value;
    }

    public BindParameter(int value) {
        this.type = BindParameter.TYPE_INT;
        this.intValue = value;
    }

    public BindParameter(Date value) {
        this.type = BindParameter.TYPE_DATE;
        this.dateValue = value;
    }

    public BindParameter(Double value) {
        this.type = BindParameter.TYPE_DOUBLE;
        this.doubleValue = value;
    }
}
