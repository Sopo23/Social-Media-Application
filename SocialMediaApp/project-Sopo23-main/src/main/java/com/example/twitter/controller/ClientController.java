package com.example.twitter.controller;

import com.example.twitter.dto.ClientAuxDTO;
import com.example.twitter.dto.ClientDTO;
import com.example.twitter.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.twitter.user.UrlMapping.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(CLIENT)
public class ClientController {

    private final ClientService clientService;

    @PutMapping(UPDATE)
    public ResponseEntity<Void> updateClient(@PathVariable Long id, @RequestParam String str) {
        try {
            clientService.updateClientData(id,str);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping(DELETE)
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        try {
            clientService.deleteClient(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping(FOLLOW)
    public ResponseEntity<Void> followAction(@RequestParam String username, @RequestParam Long userWhoFollows) {
        try {
            clientService.followAction(username, userWhoFollows);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @GetMapping(SHOW_FOLLOWERS)
    public ResponseEntity<List<String>> getFollowers(@PathVariable Long id) {
        try {
            List<String> followers = clientService.getFollowers(id);
            return ResponseEntity.ok(followers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping(SHOW_FOLLOWING)
    public ResponseEntity<List<String>> getFollowing(@PathVariable Long id) {
        try {
            List<String> following = clientService.getFollowing(id);
            return ResponseEntity.ok(following);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping(SHOW_ALL)
    public ResponseEntity<List<String>> getAllClients() {
        List<String> clients = clientService.getAllClients();
        return ResponseEntity.ok(clients);
    }
    @GetMapping(CLIENT_DATA)
    public ResponseEntity<ClientAuxDTO> getClientData(@PathVariable Long id){
        ClientAuxDTO clientDTO = clientService.getClientData(id);
        return ResponseEntity.ok(clientDTO);
    }
}
