package uz.bank.apelsin.model;

import lombok.*;
import uz.bank.apelsin.model.template.AbsEntityInteger;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileStorage  extends AbsEntityInteger {

    private String name;
    private String extension;
    private long fileSize;
    private String hashId;
    private String contentType;
    private String uploadPath;
}
