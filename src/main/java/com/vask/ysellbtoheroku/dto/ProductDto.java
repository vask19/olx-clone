package com.vask.ysellbtoheroku.dto;
import com.vask.ysellbtoheroku.model.enums.Locate;
import com.vask.ysellbtoheroku.model.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

    private int id;
    private String name;
    private String description;
    private Type type;
    private Locate locate;
    private Double price;
    @Builder.Default
    private List<Long> imageIds = new ArrayList<>();
    private Integer userId;
    private String ownerUsername;
    private Long previewImageId;
    private LocalDateTime dateOfCreated;

    public ProductDto(Integer bookId) {
        this.id = bookId;
    }
}
