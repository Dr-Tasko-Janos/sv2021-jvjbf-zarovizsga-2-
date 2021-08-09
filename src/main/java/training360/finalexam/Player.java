package training360.finalexam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @NotEmpty
    private String name;

    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Position position;

    @ManyToOne
    @JoinColumn(name = "teams_id")
    private Team team;

    public Player(String name) {
        this.name = name;
    }

    public Player(String name, LocalDate dateOfBirth, Position position) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.position = position;
    }
}
