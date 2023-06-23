package br.com.legacyink.api.domainconverter;

import br.com.legacyink.domain.model.Item;
import br.com.legacyink.api.dto.input.ItemInput;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemConvertido {

    @Autowired
    private ModelMapper modelMapper;

    public Item paraModelo(ItemInput itemInput) {
        return modelMapper.map(itemInput, Item.class);
    }

    public void copiaDTOparaModeloDominio(ItemInput itemInput, Item item) {
        modelMapper.map(itemInput, item);
    }

}
