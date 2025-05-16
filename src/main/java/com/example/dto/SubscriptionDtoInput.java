package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SubscriptionDtoInput {
    @Size(max = 255, message = "Максимальная длина - 255 символов")
    @NotBlank(message = "Наименование подписки должна быть указана")
    private String name;
}
