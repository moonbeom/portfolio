package portfolioDev.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import portfolioDev.portfolio.domain.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
