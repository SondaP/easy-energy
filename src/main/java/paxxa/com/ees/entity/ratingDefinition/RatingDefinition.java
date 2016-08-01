package paxxa.com.ees.entity.ratingDefinition;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class RatingDefinition {

    @Id
    @GeneratedValue
    private Integer id;

    private Date timestamp;
    private Date issueDate;

    @Enumerated(EnumType.STRING)
    private Voivodeship voivodeship;

    @OneToMany
    @JoinColumn(name = "FK_VARIABLE_DECLARATION")
    private List<VariableDeclarations> variableDeclarationsList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Voivodeship getVoivodeship() {
        return voivodeship;
    }

    public void setVoivodeship(Voivodeship voivodeship) {
        this.voivodeship = voivodeship;
    }

    public List<VariableDeclarations> getVariableDeclarationsList() {
        return variableDeclarationsList;
    }

    public void setVariableDeclarationsList(List<VariableDeclarations> variableDeclarationsList) {
        this.variableDeclarationsList = variableDeclarationsList;
    }
}
