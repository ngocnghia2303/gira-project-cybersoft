package cybersoft.javabackend.java16giranghia.role.mapper;

import cybersoft.javabackend.java16giranghia.role.dto.GiraGroupDTO;
import cybersoft.javabackend.java16giranghia.role.model.GiraGroup;
import cybersoft.javabackend.java16giranghia.role.model.GiraGroup.GiraGroupBuilder;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-24T09:26:32+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
public class GiraGroupMapperImpl implements GiraGroupMapper {

    @Override
    public GiraGroupDTO toDTO(GiraGroup model) {
        if ( model == null ) {
            return null;
        }

        GiraGroupDTO giraGroupDTO = new GiraGroupDTO();

        giraGroupDTO.setCode( model.getCode() );
        giraGroupDTO.setDescription( model.getDescription() );

        return giraGroupDTO;
    }

    @Override
    public GiraGroup toModel(GiraGroupDTO dto) {
        if ( dto == null ) {
            return null;
        }

        GiraGroupBuilder<?, ?> giraGroup = GiraGroup.builder();

        giraGroup.code( dto.getCode() );
        giraGroup.description( dto.getDescription() );

        return giraGroup.build();
    }
}
