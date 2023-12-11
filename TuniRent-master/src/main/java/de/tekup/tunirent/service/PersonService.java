package de.tekup.tunirent.service;

import de.tekup.tunirent.dto.PersonDTO;
import de.tekup.tunirent.dto.PersonRequest;

import java.util.List;

public interface PersonService {
    String updatePersonalData(PersonRequest personRequest);

    List<PersonDTO> getAll();
}