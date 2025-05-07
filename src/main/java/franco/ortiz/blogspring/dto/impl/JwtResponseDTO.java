package franco.ortiz.blogspring.dto.impl;

import lombok.Getter;

@Getter
public class JwtResponseDTO {
    private String token;

    public JwtResponseDTO(String token) {
        this.token = token;
    }
}
