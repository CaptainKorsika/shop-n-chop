import {writable} from "svelte/store";

export interface Recipe {
    name: String
}


export const selectedRecipesStorage  = writable<Recipe[]>([] as Recipe[])


export function updateRecipeStorage(recipe: Recipe) {
    selectedRecipesStorage.update(selectedRecipes => {
        selectedRecipes.push(recipe)
        console.log(recipe)

        return selectedRecipes
    })
}

selectedRecipesStorage.subscribe(data => {
    const selectedRecipesJSON = JSON.stringify(data)
    // window.sessionStorage.setItem("selectedRecipes", selectedRecipesJSON)
})
