package service.impl;

import model.JourOuvrable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.JourOuvrableRepository;
import service.JourOuvrableService;

import java.util.List;
import java.util.Optional;

@Service
public class JourOuvrableServiceImpl implements JourOuvrableService {

    @Autowired
    private JourOuvrableRepository jourOuvrableRepository;

    @Override
    public List<JourOuvrable> findAll() {
        return jourOuvrableRepository.findAll();
    }

    @Override
    public Optional<JourOuvrable> findById(Long id) {
        return jourOuvrableRepository.findById(id);
    }

    @Override
    public JourOuvrable save(JourOuvrable jourOuvrable) {
        return jourOuvrableRepository.save(jourOuvrable);
    }

    @Override
    public void deleteById(Long id) {
        jourOuvrableRepository.deleteById(id);
    }
}
