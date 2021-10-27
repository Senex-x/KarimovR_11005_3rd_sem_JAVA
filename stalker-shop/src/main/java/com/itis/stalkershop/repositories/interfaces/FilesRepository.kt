package com.itis.stalkershop.repositories.interfaces

import com.itis.stalkershop.models.Image
import com.itis.stalkershop.models.ImageDto

interface FilesRepository : Repository<Long, ImageDto, Image>