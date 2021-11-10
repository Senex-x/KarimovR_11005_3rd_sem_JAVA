package com.itis.stalkershop.repositories.interfaces

import com.itis.stalkershop.models.Item

// This is not a mistake.
// In fact repos should only work with models of lower level.
interface ItemsRepository: Repository<String, Item, Item>