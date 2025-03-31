package com.example.twitter.repository;

import com.example.twitter.entity.Client;
import com.example.twitter.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    // Custom query to delete a client by ID using @Modifying and @Transactional annotations if needed
    @Query(value = "DELETE FROM clients WHERE id=?1", nativeQuery = true)
    void deleteClient(Long id);
    Client findByUser_Id(Long userId);

    @Query("SELECT c.followersClient FROM Client c WHERE c.id = ?1")
    List<Client> findFollowersByClientId(Long clientId);

    @Query("SELECT c.followingAccounts FROM Client c WHERE c.id = ?1")
    List<Client> findFollowingByClientId(Long clientId);

}
