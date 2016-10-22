package paxxa.com.ees.entity.client;

import paxxa.com.ees.entity.company.Company;
import paxxa.com.ees.entity.user.User;

import javax.persistence.*;

@Entity
@Table(name = "CLIENT")
public class Client {

    @Id
    @GeneratedValue
    private Integer id;
    @OneToOne
    private User currentAdvisor;
    @OneToOne
    @JoinColumn
    private Company company;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getCurrentAdvisor() {
        return currentAdvisor;
    }

    public void setCurrentAdvisor(User currentAdvisor) {
        this.currentAdvisor = currentAdvisor;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
