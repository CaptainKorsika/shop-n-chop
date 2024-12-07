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
        updateList.push("test")
        ingredients = updateList
    }

    function navigate(path) {
        goto(`${path}`)
    }

    function createRecipe() {

    }


</script>



<div class="frame">
    <header>
        <button onclick={() => {navigate("/recipes")}}>Go Back</button>
    </header>
    <div class="main-add-recipe-wrapper">
        <div class="recipe-input-area">
            <div class="recipe-input-wrapper">
                <h2>Name</h2>
                <input type="text">
            </div>
            <div class="recipe-input-wrapper">
                <h2>Duration</h2>
                <input type="text">
            </div>
            <div class="recipe-input-wrapper">
                <h2>Manual</h2>
                <input type="text">
            </div>
        </div>
        <div class="ingredient-list">
            <h2>Ingredients</h2>
            <ul>
                {#each ingredients as ingredient}
                    <AddRecipeIngredient/>
                {/each}
            </ul>
            <button class="add-ingredient-button" onclick={addIngredient}>Add ingredient</button>
        </div>

    </div>
    <footer>
        <button onclick={createRecipe}>Create recipe</button>
    </footer>
</div>