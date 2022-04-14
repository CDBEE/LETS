package com.lets.web.dto.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostSaveRequestDto {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    private List<String> tags;


}
