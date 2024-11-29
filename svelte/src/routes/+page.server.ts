export async function fetchRecipes() {
    return await fetch("http://localhost:8080/shopnchop/recipes")
}