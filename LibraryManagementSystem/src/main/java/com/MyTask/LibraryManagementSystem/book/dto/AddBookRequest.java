package com.MyTask.LibraryManagementSystem.book.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddBookRequest {
    @NotBlank(message = "Title is a required field")
    private String title;
    @NotBlank(message = "Author is a required field")
    private String author;
    @NotBlank(message = "Descripion is a required field")
    private String description;
}
