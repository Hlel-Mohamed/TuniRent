package de.tekup.tunirent.service.impl;

import de.tekup.tunirent.exception.ResourceNotFoundException;
import de.tekup.tunirent.model.Client;
import de.tekup.tunirent.repository.ClientRepository;
import de.tekup.tunirent.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client", "Id", id));
    }

    @Override
    public Client getClientByUsername(String username) {
        Optional<Client> clientOptional = Optional.ofNullable(clientRepository.findByUsername(username));

        if (clientOptional.isPresent()) {
            return clientOptional.get();
        } else {
            throw new ResourceNotFoundException("Client", "Username", username);
        }
    }

    @Override
    public Client updateClient(Client client, Long id) {
        Client existingClient = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client", "Id", id));
        existingClient.setFirstName(client.getFirstName());
        existingClient.setLastName(client.getLastName());
        existingClient.setPhoneNumber(client.getPhoneNumber());
        existingClient.setUsername(client.getUsername());
        existingClient.setPassword(client.getPassword());
        clientRepository.save(existingClient);
        return existingClient;
    }

    @Override
    public void deleteClientById(Long id) {
        getClientById(id);
        clientRepository.deleteById(id);
    }
}
