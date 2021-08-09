package training360.finalexam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {

    private long id;

    private String name;

    private LocalDate dateOfBirth;

    private Position position;

    private Team team;
}
