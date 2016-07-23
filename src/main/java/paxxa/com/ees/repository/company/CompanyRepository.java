package paxxa.com.ees.repository.company;

import org.springframework.data.jpa.repository.JpaRepository;
import paxxa.com.ees.entity.company.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
