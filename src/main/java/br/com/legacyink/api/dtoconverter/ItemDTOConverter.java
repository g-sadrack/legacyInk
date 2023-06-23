package br.com.legacyink.api.dtoconverter;

import br.com.legacyink.api.dto.ItemDTO;
import br.com.legacyink.domain.model.Item;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemDTOConverter {
    @Autowired
    private ModelMapper modelMapper;

    public ItemDTO paraDTO(Item item) {
        return modelMapper.map(item, ItemDTO.class);
    }

    public List<ItemDTO> paraDTOLista(List<Item> clientes) {
        return clientes.stream().map(this::paraDTO).collect(Collectors.toList());
    }

}
