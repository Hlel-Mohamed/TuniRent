package de.tekup.tunirent.service;

import de.tekup.tunirent.model.Client;

import java.util.List;
public interface ClientService {
    Client saveClient(Client client);
    List<Client> getAllClients();
    Client getClientById(Long id);
    Client getClientByUsername(String username);
    Client updateClient(Client client, Long id);
    void deleteClientById(Long id);
}
