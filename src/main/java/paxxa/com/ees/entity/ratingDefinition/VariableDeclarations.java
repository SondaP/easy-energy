package paxxa.com.ees.entity.ratingDefinition;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class VariableDeclarations {

    @Id
    @GeneratedValue
    private Integer id;

    private String variableIdentification;
    private BigDecimal variableValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVariableIdentification() {
        return variableIdentification;
    }

    public void setVariableIdentification(String variableIdentification) {
        this.variableIdentification = variableIdentification;
    }

    public BigDecimal getVariableValue() {
        return variableValue;
    }

    public void setVariableValue(BigDecimal variableValue) {
        this.variableValue = variableValue;
    }
}
