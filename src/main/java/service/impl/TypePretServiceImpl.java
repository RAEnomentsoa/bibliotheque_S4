package service.impl;

import model.TypePret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.TypePretRepository;
import service.TypePretService;

import java.util.List;
import java.util.Optional;

@Service
public class TypePretServiceImpl implements TypePretService {

    @Autowired
    private TypePretRepository typePretRepository;

    @Override
    public List<TypePret> findAll() {
        return typePretRepository.findAll();
    }

    @Override
    public Optional<TypePret> findById(Long id) {
        return typePretRepository.findById(id);
    }

    @Override
    public TypePret save(TypePret typePret) {
        return typePretRepository.save(typePret);
    }

    @Override
    public void deleteById(Long id) {
        typePretRepository.deleteById(id);
    }
}
