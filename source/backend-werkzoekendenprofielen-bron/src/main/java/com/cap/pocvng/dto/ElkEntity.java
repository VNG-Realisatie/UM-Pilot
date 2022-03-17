package com.cap.pocvng.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "werkzoekende-request-index")
public class ElkEntity {

    @Id
    String id;

    private WerkzoekendeProfielMatchesRequest request;

    @Field(type = FieldType.Date, format = {}, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'") //TODO: UTC TIMEZONE
    private LocalDateTime timestamp = LocalDateTime.now();

    private String toOin;

    private String fromOin;

    public ElkEntity(WerkzoekendeProfielMatchesRequest request, String toOin, String fromOin) {
        this.request = request;
        this.toOin = toOin;
        this.fromOin = fromOin;
    }
}
