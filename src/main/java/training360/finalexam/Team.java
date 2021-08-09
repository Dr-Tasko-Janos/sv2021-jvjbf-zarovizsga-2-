package training360.finalexam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "teams")
@Data
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @NotEmpty
    private String name;

    @OneToMany(mappedBy = "team", cascade = CascadeType.PERSIST)
    private Set<Player> players;

    public Team(String name) {
        this.name = name;
    }

    public void addNewPlayer(Player player) {
        player.setTeam(this);
        if (players == null) {
            players = new HashSet<>();
        }
        players.add(player);
        player.setTeam(this);
    }
}
