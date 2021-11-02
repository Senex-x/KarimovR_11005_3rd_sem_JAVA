package com.itis.stalkershop.services.implementations;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itis.stalkershop.models.Cart;
import com.itis.stalkershop.models.CartDto;
import com.itis.stalkershop.models.ItemDto;
import com.itis.stalkershop.models.UserDto;
import com.itis.stalkershop.repositories.interfaces.CartRepository;
import com.itis.stalkershop.repositories.interfaces.ItemsRepository;
import com.itis.stalkershop.services.interfaces.CartService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CartServiceMain implements CartService {
    private final CartRepository cartRepository;
    private final ItemsRepository itemsRepository;

    public CartServiceMain(
            CartRepository cartRepository,
            ItemsRepository itemsRepository
    ) {
        this.cartRepository = cartRepository;
        this.itemsRepository = itemsRepository;
    }

    // TODO: Refactor
    @Override
    public CartDto get(
            @NotNull String userEmail
    ) {

        Cart cart = cartRepository.get(userEmail); // Unchecked nullable
        Gson gson = new Gson();

        List<String> items = gson.fromJson(
                cart.getItemNamesJson(),
                new TypeToken<List<String>>() {
                }.getType()
        );

        List<ItemDto> itemDtos = new ArrayList<>();

        items.forEach(itemName -> itemDtos.add(
                itemsRepository.findByPrimaryKey(itemName).get().toItemDto()
        ));

        return new CartDto(
                cart.getUserEmail(),
                itemDtos
        );
    }

    @Override
    public void addItem(
            @NotNull String userEmail,
            @NotNull String itemName
    ) {
        Gson gson = new Gson();
        Cart cart = cartRepository.get(userEmail);

        if (cart != null) { // Update
            List<String> items = gson.fromJson(
                    cart.getItemNamesJson(),
                    new TypeToken<List<String>>() {
                    }.getType()
            );
            items.add(itemName);
            String itemsString = gson.toJson(items);
            cartRepository.update(new Cart(
                    userEmail,
                    itemsString
            ));
        } else { // Add new
            cartRepository.add(new Cart(
                    userEmail,
                    gson.toJson(List.of(itemName))
            ));
        }
    }

    @Nullable
    @Override
    public CartDto get(
            @NotNull UserDto user
    ) {
        return CartService.super.get(user);
    }

    @Override
    public void addItem(
            @NotNull UserDto user,
            @NotNull String itemName
    ) {
        CartService.super.addItem(user, itemName);
    }
}
