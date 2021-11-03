package com.itis.stalkershop.services.implementations;

import com.itis.stalkershop.models.Cart;
import com.itis.stalkershop.models.CartDto;
import com.itis.stalkershop.models.ItemDto;
import com.itis.stalkershop.repositories.interfaces.CartRepository;
import com.itis.stalkershop.repositories.interfaces.ItemsRepository;
import com.itis.stalkershop.services.interfaces.CartService;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.itis.stalkershop.utils.UtilsKt.jsonToList;
import static com.itis.stalkershop.utils.UtilsKt.toJson;

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

    @NotNull
    @Override
    public CartDto get(
            @NotNull String userEmail
    ) {
        Cart cart = cartRepository.get(userEmail);

        // If specified user's cart doesn't exist, create it
        if (cart == null) {
            Cart newCart = new Cart(
                    userEmail,
                    toJson(Collections.emptyList())
            );

            cartRepository.add(newCart);
            cart = newCart;
        }

        // Retrieve item names from cart
        List<String> cartItemNames = jsonToList(cart.getItemNamesJson());

        // Get items associated with provided names from the database
        List<ItemDto> cartItems = new ArrayList<>();
        cartItemNames.forEach(itemName ->
                cartItems.add(
                        itemsRepository
                                .findByPrimaryKey(itemName)
                                .get()
                                .toItemDto()
                )
        );

        return new CartDto(
                userEmail,
                cartItems
        );
    }

    @Override
    public void addItem(
            @NotNull String userEmail,
            @NotNull String newItemName
    ) {
        Cart cart = cartRepository.get(userEmail);

        // If specified user's cart doesn't exist,
        // create it with new item
        if (cart == null) {
            cartRepository.add(new Cart(
                    userEmail,
                    toJson(List.of(newItemName))
            ));
            return;
        }

        // Update content of received cart
        List<String> cartItemNames = jsonToList(cart.getItemNamesJson());
        cartItemNames.add(newItemName);

        // Send updated cart back
        cartRepository.update(new Cart(
                userEmail,
                toJson(cartItemNames)
        ));
    }

    @Override
    public void delete(
            @NotNull String userEmail
    ) {
        cartRepository.delete(userEmail);
    }
}
