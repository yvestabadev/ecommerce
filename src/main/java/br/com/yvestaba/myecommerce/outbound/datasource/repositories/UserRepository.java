package br.com.yvestaba.myecommerce.outbound.datasource.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.yvestaba.myecommerce.outbound.jpa.UserJpa;

@Repository
public interface UserRepository extends JpaRepository<UserJpa, String> {

}
