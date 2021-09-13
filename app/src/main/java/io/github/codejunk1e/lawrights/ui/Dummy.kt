package io.github.codejunk1e.lawrights.ui

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import io.github.codejunk1e.lawrights.R
import io.github.codejunk1e.lawrights.models.CardModel
import io.github.codejunk1e.lawrights.models.LinksModel

    fun provideCardsDummyList() = listOf(
        CardModel(
            image = R.drawable.law_card_image, caption = R.string.law_caption,
            desc = R.string.law_description, actionText = R.string.law_action_text
        ),
        CardModel(
            image = R.drawable.law_card_image, caption = R.string.law_caption,
            desc = R.string.law_description, actionText = R.string.law_action_text
        ),
        CardModel(
            image = R.drawable.law_card_image, caption = R.string.law_caption,
            desc = R.string.law_description, actionText = R.string.law_action_text
        ),
        CardModel(
            image = R.drawable.law_card_image, caption = R.string.law_caption,
            desc = R.string.law_description, actionText = R.string.law_action_text
        ),
    )

    fun provideDummyLinksList() = listOf(
        LinksModel(title = "My Rights", color = R.color.bg_color_grid_one),
        LinksModel(title = "My Duties", color = R.color.bg_color_grid_two),
        LinksModel(title = "General \n" + "Provisions", color = R.color.bg_color_grid_three),
        LinksModel(title = "Find a Lawyer", color = R.color.bg_color_grid_four),
        LinksModel(title = "Know your\n" + "Representatives", color = R.color.bg_color_grid_five),
        LinksModel(title = "Constitution", color = R.color.bg_color_grid_six),
        LinksModel(title = "Report a Violation", color = R.color.bg_color_grid_seven)
    )