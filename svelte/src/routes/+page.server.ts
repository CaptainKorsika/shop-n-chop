export function fetchRecipes() {
    fetch("/recipes").then(() => {console.log("test")})
}