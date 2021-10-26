package com.itis.stalkershop.repositories.interfaces

import com.itis.stalkershop.models.UploadedFile
import com.itis.stalkershop.models.UploadedFileDto

interface FilesRepository : Repository<Long, UploadedFileDto, UploadedFile>