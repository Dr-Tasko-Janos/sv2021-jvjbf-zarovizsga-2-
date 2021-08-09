package training360.finalexam;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping()
    public List<TeamDto> allTeams() {
        return teamService.allTeams();
    }

    @PostMapping()
    public TeamDto createTeam(@Valid @RequestBody CreateTeamCommand command) {
        return teamService.createTeam(command);
    }

    @PostMapping("/{id}/players")
    public TeamDto insertPlayerIntoTeam(@PathVariable("id") long id, @Valid @RequestBody CreatePlayerCommand command) {
        return teamService.insertPlayerIntoTeam(id, command);
    }

    @PutMapping("{id}/players")
    public TeamDto transferExistingPlayer(@PathVariable("id") long id, @RequestBody UpdateExistingPlayerStatusForTransferCommand command) {
        return teamService.transferExistingPlayer(id, command);
    }

}
