package franco.ortiz.blogspring.dto.common;

import franco.ortiz.blogspring.dto.IDTOEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MappingDTO {
    private MappingDTO(){}

    public static IDTOEntity convertToDto(Object object, IDTOEntity dto){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(Boolean.TRUE);
        return modelMapper.map(object, dto.getClass());
    }

    public static Object convertToEntity(IDTOEntity dto, Class<?> obj){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(Boolean.TRUE);
        return modelMapper.map(dto, obj);
    }
}
