package gateway.com.example.user.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import gateway.com.example.user.demo.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
