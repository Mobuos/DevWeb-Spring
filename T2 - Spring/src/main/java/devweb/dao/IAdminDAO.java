package devweb.dao;

import org.springframework.data.repository.CrudRepository;

import devweb.domain.Admin;

@SuppressWarnings("unchecked")
public interface IAdminDAO extends CrudRepository<Admin, Long>{
    Admin save(Admin admin);
}
