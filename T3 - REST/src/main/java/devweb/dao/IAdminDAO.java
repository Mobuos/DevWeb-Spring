package devweb.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import devweb.domain.Admin;

@SuppressWarnings("unchecked")
@Repository
public interface IAdminDAO extends CrudRepository<Admin, Long>{
    Admin save(Admin admin);
}
