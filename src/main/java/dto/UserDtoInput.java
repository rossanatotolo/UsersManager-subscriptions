package dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDtoInput {
    @Size(min = 2, max = 255, message = "Максимальная длина - 255 символов")
    @NotBlank(message = "Имя пользователя должно быть указано")
    private String name;
    @NotBlank(message = "Имейл должен быть указан")
    @Email(message = "Имейл должен содержать символ «@». Формат имейла: example@mail.com")
    @Size(min = 6, max = 255, message = "Максимальная длина - 255 символов")
    private String email;
}
