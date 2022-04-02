package br.com.estoque;

import java.sql.PreparedStatement;
import java.sql.Date;
import br.com.estoque.BindParameter;

public class BindResolver {

    public BindResolver(PreparedStatement pstm, BindParameter... parameters) throws Exception {
        int paramId = 1;

        for (BindParameter param : parameters) {
            switch (param.type) {
                case BindParameter.TYPE_STRING: {
                    pstm.setString(paramId, param.stringValue);
                    break;
                }
                case BindParameter.TYPE_INT: {
                    pstm.setInt(paramId, param.intValue);
                    break;
                }
                case BindParameter.TYPE_DOUBLE: {
                    pstm.setDouble(paramId, param.doubleValue);
                    break;
                }
                case BindParameter.TYPE_DATE: {
                    pstm.setDate(paramId, param.dateValue);
                    break;
                }
            }

            paramId++;
        }
    }
}
