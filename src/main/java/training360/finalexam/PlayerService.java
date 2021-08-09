package training360.finalexam;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository repository;

    private final ModelMapper modelMapper;

    private Type targetTypeForList = new TypeToken<List<PlayerDto>>(){}.getType();


    public List<PlayerDto> allPlayers() {
        return repository.findAll().stream().map(e -> modelMapper.map(e, PlayerDto.class)).collect(Collectors.toList());
    }


    public PlayerDto createPlayer(CreatePlayerCommand command) {
        Player newPlayer = repository.save(new Player(command.getName(), command.getDateOfBirth(), command.getPosition()));
        return  modelMapper.map(newPlayer, PlayerDto.class);

    }

    public void deletePlayerById(Long id) {
        repository.deleteById(id);
    }
}
