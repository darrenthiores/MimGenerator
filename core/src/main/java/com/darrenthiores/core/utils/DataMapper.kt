package com.darrenthiores.core.utils

import com.darrenthiores.core.model.data.Memes
import com.darrenthiores.core.model.domain.ImageDomain
import com.darrenthiores.core.model.presenter.Image

object DataMapper {

    fun mapResponseToDomain(input:List<Memes>):List<ImageDomain> =
        input.map {

            ImageDomain(
                image = it.image
            )

        }

    fun mapDomainToPresenter(input: List<ImageDomain>):List<Image> =
        input.map {

            Image(
                image = it.image
            )

        }

}