package de.tekup.tunirent.service.impl;

import de.tekup.tunirent.dto.PersonDTO;
import de.tekup.tunirent.dto.PersonRequest;
import de.tekup.tunirent.mapper.UserMapper;
import de.tekup.tunirent.model.Person;
import de.tekup.tunirent.repository.PersonRepository;
import de.tekup.tunirent.service.PersonService;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
@NoArgsConstructor
public abstract class PersonServiceImpl<T extends Person> implements PersonService {
    public abstract PersonRepository<T> getRepository();
    @Autowired
    @Setter
    protected UserMapper<T> userMapper;

    public String updatePersonalData(PersonRequest request) {
        T person = getRepository().findById(request.getId()).orElseThrow(() -> new RuntimeException("Person not found"));


        long personId = person.getId();
        boolean updateOccurred = false;

        if (request.getFirstName() != null && !request.getFirstName().isEmpty()) {
            getRepository().updateFirstName(personId, request.getFirstName());
            updateOccurred = true;
        }

        if (request.getLastName() != null && !request.getLastName().isEmpty()) {
            getRepository().updateLastName(personId, request.getLastName());
            updateOccurred = true;
        }

        if (request.getPhone() != null && !request.getPhone().isEmpty()) {
            getRepository().updatePhone(personId, request.getPhone());
            updateOccurred = true;
        }

        if (updateOccurred) {
            log.info("Update successful");
            return "Personal data updated successfully!";
        } else {
            throw new IllegalArgumentException("No valid field specified for update.");
        }
    }

    @Override
    public List<PersonDTO> getAll() {
        List<T> persons = getRepository().findAll();
        return persons.stream().map(this.userMapper::convertToDTO).toList();
    }
}
