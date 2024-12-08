<script>
    import "../../styles/frame.css"
    import "../../styles/header.css"
    import "../../styles/footer.css"
    import "../../styles/addRecipe.css"
    import {goto} from "$app/navigation";
    import AddRecipeIngredient from "../../components/AddRecipeIngredient.svelte";

    let ingredients = []


    function addIngredient() {
        let updateList = ingredients
        updateList.push({})
        ingredients = updateList
    }

    function navigate(path) {
        goto(`${path}`)
    }

    function createRecipe() {

        const name = document.getElementById("recipe-name-input").value
        const duration = document.getElementById("recipe-duration-input").value
        const manual = document.getElementById("recipe-manual-input").value

        if (name.length !== 0 && duration.length !== 0 && manual.length !== 0) {
            const recipeDto = {
                name: name,
                duration: duration,
                manual: manual,
                ingredients: ingredients
            }

            try{
                fetch(`http://localhost:8080/shopnchop/recipes`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(recipeDto)
                });
                goto("recipes")
            } catch (e) {
                console.log("Something went wrong")
            }
        }
    }


</script>



<div class="frame">
    <header class="add-recipe-header">
        <button onclick={() => {navigate("/recipes")}}>Go Back</button>
    </header>
    <div class="main-add-recipe-wrapper">
        <div class="recipe-input-area">
            <div class="recipe-input-wrapper">
                <h2>Name</h2>
                <input id="recipe-name-input" type="text">
            </div>
            <div class="recipe-input-wrapper">
                <h2>Duration</h2>
                <input id="recipe-duration-input" type="text">
            </div>
            <div class="recipe-input-wrapper">
                <h2>Manual</h2>
                <input id="recipe-manual-input" type="text">
            </div>
        </div>
        <div class="ingredient-list">
            <h2>Ingredients</h2>
            <ul>
                {#each ingredients as ingredient}
                    <AddRecipeIngredient bind:recipeIngredientDto={ingredient}/>
                {/each}
            </ul>
            <button class="add-ingredient-button" onclick={addIngredient}>Add ingredient</button>
        </div>

    </div>
    <footer>
        <button onclick={createRecipe}>Create recipe</button>
    </footer>
</div>