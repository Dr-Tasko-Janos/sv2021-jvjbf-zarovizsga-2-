package training360.finalexam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePlayerCommand {

    @NotNull
    @NotEmpty
    @NotBlank
    private String name;

    private LocalDate dateOfBirth;

    private Position position;

}
