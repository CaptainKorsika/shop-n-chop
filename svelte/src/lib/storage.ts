import {writable} from "svelte/store";
import {BROWSER} from "esm-env";

export interface RecipeIngredient {
    name: String,
    amount: String,
    unit: String,
}

export interface Recipe {
    day: String,
    selectedRecipe: {
        id: String,
        name: String,
        manual: String,
        duration: String,
        ingredients: RecipeIngredient[]
    }
}


export const selectedRecipesStorage = writable<Recipe[]>([] as Recipe[])

export function readDataFromSessionStorage() {
    if (BROWSER) {
        const storage = window.sessionStorage.getItem("selectedRecipes")
        try {
            return storage == null ? [] : JSON.parse(storage)
        } catch {
            return []
        }
    }
}

export function deleteDataFromSessionStorage(day: String) {
    if (BROWSER) {
        selectedRecipesStorage.update(selectedRecipes => {
            const updatedRecipes = selectedRecipes.filter(recipe => recipe.day !== day);
            sessionStorage.setItem('selectedRecipes', JSON.stringify(updatedRecipes));

            return updatedRecipes
        })

    }
}


export function updateRecipeStorage(recipe: Recipe) {
    selectedRecipesStorage.update(selectedRecipes => {
        selectedRecipes.push(recipe)
        return selectedRecipes
    })
}

selectedRecipesStorage.subscribe(data => {
    const selectedRecipesJSON = JSON.stringify(data)
    if (BROWSER) {
        window.sessionStorage.setItem("selectedRecipes", selectedRecipesJSON)
    }

})
