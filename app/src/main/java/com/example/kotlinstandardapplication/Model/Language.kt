package com.example.kotlinstandardapplication.Model

/**
 * @author Phong-Apero
 * @since 04-05-2023
 * @param name name of the language. For example: Vietnam
 * @param codeName short name presents for this language. For example: vn
 * @param chosen indicates that this language is selected or not
 */
class Language
constructor(val name: Int, val codeName: String, var chosen: Boolean)
{
}