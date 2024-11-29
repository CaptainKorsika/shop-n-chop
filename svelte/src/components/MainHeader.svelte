<script>
    import "../styles/main-header.css"
    import {goto} from "$app/navigation";

    function navigate() {
         goto('/fridge');
    }
</script>



<header>
    <div class="button-section">
        <div class="recipe-section">
            <button class="create-recipe">Create New Recipe</button>
            <button class="show-recipes">Show Recipes</button>
        </div>
        <div class="ingredient-section">
            <button>Add Ingredient</button>
            <button>Show Ingredients</button>
        </div>
    </div>
    <button class="show-fridge" on:click={navigate}>Open Fridge</button>
</header>