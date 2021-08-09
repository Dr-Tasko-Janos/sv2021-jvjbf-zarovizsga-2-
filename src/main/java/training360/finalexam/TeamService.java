package training360.finalexam;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final PlayerRepository repository;

    ModelMapper modelMapper;

    public List<TeamDto> allTeams() {
        return teamRepository.findAll().stream().map(e -> modelMapper.map(e, TeamDto.class)).collect(Collectors.toList());
    }

    public TeamDto createTeam(CreateTeamCommand command) {
        Team newTeam = teamRepository.save(new Team(command.getName()));

        return modelMapper.map(newTeam, TeamDto.class);
    }

    @Transactional
    public TeamDto insertPlayerIntoTeam(long id, CreatePlayerCommand command) {
        Player playerToInsert = repository.save(new Player(command.getName(), command.getDateOfBirth(), command.getPosition()));

        Team targetTeam = teamRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Team not found with the given id: " + id));

        targetTeam.addNewPlayer(playerToInsert);

        return modelMapper.map(targetTeam, TeamDto.class);
    }

@Transactional
    public TeamDto transferExistingPlayer(long id, UpdateExistingPlayerStatusForTransferCommand command) {
        Player playerToTransfer = repository.findById(command.getId()).orElseThrow(() -> new IllegalArgumentException("Player not found with id: " + id));
    Team transferTargetTeam = teamRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Team not found with the given id: " + id));
        if((playerToTransfer.getTeam() == null)  && checkCondition(id, playerToTransfer.getPosition())) {
            playerToTransfer.setTeam(transferTargetTeam);
        }
        return modelMapper.map(transferTargetTeam, TeamDto.class);
    }

    public boolean checkCondition (Long id, Enum position) {
        Team transferTargetTeam = teamRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Team not found with the given id: " + id));
        long numberOfPlayerInPosition = transferTargetTeam.getPlayers().stream().filter(e -> e.getPosition().equals(position)).count();
        return (numberOfPlayerInPosition > 2) ? true : false;
    }
}
