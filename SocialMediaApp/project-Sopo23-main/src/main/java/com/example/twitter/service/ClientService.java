package com.example.twitter.service;

import com.example.twitter.dto.ClientAuxDTO;
import com.example.twitter.dto.ClientDTO;
import com.example.twitter.entity.Client;
import com.example.twitter.mapper.ClientAuxMapper;
import com.example.twitter.mapper.ClientMapper;
import com.example.twitter.mapper.UserMapper;
import com.example.twitter.repository.ClientRepository;
import com.example.twitter.user.UserRepository;
import com.example.twitter.user.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final ClientAuxMapper clientAuxMapper;

    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper, UserMapper userMapper, UserRepository userRepository, ClientAuxMapper clientAuxMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.clientAuxMapper = clientAuxMapper;
    }
    @Transactional
    public void followAccount(String username,Long userWhoFollows){
        User user = userRepository.findByUsername(username).get();
        User user1 = userRepository.findByUsername(userRepository.findById(userWhoFollows).get().getUsername()).get();
        Client client = clientRepository.findByUser_Id(user.getId());
        Client client1 = clientRepository.findByUser_Id(user1.getId());
        List<Client> followers = client.getFollowersClient();
        followers.add(client1);
        List<Client> following = client1.getFollowingAccounts();
        following.add(client);
        client.setFollowers(client.getFollowers() + 1);
        client1.setAccountsFollowed(client1.getAccountsFollowed() + 1);
        client.setFollowersClient(followers);
        client1.setFollowingAccounts(following);
        clientRepository.save(client);
        clientRepository.save(client1);

    }

    @Transactional
    public void unfollowAccount(String username, Long userWhoFollows) {
        User user = userRepository.findByUsername(username).get();
        User user1 = userRepository.findByUsername(userRepository.findById(userWhoFollows).get().getUsername()).get();
        Client client = clientRepository.findByUser_Id(user.getId());
        Client client1 = clientRepository.findByUser_Id(user1.getId());
        List<Client> followers = client.getFollowersClient();
        List<Client> following = client1.getFollowingAccounts();
        if (followers.contains(client1)) {
            followers.remove(client1);
            client.setFollowers(client.getFollowers() - 1);
        }
        if (following.contains(client)) {
            following.remove(client);
            client1.setAccountsFollowed(client1.getAccountsFollowed() - 1);
        }
        client.setFollowersClient(followers);
        client1.setFollowingAccounts(following);
        clientRepository.save(client);
        clientRepository.save(client1);
    }

    @Transactional
    public void followAction (String username, Long userWhoFollows){
        User user = userRepository.findByUsername(username).get();
        User user1 = userRepository.findByUsername(userRepository.findById(userWhoFollows).get().getUsername()).get();
        Client client = clientRepository.findByUser_Id(user.getId());
        Client client1 = clientRepository.findByUser_Id(user1.getId());
        if(client.getFollowersClient().contains(client1) && client1.getFollowingAccounts().contains(client)){
            unfollowAccount( username, userWhoFollows);
        }else{
            followAccount( username,userWhoFollows);
        }

    }
    // Update client data
    @Transactional
    public void updateClientData(Long id, String str) {
        Client client = clientRepository.findByUser_Id(id);
        client.setBio(str);
        clientRepository.save(client);
    }
    @Transactional
    public List<String> getFollowers(Long id) {
        List<Client> followers = clientRepository.findFollowersByClientId(clientRepository.findByUser_Id(id).getId());
        List<String> list = new ArrayList<>();
        for(Client cl : followers){
            list.add(cl.getUser().getUsername());
        }
        return list.stream().toList();
    }

    @Transactional
    public List<String> getFollowing(Long id) {
        List<Client> following = clientRepository.findFollowingByClientId(clientRepository.findByUser_Id(id).getId());
        List<String> list = new ArrayList<>();
        for(Client cl : following){
            list.add(cl.getUser().getUsername());
        }
        return list.stream().toList();
    }
    // Delete a client account

    @Transactional
    public List<String> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        List<String> list = new ArrayList<>();
        for(Client cl : clients){
            list.add(cl.getUser().getUsername());
        }
        return list.stream().toList();
    }

    @Transactional
    public ClientAuxDTO getClientData(Long id){
        Client client = clientRepository.findByUser_Id(id);
        return clientAuxMapper.EntityToDTO(client);
    }
    @Transactional
    public void deleteClient(Long id) {
        clientRepository.deleteById(clientRepository.findByUser_Id(id).getId());
        userRepository.deleteById(id);
    }
}
