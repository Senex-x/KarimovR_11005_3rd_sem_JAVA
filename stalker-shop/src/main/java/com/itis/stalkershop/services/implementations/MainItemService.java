package com.itis.stalkershop.services.implementations;

import com.itis.stalkershop.models.Item;
import com.itis.stalkershop.models.ItemDto;
import com.itis.stalkershop.repositories.interfaces.ItemsRepository;
import com.itis.stalkershop.services.interfaces.ItemService;
import com.itis.stalkershop.utils.exceptions.ItemNotFoundException;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public class MainItemService implements ItemService {
    private final ItemsRepository itemsRepository;

    public MainItemService(
            ItemsRepository itemsRepository
    ) {
        this.itemsRepository = itemsRepository;
    }

    @Override
    public void add(@NotNull ItemDto newItem) {
        itemsRepository.save(newItem.toItem());
    }

    @NotNull
    @Override
    public ItemDto get(
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

    @NotNull
    @Override
    public List<ItemDto> getAll() {
        return itemsRepository.findAll().stream()
                .map(Item::toItemDto).toList();
    }
}
