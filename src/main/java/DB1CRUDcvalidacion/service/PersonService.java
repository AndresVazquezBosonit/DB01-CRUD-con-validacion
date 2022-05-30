package DB1CRUDcvalidacion.service;

import DB1CRUDcvalidacion.entity.Person;
import DB1CRUDcvalidacion.entity.PersonInputDTO;
import DB1CRUDcvalidacion.entity.PersonOutputDTO;
import DB1CRUDcvalidacion.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PersonService {
  @Autowired private PersonRepository personRepository;
  @Autowired private ModelMapper modelMapper;

  ///// ----------------------- Add new Person -----------------------/////
  public PersonOutputDTO addPerson(PersonInputDTO personInputDTO) throws Exception {
    if (personInputDTO.getUsername().length() < 6 || personInputDTO.getUsername().length() > 10) {
      throw new Exception("The username must be between 6 and 10 characters long");
    } else if (personInputDTO.getUsername() == null) {
      throw new Exception("The username cannot be null");
    } else {
      LocalDate creationDate = LocalDate.now();
      PersonOutputDTO personOutputDTO = new PersonOutputDTO();
      personInputDTO.setCreated_date(
          new SimpleDateFormat("yyyy-mm-dd").parse(creationDate.toString()));
      Person personEntity =
          personRepository.saveAndFlush(modelMapper.map(personInputDTO, Person.class));
      personOutputDTO.setId_person(personEntity.getId_person());
      personOutputDTO.setUsername(personEntity.getUsername());
      personOutputDTO.setName(personEntity.getName());
      personOutputDTO.setSurname(personEntity.getSurname());
      personOutputDTO.setCompany_email(personEntity.getCompany_email());
      personOutputDTO.setPersonal_email(personEntity.getPersonal_email());
      personOutputDTO.setCity(personEntity.getCity());
      personOutputDTO.setActive(personEntity.getActive());
      personOutputDTO.setCreated_date(personEntity.getCreated_date());
      personOutputDTO.setImage_url(personEntity.getImage_url());
      personOutputDTO.setTermination_date(personEntity.getTermination_date());

      return personOutputDTO;
    }
  }

  ///// ----------------------------- Get All person ---------------------/////
  public List<PersonOutputDTO> listPerson() {
    List<PersonOutputDTO> personList = new ArrayList<>();
    personRepository.findAll().stream()
        .forEach(
            person -> {
              PersonOutputDTO personOutputDTO = new PersonOutputDTO();
              personOutputDTO.setId_person(person.getId_person());
              personOutputDTO.setUsername(person.getUsername());
              personOutputDTO.setName(person.getName());
              personOutputDTO.setSurname(person.getSurname());
              personOutputDTO.setCompany_email(person.getCompany_email());
              personOutputDTO.setPersonal_email(person.getPersonal_email());
              personOutputDTO.setCity(person.getCity());
              personOutputDTO.setImage_url(person.getImage_url());
              personOutputDTO.setCreated_date(person.getCreated_date());
              personOutputDTO.setActive(person.getActive());
              personList.add(personOutputDTO);
            });
    return personList;
  }
  ///// ---------------------------- find Person By Id ------------------------/////
  public PersonOutputDTO personById(int id) throws Exception {
    try {
      Optional<Person> personInBD = personRepository.findById(id);
      PersonOutputDTO personOutputDTO = new PersonOutputDTO();
      personOutputDTO.setId_person(personInBD.get().getId_person());
      personOutputDTO.setUsername(personInBD.get().getUsername());
      personOutputDTO.setName(personInBD.get().getName());
      personOutputDTO.setSurname(personInBD.get().getSurname());
      personOutputDTO.setCompany_email(personInBD.get().getCompany_email());
      personOutputDTO.setPersonal_email(personInBD.get().getPersonal_email());
      personOutputDTO.setCity(personInBD.get().getCity());
      personOutputDTO.setImage_url(personInBD.get().getImage_url());
      personOutputDTO.setCreated_date(personInBD.get().getCreated_date());
      personOutputDTO.setActive(personInBD.get().getActive());
      return personOutputDTO;

    } catch (Exception e) {
      throw new Exception("the person no does not exist");
    }
  }

  ///// ------------------------------- Delete Person --------------------------- /////
  public String deletePersona(int id) throws Exception {
    try {
      Optional<Person> personToDelete = personRepository.findById(id);
      personRepository.deleteById(id);
      return "Has been deleted: "
          + personToDelete.get().getName()
          + " from: "
          + personToDelete.get().getCity()
          + " with id: "
          + personToDelete.get().getId_person();

    } catch (Exception e) {
      throw new Exception("the person does not exist");
    }
  }

  ///// -------------------------------- Update Person ----------------------------- /////
  public PersonOutputDTO updatePerson(PersonInputDTO personInputDTO, int id) throws Exception {
    Optional<Person> personEntity;
    try {
      personEntity = personRepository.findById(id);
      personInputDTO.setId_person(id);
      personInputDTO.setUsername(
          Optional.ofNullable(personInputDTO.getUsername())
              .orElse(personEntity.get().getUsername()));
      personInputDTO.setPassword(
          Optional.ofNullable(personInputDTO.getPassword())
              .orElse(personEntity.get().getPassword()));
      personInputDTO.setName(
          Optional.ofNullable(personInputDTO.getName()).orElse(personEntity.get().getName()));
      personInputDTO.setSurname(
          Optional.ofNullable(personInputDTO.getSurname()).orElse(personEntity.get().getSurname()));
      personInputDTO.setCompany_email(
          Optional.ofNullable(personInputDTO.getCompany_email())
              .orElse(personEntity.get().getCompany_email()));
      personInputDTO.setPersonal_email(
          Optional.ofNullable(personInputDTO.getPersonal_email())
              .orElse(personEntity.get().getPersonal_email()));
      personInputDTO.setCity(
          Optional.ofNullable(personInputDTO.getCity()).orElse(personEntity.get().getCity()));
      personInputDTO.setImage_url(
          Optional.ofNullable(personInputDTO.getImage_url())
              .orElse(personEntity.get().getImage_url()));
      personInputDTO.setActive(personEntity.get().getActive());
      personInputDTO.setCreated_date(personEntity.get().getCreated_date());
      personRepository.saveAndFlush(modelMapper.map(personInputDTO, Person.class));
      PersonOutputDTO personOutputDTO = modelMapper.map(personInputDTO, PersonOutputDTO.class);
      return personOutputDTO;

    } catch (Exception e) {
      throw new Exception("Something went wrong or the person does not exist");
    }
  }
}
