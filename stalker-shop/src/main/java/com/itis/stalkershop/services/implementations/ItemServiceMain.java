package com.itis.stalkershop.services.implementations;

import com.itis.stalkershop.models.Item;
import com.itis.stalkershop.models.ItemDto;
import com.itis.stalkershop.repositories.interfaces.ItemsRepository;
import com.itis.stalkershop.services.interfaces.ItemService;
import com.itis.stalkershop.utils.exceptions.ItemNotFoundException;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class ItemServiceMain implements ItemService {
    private final ItemsRepository itemsRepository;

    public ItemServiceMain(
            ItemsRepository itemsRepository
    ) {
        this.itemsRepository = itemsRepository;
    }

    @NotNull
    @Override
    public ItemDto getItemDto(
            @NotNull String name
    ) {
        Optional<Item> itemOptional = itemsRepository.findByPrimaryKey(name);

        if (itemOptional.isPresent()) {
            return itemOptional.get().toItemDto();
        }

        throw new ItemNotFoundException(
                "Item with name '" + name + "' not exists"
        );
    }
}
