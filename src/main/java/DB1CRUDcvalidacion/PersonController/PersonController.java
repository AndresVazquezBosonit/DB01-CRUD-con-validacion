package DB1CRUDcvalidacion.PersonController;

import DB1CRUDcvalidacion.entity.Person;
import DB1CRUDcvalidacion.entity.PersonInputDTO;
import DB1CRUDcvalidacion.entity.PersonOutputDTO;
import DB1CRUDcvalidacion.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/person")
public class PersonController {

  @Autowired private PersonService personService;

  @PostMapping
  public PersonOutputDTO newPerson(@RequestBody PersonInputDTO personInputDTO) throws Exception {
    return personService.addPerson(personInputDTO);
  }

  @GetMapping
  public List<PersonOutputDTO> personList() {
    return personService.listPerson();
  }

  @GetMapping("/{id}")
  public PersonOutputDTO personById(@PathVariable int id) throws Exception {
    return personService.personById(id);
  }

  @DeleteMapping("/{id}")
  public String deletePerson(@PathVariable int id) throws Exception {
    return personService.deletePersona(id);
  }

  @PutMapping("/{id}")
  public PersonOutputDTO updatePerson(@RequestBody PersonInputDTO person, @PathVariable int id)
      throws Exception {
    return personService.updatePerson(person, id);
  }
}
