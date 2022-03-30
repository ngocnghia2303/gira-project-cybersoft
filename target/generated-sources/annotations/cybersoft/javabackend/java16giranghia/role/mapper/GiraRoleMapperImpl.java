package cybersoft.javabackend.java16giranghia.role.mapper;

import cybersoft.javabackend.java16giranghia.role.dto.GiraRoleDTO;
import cybersoft.javabackend.java16giranghia.role.model.GiraRole;
import cybersoft.javabackend.java16giranghia.role.model.GiraRole.GiraRoleBuilder;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-30T15:01:31+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
public class GiraRoleMapperImpl implements GiraRoleMapper {

    @Override
    public GiraRole mapToEntity(GiraRoleDTO dto) {
        if ( dto == null ) {
            return null;
        }

        GiraRoleBuilder<?, ?> giraRole = GiraRole.builder();

        giraRole.code( dto.getCode() );
        giraRole.description( dto.getDescription() );

        return giraRole.build();
    }
}
