package service.impl;

import model.TypeAdherent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.TypeAdherentRepository;
import service.TypeAdherentService;

import java.util.List;
import java.util.Optional;

@Service
public class TypeAdherentServiceImpl implements TypeAdherentService {

    @Autowired
    private TypeAdherentRepository typeAdherentRepository;

    @Override
    public List<TypeAdherent> findAll() {
        return typeAdherentRepository.findAll();
    }

    @Override
    public Optional<TypeAdherent> findById(Long id) {
        return typeAdherentRepository.findById(id);
    }

    @Override
    public TypeAdherent save(TypeAdherent typeAdherent) {
        return typeAdherentRepository.save(typeAdherent);
    }

    @Override
    public void deleteById(Long id) {
        typeAdherentRepository.deleteById(id);
    }
}
