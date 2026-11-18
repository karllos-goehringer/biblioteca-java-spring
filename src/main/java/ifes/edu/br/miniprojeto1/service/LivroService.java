package ifes.edu.br.miniprojeto1.service;

import ifes.edu.br.miniprojeto1.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LivroService {
    private final LivroRepository livroRepository;
}
