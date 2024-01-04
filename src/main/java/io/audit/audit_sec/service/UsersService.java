package io.audit.audit_sec.service;

import io.audit.audit_sec.domain.Users;
import io.audit.audit_sec.model.UsersDTO;
import io.audit.audit_sec.repos.UsersRepository;
import io.audit.audit_sec.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(final UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<UsersDTO> findAll() {
        final List<Users> userses = usersRepository.findAll(Sort.by("id"));
        return userses.stream()
                .map(users -> mapToDTO(users, new UsersDTO()))
                .toList();
    }

    public UsersDTO get(final Long id) {
        return usersRepository.findById(id)
                .map(users -> mapToDTO(users, new UsersDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final UsersDTO usersDTO) {
        final Users users = new Users();
        mapToEntity(usersDTO, users);
        return usersRepository.save(users).getId();
    }

    public void update(final Long id, final UsersDTO usersDTO) {
        final Users users = usersRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(usersDTO, users);
        usersRepository.save(users);
    }

    public void delete(final Long id) {
        usersRepository.deleteById(id);
    }

    private UsersDTO mapToDTO(final Users users, final UsersDTO usersDTO) {
        usersDTO.setId(users.getId());
        usersDTO.setLogin(users.getLogin());
        usersDTO.setNom(users.getNom());
        usersDTO.setPrenom(users.getPrenom());
        usersDTO.setPassword(users.getPassword());
        usersDTO.setRole(users.getRole());
        return usersDTO;
    }

    private Users mapToEntity(final UsersDTO usersDTO, final Users users) {
        users.setLogin(usersDTO.getLogin());
        users.setNom(usersDTO.getNom());
        users.setPrenom(usersDTO.getPrenom());
        users.setPassword(usersDTO.getPassword());
        users.setRole(usersDTO.getRole());
        return users;
    }

}
